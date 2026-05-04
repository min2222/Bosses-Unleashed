package com.min01.unleashed;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

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
        if(!Arrays.equals(magic, MAGIC_HEADER)) 
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
}
