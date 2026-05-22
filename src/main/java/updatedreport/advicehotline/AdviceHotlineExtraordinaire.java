package updatedreport.advicehotline;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import updatedreport.advicehotline.item.AdviceHotlineItem;

public class AdviceHotlineExtraordinaire implements ModInitializer {
	public static final String MOD_ID = "advicehotline";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        AdviceHotlineItem.registerModItems();
		LOGGER.info("Hello Fabric world!");
	}
}