package tech.thatgravyboat.spookymansions.common.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import tech.thatgravyboat.spookymansions.client.trinkets.camera.ClientCameraPicture;

public class CameraItem extends Item {

    public CameraItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        var stack = player.getItemInHand(hand);
        stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));

        if (level.isClientSide()) {
            ClientCameraPicture.takePicture();
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
