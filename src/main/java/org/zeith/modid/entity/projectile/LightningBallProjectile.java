package org.zeith.modid.entity.projectile;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.zeith.modid.init.ItemsMI;

import java.util.Random;

public class LightningBallProjectile extends ThrowableItemProjectile {
    public LightningBallProjectile(EntityType<? extends ThrowableItemProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemsMI.LIGHTNING_BALL;
    }

    @Override
    protected void onHit(HitResult hitResult) {
        thunderHit(hitResult.getLocation());
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
    }

    private void thunderHit(Vec3 location) {
        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, this.level());
        lightningBolt.setPos(location);

        this.level().addFreshEntity(lightningBolt);
    }

    @Override
    public void tick() {
        super.tick();

        Level level = this.level();
        if (!level.isClientSide()) return;
        float f1 = -0.3F + random.nextFloat() * 0.6F;
        float f2 = -0.3F + random.nextFloat() * 0.6F;
        float f3 = -0.3F + random.nextFloat() * 0.6F;
        level.addParticle(ParticleTypes.ELECTRIC_SPARK, this.getX() + f1, this.getY() + f2, this.getZ() + f3, 0.0D, 0.0D, 0.0D);
    }

    @Override
    protected float getGravity() {
        return 0.0F;
    }
}
