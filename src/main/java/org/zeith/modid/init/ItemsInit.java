package org.zeith.modid.init;

import net.minecraft.world.item.Item;
import org.zeith.hammerlib.annotations.Ref;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.zeith.modid.MainClass;
import org.zeith.modid.items.LightningWandItem;

@SimplyRegister(creativeTabs = @Ref(value = MainClass.class, field = "MOD_TAB"))
public interface ItemsInit {
    @RegistryName("lightning_wand")
    Item WAND = new LightningWandItem(new Item.Properties().stacksTo(1).durability(5));
}