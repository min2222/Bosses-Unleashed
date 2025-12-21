package com.min01.unleashed.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CelestialExplosionParticle extends TextureSheetParticle
{
	private final SpriteSet sprites;
	
	public CelestialExplosionParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet sprites)
	{
		super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
		this.quadSize = 1.0F;
		this.lifetime = 60;
		this.sprites = sprites;
		this.setSpriteFromAge(sprites);
	}
	
	@Override
	public void tick() 
	{
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		this.age += 3;
		if(this.age >= this.lifetime) 
		{
			this.remove();
		} 
		else
		{
			this.quadSize += 0.2F;
		}
		this.setSpriteFromAge(this.sprites);
	}

	@Override
	public ParticleRenderType getRenderType() 
	{
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}
	
	@Override
	protected int getLightColor(float pPartialTick) 
	{
		return LightTexture.FULL_BRIGHT;
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType>
	{
		private final SpriteSet sprites;

		public Provider(SpriteSet sprites) 
		{
			this.sprites = sprites;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) 
		{
			return new CelestialExplosionParticle(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, this.sprites);
		}
	}
}
