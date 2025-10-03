package tech.thatgravyboat.spookymansions.common.registries;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import tech.thatgravyboat.spookymansions.SpookyMansions;
import tech.thatgravyboat.spookymansions.common.components.CameraPicture;

public class ModComponents {

    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(
            Registries.DATA_COMPONENT_TYPE, SpookyMansions.MODID
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CameraPicture>> CAMERA_PICTURE = COMPONENTS.registerComponentType(
            "camera_picture",
            builder -> builder.persistent(CameraPicture.CODEC).networkSynchronized(CameraPicture.NETWORK_CODEC)
    );
}
