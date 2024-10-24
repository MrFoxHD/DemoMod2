package org.zeith.modid.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.zeith.modid.entity.projectile.LightningBallProjectile;
import org.zeith.modid.init.EntityInit;
import org.zeith.modid.init.ItemsComponentInit;
import org.zeith.modid.init.ItemsInit;

public class LightningWandItem extends Item {
    public LightningWandItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide()) return InteractionResultHolder.success(stack);

        LightningBallProjectile projectile = new LightningBallProjectile(EntityInit.tile, level);
        projectile.setItem(new ItemStack(ItemsComponentInit.LIGHTNING_BALL));
        projectile.setPos(player.getX(), player.getY() + player.getEyeHeight(), player.getZ());

        float speed = 0.5F;

        Vec3 direction = player.getViewVector(1.0F);
        projectile.setDeltaMovement(direction.x * speed, direction.y * speed, direction.z * speed);
        level.addFreshEntity(projectile);

        stack.hurtAndBreak(1, player, living -> living.broadcastBreakEvent(hand));
        return InteractionResultHolder.success(stack);
    }
}
