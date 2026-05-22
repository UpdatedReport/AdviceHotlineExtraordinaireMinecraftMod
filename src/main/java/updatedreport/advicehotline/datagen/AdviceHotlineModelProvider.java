package updatedreport.advicehotline.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import updatedreport.advicehotline.block.AdviceHotlineBlock;
import updatedreport.advicehotline.item.AdviceHotlineItem;

public class AdviceHotlineModelProvider extends FabricModelProvider {

    public AdviceHotlineModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube((AdviceHotlineBlock.EVERYTHING_MAN_TELEPHONE));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(AdviceHotlineItem.EVERYTHING_MAN_COIN, ModelTemplates.FLAT_ITEM);
    }
}
