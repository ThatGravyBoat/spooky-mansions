package tech.thatgravyboat.spookytrinkets.common.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.spookytrinkets.SpookyTrinkets;

public class DrowningEffect extends MobEffect {

    public DrowningEffect() {
        super(MobEffectCategory.HARMFUL, 10017472);

        this.addAttributeModifier(
                NeoForgeMod.SWIM_SPEED,
                SpookyTrinkets.id("effect.drowning"),
                -1f, // -1.0f to completely stop swimming
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity.isInWater()) {
            entity.hurt(entity.level().damageSources().drown(), 2.0F);
        }
        return true;
    }
}
