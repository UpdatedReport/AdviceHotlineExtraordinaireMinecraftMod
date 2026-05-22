package updatedreport.advicehotline.block;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import updatedreport.advicehotline.AdviceHotlineExtraordinaire;

import java.util.function.Function;

public class AdviceHotlineBlock {

    public static final Block EVERYTHING_MAN_TELEPHONE = registerBlock("everything_man_telephone",
            properties -> new Block(properties.strength(20f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.IRON)));

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply((BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(AdviceHotlineExtraordinaire.MOD_ID, name)))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(AdviceHotlineExtraordinaire.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(AdviceHotlineExtraordinaire.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AdviceHotlineExtraordinaire.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        AdviceHotlineExtraordinaire.LOGGER.info("Registering Mod Blocks for " + AdviceHotlineExtraordinaire.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(output -> {
            output.accept(EVERYTHING_MAN_TELEPHONE);
        });
    }
}
