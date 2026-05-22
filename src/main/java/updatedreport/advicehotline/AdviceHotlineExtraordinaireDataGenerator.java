package updatedreport.advicehotline;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import updatedreport.advicehotline.datagen.AdviceHotlineBlockLootTableProvider;
import updatedreport.advicehotline.datagen.AdviceHotlineBlockTagsProvider;
import updatedreport.advicehotline.datagen.AdviceHotlineModelProvider;
import updatedreport.advicehotline.datagen.AdviceHotlineRecipeProvider;

public class AdviceHotlineExtraordinaireDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        var pack = fabricDataGenerator.createPack();

        pack.addProvider(AdviceHotlineModelProvider::new);
        pack.addProvider(AdviceHotlineBlockTagsProvider::new);
        pack.addProvider(AdviceHotlineBlockLootTableProvider::new);
        pack.addProvider(AdviceHotlineRecipeProvider::new);
	}
}
