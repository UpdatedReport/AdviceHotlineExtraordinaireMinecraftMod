package updatedreport.advicehotline.item.custom;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import updatedreport.advicehotline.block.AdviceHotlineBlock;

public class AdviceHotlineEverythingManCoinItem extends Item {
    public AdviceHotlineEverythingManCoinItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();
        Player player = context.getPlayer();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(clickedBlock == AdviceHotlineBlock.EVERYTHING_MAN_TELEPHONE && !level.isClientSide()) {

            context.getItemInHand().shrink(1);

            ItemStack compass = new ItemStack(Items.COMPASS);
            player.addItem(compass);

        }
        return InteractionResult.SUCCESS;
    }
}
