package tech.thatgravyboat.spookytrinkets.common.registries;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredRegister;
import tech.thatgravyboat.spookytrinkets.SpookyTrinkets;
import tech.thatgravyboat.spookytrinkets.common.effects.DrowningEffect;
import tech.thatgravyboat.spookytrinkets.common.effects.ReachingEffect;
import tech.thatgravyboat.spookytrinkets.common.effects.UndeadEffect;

public class ModEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(
            BuiltInRegistries.MOB_EFFECT,
            SpookyTrinkets.MODID
    );

    public static final Holder<MobEffect> DROWNING = EFFECTS.register("drowning", DrowningEffect::new);
    public static final Holder<MobEffect> UNDEAD = EFFECTS.register("undead", UndeadEffect::new);
    public static final Holder<MobEffect> REACHING = EFFECTS.register("reaching", ReachingEffect::new);


}
