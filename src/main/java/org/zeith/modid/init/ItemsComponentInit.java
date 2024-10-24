package org.zeith.modid.init;

import net.minecraft.world.item.Item;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface ItemsComponentInit {
    @RegistryName("lightning_ball_item")
    Item LIGHTNING_BALL = new Item(new Item.Properties().stacksTo(1));
}
