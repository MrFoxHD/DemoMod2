package org.zeith.modid.init;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.zeith.hammerlib.annotations.Ref;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.zeith.modid.MainClass;
import org.zeith.modid.blocks.LightningGeneratorBlock;

@SimplyRegister(creativeTabs = @Ref(value = MainClass.class, field = "MOD_TAB"))
public interface BlocksInit {
    @RegistryName("lightning_generator")
    LightningGeneratorBlock LIGHTNING_GENERATOR_BLOCK = new LightningGeneratorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK));
}
