package updatedreport.advicehotline.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import updatedreport.advicehotline.block.AdviceHotlineBlock;

import java.util.Optional;

public class AdviceHotlineEverythingManCoinItem extends Item {
    public AdviceHotlineEverythingManCoinItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();
        Player player = context.getPlayer();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(clickedBlock == AdviceHotlineBlock.EVERYTHING_MAN_TELEPHONE) {

            if (level.isClientSide()) {
                return InteractionResult.SUCCESS;
            }

            ServerLevel serverLevel = (ServerLevel) level;

            BlockPos structPos = null;


            //FIND THE NEAREST STRUCTURE WITHIN 1000 BLOCKS
            structPos = serverLevel.findNearestMapStructure(
                    StructureTags.VILLAGE,
                    player.blockPosition(),
                    1000,
                    false
                );


            if (structPos != null) {
            //GIVE A MAP TO FIND LOCATION

                ItemStack map = MapItem.create(
                        serverLevel,
                        structPos.getX(),
                        structPos.getY(),
                        (byte) 2,
                        true,
                        true
                );

                MapItem.renderBiomePreviewMap(serverLevel, map);

                MapItemSavedData.addTargetDecoration(
                        map,
                        structPos,
                        "+",
                        MapDecorationTypes.RED_X
                );

                map.set(
                        DataComponents.CUSTOM_NAME,
                        (Component.literal("The Everything Man's Directions"))
                );

                player.addItem(map);
            }

        }
        return InteractionResult.SUCCESS;
    }
}
