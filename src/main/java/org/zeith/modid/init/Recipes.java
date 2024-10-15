package org.zeith.modid.init;

import net.minecraft.world.item.Items;
import org.zeith.hammerlib.annotations.ProvideRecipes;
import org.zeith.hammerlib.api.IRecipeProvider;
import org.zeith.hammerlib.event.recipe.RegisterRecipesEvent;

@ProvideRecipes
public class Recipes implements IRecipeProvider {
    @Override
    public void provideRecipes(RegisterRecipesEvent event) {
        event.shaped()
                .result(ItemsMI.WAND)
                .shape("gsg", " o ", " g ")
                .map('g', Items.GOLD_INGOT)
                .map('o', Items.OBSIDIAN)
                .map('s', Items.NETHER_STAR)
                .register();
    }
}
