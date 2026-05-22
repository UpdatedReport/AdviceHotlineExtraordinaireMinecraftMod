package updatedreport.advicehotline.datagen;

import com.ibm.icu.util.Output;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.ItemLike;
import updatedreport.advicehotline.AdviceHotlineExtraordinaire;
import updatedreport.advicehotline.block.AdviceHotlineBlock;
import updatedreport.advicehotline.item.AdviceHotlineItem;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AdviceHotlineRecipeProvider extends FabricRecipeProvider {

    public AdviceHotlineRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {

                List<ItemLike> ADVICE_HOTLINE_COIN_SMELTING = List.of(AdviceHotlineItem.EVERYTHING_MAN_COIN);
                List<ItemLike> ADVICE_HOTLINE_PHONE_SMELTING = List.of(AdviceHotlineBlock.EVERYTHING_MAN_TELEPHONE);

                oreSmelting(ADVICE_HOTLINE_PHONE_SMELTING, RecipeCategory.MISC, CookingBookCategory.BLOCKS, AdviceHotlineItem.EVERYTHING_MAN_COIN, 25,200, "telephone");
                oreBlasting(ADVICE_HOTLINE_PHONE_SMELTING, RecipeCategory.MISC, CookingBookCategory.BLOCKS, AdviceHotlineItem.EVERYTHING_MAN_COIN, 25,100, "telephone");

                oreSmelting(ADVICE_HOTLINE_COIN_SMELTING, RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.IRON_NUGGET, 100,200, "coin");
                oreBlasting(ADVICE_HOTLINE_COIN_SMELTING, RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.IRON_NUGGET, 100,100, "coin");

                shaped(RecipeCategory.MISC, AdviceHotlineBlock.EVERYTHING_MAN_TELEPHONE)
                        .pattern("INI")
                        .pattern("IRI")
                        .pattern("III")
                        .define('N', Items.NETHERITE_INGOT)
                        .define('I', Items.IRON_BLOCK)
                        .define('R', Items.REDSTONE_BLOCK)
                        .unlockedBy(getHasName(AdviceHotlineItem.EVERYTHING_MAN_COIN), has(AdviceHotlineItem.EVERYTHING_MAN_COIN))
                        .save(recipeOutput);

                shapeless(RecipeCategory.MISC, AdviceHotlineItem.EVERYTHING_MAN_COIN)
                        .requires(Items.IRON_INGOT)
                        .requires(Items.EMERALD)
                        .unlockedBy(getHasName((Items.IRON_ORE)), has(Items.IRON_ORE))
                        .save(recipeOutput);

            }
        };
    }

    @Override
    public String getName() {
        return "Advice Hotline Extraordinaire Recipes";
    }
}
