package updatedreport.advicehotline.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import updatedreport.advicehotline.block.AdviceHotlineBlock;

import java.util.concurrent.CompletableFuture;

public class AdviceHotlineBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {

    public AdviceHotlineBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(AdviceHotlineBlock.EVERYTHING_MAN_TELEPHONE);

        valueLookupBuilder(BlockTags.GUARDED_BY_PIGLINS)
                .add(AdviceHotlineBlock.EVERYTHING_MAN_TELEPHONE);

        valueLookupBuilder(BlockTags.INFINIBURN_OVERWORLD)
                .add(AdviceHotlineBlock.EVERYTHING_MAN_TELEPHONE);

        valueLookupBuilder(BlockTags.INFINIBURN_NETHER)
                .add(AdviceHotlineBlock.EVERYTHING_MAN_TELEPHONE);

        valueLookupBuilder(BlockTags.INFINIBURN_END)
                .add(AdviceHotlineBlock.EVERYTHING_MAN_TELEPHONE);
    }


}
