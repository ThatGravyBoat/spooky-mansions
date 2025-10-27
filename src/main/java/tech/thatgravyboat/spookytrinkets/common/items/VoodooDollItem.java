package tech.thatgravyboat.spookytrinkets.common.items;

import net.minecraft.Optionull;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import tech.thatgravyboat.spookytrinkets.common.registries.ModComponents;

public class VoodooDollItem extends Item {

    public VoodooDollItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        stack.set(ModComponents.ATTACHED_ENTITY, target.getUUID());
        player.setItemInHand(hand, stack);
        return InteractionResult.sidedSuccess(player.level().isClientSide());
    }

    public static void onVoodooDollDamaged(EntityInvulnerabilityCheckEvent event) {
        if (!(event.getEntity() instanceof ItemEntity itemEntity)) return;
        if (!(itemEntity.getItem().getItem() instanceof VoodooDollItem)) return;
        if (!(event.getEntity().level() instanceof ServerLevel level)) return;

        var stack = itemEntity.getItem();
        var attached = Optionull.map(stack.get(ModComponents.ATTACHED_ENTITY), level::getEntity);
        var source = event.getSource();
        if (!(attached instanceof LivingEntity entity) || !attached.isAlive()) {
            event.setInvulnerable(false);
        } else {

            if (source.is(DamageTypeTags.IS_FIRE)) {
                entity.setRemainingFireTicks(entity.getRemainingFireTicks() + 20);
                entity.hurt(source, 1f);
            } else if (source.is(DamageTypeTags.IS_EXPLOSION) || source.is(DamageTypes.FELL_OUT_OF_WORLD) || source.is(DamageTypes.OUTSIDE_BORDER)) {
                entity.hurt(source, 5f);
            } else {
                return;
            }

            event.setInvulnerable(true);
        }
    }
}
