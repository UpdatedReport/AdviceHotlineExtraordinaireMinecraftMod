package updatedreport.advicehotline.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
            //GIVE A METHOD TO FIND STRUCT
                serverLevel.setBlockAndUpdate(
                        structPos,
                        Blocks.LODESTONE.defaultBlockState()
                );

                context.getItemInHand().shrink(1);

                ItemStack compass = new ItemStack(Items.COMPASS);

                compass.set(
                        DataComponents.LODESTONE_TRACKER,
                        new LodestoneTracker(
                                Optional.of(
                                        GlobalPos.of(
                                                serverLevel.dimension(),
                                                structPos
                                        )
                                ),
                                true
                        )
                );

                player.addItem(compass);
            }

        }
        return InteractionResult.SUCCESS;
    }
}
