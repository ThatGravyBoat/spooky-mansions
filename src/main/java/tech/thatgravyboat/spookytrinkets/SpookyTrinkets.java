package tech.thatgravyboat.spookytrinkets;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import tech.thatgravyboat.spookytrinkets.common.items.VoodooDollItem;
import tech.thatgravyboat.spookytrinkets.common.registries.ModComponents;
import tech.thatgravyboat.spookytrinkets.common.registries.ModEffects;
import tech.thatgravyboat.spookytrinkets.common.registries.ModItems;

@Mod(SpookyTrinkets.MODID)
public class SpookyTrinkets {
    public static final String MODID = "spookytrinkets";

    public SpookyTrinkets(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.ITEMS.register(modEventBus);
        ModComponents.COMPONENTS.register(modEventBus);
        ModEffects.EFFECTS.register(modEventBus);

        NeoForge.EVENT_BUS.addListener(VoodooDollItem::onVoodooDollDamaged);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
