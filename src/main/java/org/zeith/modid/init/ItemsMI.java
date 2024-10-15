package org.zeith.modid.init;

import net.minecraft.world.item.Item;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.zeith.modid.items.LightningWandItem;

@SimplyRegister
public interface ItemsMI {
    @RegistryName("lightning_wand")
    Item WAND = new LightningWandItem(new Item.Properties().stacksTo(1).durability(5));
    @RegistryName("lightning_ball_item")
    Item LIGHTNING_BALL = new Item(new Item.Properties().stacksTo(1));
}