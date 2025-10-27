package tech.thatgravyboat.spookytrinkets.common.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import tech.thatgravyboat.spookytrinkets.SpookyTrinkets;

public class ReachingEffect extends MobEffect {

    public ReachingEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xB3EBF2);

        this.addAttributeModifier(
                Attributes.BLOCK_INTERACTION_RANGE,
                SpookyTrinkets.id("effect.reaching.block"),
                2,
                AttributeModifier.Operation.ADD_VALUE
        );

        this.addAttributeModifier(
                Attributes.ENTITY_INTERACTION_RANGE,
                SpookyTrinkets.id("effect.reaching.entity"),
                2,
                AttributeModifier.Operation.ADD_VALUE
        );
    }
}
