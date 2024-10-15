package org.zeith.modid.entity.projectile;

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
    protected float getGravity() {
        return 0.0F;
    }
}
