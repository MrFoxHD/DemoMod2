package org.zeith.modid.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.zeith.modid.ModId;
import org.zeith.modid.entity.projectile.LightningBallProjectile;

@SimplyRegister
public interface EntityMI {
    @RegistryName("lightning_ball")
    EntityType<LightningBallProjectile> tile = EntityType.Builder.of(LightningBallProjectile::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .build(new ResourceLocation(ModId.MOD_ID, "lightning_ball").toString());
}
