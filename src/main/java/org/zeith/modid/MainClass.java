package org.zeith.modid;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import org.zeith.hammerlib.api.items.CreativeTab;
import org.zeith.hammerlib.core.adapter.LanguageAdapter;
import org.zeith.hammerlib.core.init.ItemsHL;
import org.zeith.hammerlib.proxy.HLConstants;
import org.zeith.modid.init.BlocksInit;
import org.zeith.modid.init.ItemsInit;

@Mod(MainClass.MOD_ID)
public class MainClass {
    public static final String MOD_ID = "demo_mod_2";

    @CreativeTab.RegisterTab
    public static final CreativeTab MOD_TAB = new CreativeTab(id("root"),
            builder -> builder
                    .icon(() -> ItemsHL.COPPER_GEAR.getDefaultInstance())
                    .withTabsBefore(HLConstants.HL_TAB.id())
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ItemsInit.WAND);
                        output.accept(BlocksInit.LIGHTNING_GENERATOR_BLOCK);
                    }))
    );

    public MainClass() {
        LanguageAdapter.registerMod(MOD_ID);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}