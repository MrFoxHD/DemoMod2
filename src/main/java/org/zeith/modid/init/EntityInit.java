package org.zeith.modid.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.zeith.hammerlib.api.forge.BlockAPI;
import org.zeith.modid.MainClass;
import org.zeith.modid.entity.block.LightningGeneratorEntity;
import org.zeith.modid.entity.projectile.LightningBallProjectile;

@SimplyRegister
public interface EntityInit {
    @RegistryName("lightning_ball")
    EntityType<LightningBallProjectile> tile = EntityType.Builder.of(LightningBallProjectile::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .build(new ResourceLocation(MainClass.MOD_ID, "lightning_ball").toString());

    @RegistryName("lightning_generator_entity")
    BlockEntityType<LightningGeneratorEntity> LIGHTNING_GENERATOR_ENTITY = BlockAPI.createBlockEntityType(LightningGeneratorEntity::new, BlocksInit.LIGHTNING_GENERATOR_BLOCK);
}
