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
	protected final SpriteSet sprites;
	public CelestialExplosionParticle(ClientLevel p_108328_, double p_108329_, double p_108330_, double p_108331_, double p_108332_, double p_108333_, double p_108334_, SpriteSet sprites)
	{
		super(p_108328_, p_108329_, p_108330_, p_108331_, p_108332_, p_108333_, p_108334_);
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
	protected int getLightColor(float p_107249_) 
	{
		return LightTexture.FULL_BRIGHT;
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType>
	{
		private final SpriteSet sprites;

		public Provider(SpriteSet p_106555_) 
		{
			this.sprites = p_106555_;
		}
		
		@Override
		public Particle createParticle(SimpleParticleType p_107421_, ClientLevel p_107422_, double p_107423_, double p_107424_, double p_107425_, double p_107426_, double p_107427_, double p_107428_) 
		{
			return new CelestialExplosionParticle(p_107422_, p_107423_, p_107424_, p_107425_, p_107426_, p_107427_, p_107428_, this.sprites);
		}
	}
}
