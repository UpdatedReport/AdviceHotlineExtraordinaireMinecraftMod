package updatedreport.advicehotline.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.AbstractBannerBlock;
import updatedreport.advicehotline.block.AdviceHotlineBlock;

import java.util.concurrent.CompletableFuture;

public class AdviceHotlineBlockLootTableProvider extends FabricBlockLootSubProvider {
    public AdviceHotlineBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {

        add(AdviceHotlineBlock.EVERYTHING_MAN_TELEPHONE, createOreDrop(AdviceHotlineBlock.EVERYTHING_MAN_TELEPHONE, Items.REDSTONE));

    }
}
