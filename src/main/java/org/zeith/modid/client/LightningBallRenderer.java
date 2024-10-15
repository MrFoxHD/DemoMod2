package org.zeith.modid.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.level.Level;

import java.util.Random;

public class LightningBallRenderer<T extends Entity & ItemSupplier> extends ThrownItemRenderer<T> {
    public LightningBallRenderer(EntityRendererProvider.Context context) {
        super(context, 1.0F, false);
    }

    @Override
    public void render(T projectile, float p_116086_, float p_116087_, PoseStack p_116088_, MultiBufferSource p_116089_, int p_116090_) {
        super.render(projectile, p_116086_, p_116087_, p_116088_, p_116089_, p_116090_);
        Level level = projectile.level();
        Random rand = new Random();
        float randomFloat = -0.3f + rand.nextFloat() * 0.6F;
        level.addParticle(ParticleTypes.ELECTRIC_SPARK, projectile.getX() + randomFloat, projectile.getY() + randomFloat, projectile.getZ() + randomFloat, 0.0D, 0.0D, 0.0D);
    }
}
