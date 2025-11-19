package com.min01.unleashed;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AESUtil
{
    private static final int IV_SIZE = 12;
    private static final int TAG_LENGTH = 128;

    private static final String MASTER_KEY = ",OFAOe0)F)032-1V<#@_F2";
    private static final String SALT = "ewjweF))(ELKFEee_;1r1=3";
    public static final String HEADER = "BOSSESUN";
    
    private static final byte[] MAGIC_HEADER = HEADER.getBytes(StandardCharsets.UTF_8);
    
    private static SecretKey getMasterKey() throws NoSuchAlgorithmException, InvalidKeySpecException 
    {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(MASTER_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), "AES");
    }
    
    public static ByteArrayInputStream decryptFile(byte[] encryptedData) throws Exception 
    {
        SecretKey key = getMasterKey();
        ByteBuffer byteBuffer = ByteBuffer.wrap(encryptedData);
        byte[] magic = new byte[MAGIC_HEADER.length];
        byteBuffer.get(magic);
        if(!java.util.Arrays.equals(magic, MAGIC_HEADER)) 
        {
            return null;
        }
        byte[] iv = new byte[IV_SIZE];
        byteBuffer.get(iv);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH, iv);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] cipherBytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(cipherBytes);
        byte[] plainBytes = cipher.doFinal(cipherBytes);
        return new ByteArrayInputStream(plainBytes);
    }
    
    public static void encryptFile(File inputFile, File outputFile) throws Exception
    {
        SecretKey key = getMasterKey();
        byte[] iv = new byte[IV_SIZE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH, iv);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        try(FileInputStream fis = new FileInputStream(inputFile); FileOutputStream fos = new FileOutputStream(outputFile)) 
        {
            fos.write(MAGIC_HEADER);
            fos.write(iv);
            byte[] buffer = new byte[1024];
            int read;
            while((read = fis.read(buffer)) != -1)
            {
                byte[] cipherBytes = cipher.update(buffer, 0, read);
                if (cipherBytes != null) fos.write(cipherBytes);
            }
            byte[] finalBytes = cipher.doFinal();
            if(finalBytes != null)
            {
            	fos.write(finalBytes);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void encryptFiles(String extension) throws Exception 
    {
        File dir = getOrCreateDirectory("aes");
        File[] files = dir.listFiles((f) -> f.isFile() && f.getName().endsWith(extension));
        if(files == null) 
        	return;
        for(File file : files) 
        {
            File encryptedFile = new File(file.getParent(), file.getName() + ".dat");
            encryptFile(file, encryptedFile);
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static File getOrCreateDirectory(String name) 
    {
        File dir = new File(Minecraft.getInstance().gameDirectory, name);
        if(!dir.exists()) 
        {
        	dir.mkdir();
        }
        return dir;
    }
}
