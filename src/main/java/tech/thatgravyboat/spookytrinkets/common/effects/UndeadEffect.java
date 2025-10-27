package tech.thatgravyboat.spookytrinkets.common.effects;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class UndeadEffect extends MobEffect {

    public UndeadEffect() {
        super(MobEffectCategory.HARMFUL, 0xFF746C);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (isSunBurnTick(entity)) {
            entity.igniteForSeconds(8.0F);
        }
        return true;
    }

    private boolean isSunBurnTick(@NotNull LivingEntity entity) {
        if (entity.level().isDay() && !entity.level().isClientSide) {
            float f = entity.getLightLevelDependentMagicValue();
            BlockPos blockpos = BlockPos.containing(entity.getX(), entity.getEyeY(), entity.getZ());
            boolean flag = entity.isInWaterRainOrBubble() || entity.isInPowderSnow || entity.wasInPowderSnow;
            return f > 0.5F && entity.getRandom().nextFloat() * 30.0F < (f - 0.4F) * 2.0F && !flag && entity.level().canSeeSky(blockpos);
        }
        return false;
    }
}
