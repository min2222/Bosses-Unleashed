package com.min01.unleashed.mixin;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.min01.unleashed.AESUtil;

import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.server.packs.resources.Resource;

@Mixin(value = Resource.class, priority = -15000)
public class MixinResource 
{
	@Shadow
	@Final
	private IoSupplier<InputStream> streamSupplier;
	   
    @Inject(method = "open", at = @At("HEAD"), cancellable = true)
    private void open(CallbackInfoReturnable<InputStream> cir) throws IOException 
    {
        byte[] data = this.streamSupplier.get().readAllBytes();
        if(data.length > 8 && new String(data, 0, 8, StandardCharsets.UTF_8).equals(AESUtil.HEADER)) 
        {
			try 
			{
				ByteArrayInputStream byteArray = AESUtil.decryptFile(data);
		    	cir.setReturnValue(byteArray);
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
        }
        else
        {
            cir.setReturnValue(new ByteArrayInputStream(data));
        }
    }
}