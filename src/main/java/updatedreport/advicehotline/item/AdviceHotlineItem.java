package updatedreport.advicehotline.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import updatedreport.advicehotline.AdviceHotlineExtraordinaire;

import java.util.function.Function;

public class AdviceHotlineItem {
    public static final Item EVERYTHING_MAN_COIN = registerItem("everything_man_coin", Item::new);


    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(AdviceHotlineExtraordinaire.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AdviceHotlineExtraordinaire.MOD_ID, name)))));
    }

    public static void registerModItems() {
        AdviceHotlineExtraordinaire.LOGGER.info("Registering mod items for " + AdviceHotlineExtraordinaire.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register( output -> {
            output.accept(EVERYTHING_MAN_COIN);
        });

    }

}
