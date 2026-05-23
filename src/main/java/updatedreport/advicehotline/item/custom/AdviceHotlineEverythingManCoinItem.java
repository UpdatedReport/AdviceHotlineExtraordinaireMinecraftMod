package updatedreport.advicehotline.item.custom;

import net.minecraft.ChatFormatting;
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

import static java.lang.Thread.yield;

public class AdviceHotlineEverythingManCoinItem extends Item {
    public AdviceHotlineEverythingManCoinItem(Properties properties) {
        super(properties);
    }

    private BlockPos getRandomPosition(ServerLevel level, Player player, int radius) {

        BlockPos origin = player.blockPosition();

        int xOffset = level.getRandom().nextInt(radius * 2) - radius;
        int zOffset = level.getRandom().nextInt(radius * 2) - radius;

        int x = origin.getX() + xOffset;
        int z = origin.getZ() + zOffset;

        int y = level.getHeight(
                net.minecraft.world.level.levelgen.Heightmap.Types.WORLD_SURFACE,
                x,
                z
        );

        return new BlockPos(x, y, z);
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

            BlockPos structPos;
            var structType = 1;


            var EverythingMansDirection = level.getRandom().nextInt(100) + 1;

            //FIND THE NEAREST STRUCTURE WITHIN 1000 BLOCKS
            if (EverythingMansDirection == 100) {

                structPos = getRandomPosition(serverLevel, player, 1000);
                structType = 2;

            } else if (EverythingMansDirection <= 50) {

                structPos = serverLevel.findNearestMapStructure(
                        StructureTags.VILLAGE,
                        player.blockPosition(),
                        10000,
                        false
                );
                structType = 3;

            } else if (EverythingMansDirection <= 70) {

                structPos = serverLevel.findNearestMapStructure(
                        StructureTags.SHIPWRECK,
                        player.blockPosition(),
                        10000,
                        false
                );
                structType = 4;

            } else if (EverythingMansDirection <= 85) {

                structPos = serverLevel.findNearestMapStructure(
                        StructureTags.EYE_OF_ENDER_LOCATED,
                        player.blockPosition(),
                        10000,
                        false
                );
                structType = 5;

            } else {

                structPos = serverLevel.findNearestMapStructure(
                        StructureTags.ON_TREASURE_MAPS,
                        player.blockPosition(),
                        10000,
                        false
                );
                structType = 6;
            }

            Component message = Component.literal("This message should not appear.");

            if (structPos != null) {

                switch (structType){

                    case 1 -> message = Component.literal("<The Everything Man> There's nothing interesting nearby...")
                            .withStyle(ChatFormatting.WHITE);

                    case 2 -> message = Component.literal("<The Everything Man> You want to go somewhere fun?")
                            .withStyle(ChatFormatting.WHITE);

                    case 3 -> message = Component.literal("<The Everything Man> I believe you can find a village here...")
                            .withStyle(ChatFormatting.WHITE);

                    case 4 -> message = Component.literal("<The Everything Man> A ship went down over here...")
                            .withStyle(ChatFormatting.WHITE);

                    case 5 -> message = Component.literal("<The Everything Man> The Ender Dragon is hidden away here...")
                            .withStyle(ChatFormatting.WHITE);

                    case 6 -> message = Component.literal("<The Everything Man> There's a lot of treasure here...")
                            .withStyle(ChatFormatting.WHITE);
                }

                player.sendSystemMessage(message);

            //GIVE A MAP TO FIND LOCATION

                ItemStack map = MapItem.create(
                        serverLevel,
                        structPos.getX(),
                        structPos.getZ(),
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
