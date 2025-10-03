package tech.thatgravyboat.spookymansions;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import tech.thatgravyboat.spookymansions.common.registries.ModComponents;
import tech.thatgravyboat.spookymansions.common.registries.ModItems;

@Mod(SpookyMansions.MODID)
public class SpookyMansions {
    public static final String MODID = "spookymansions";

    public SpookyMansions(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.ITEMS.register(modEventBus);
        ModComponents.COMPONENTS.register(modEventBus);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
