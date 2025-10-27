package tech.thatgravyboat.spookytrinkets.common.registries;

import net.minecraft.core.UUIDUtil;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import tech.thatgravyboat.spookytrinkets.SpookyTrinkets;
import tech.thatgravyboat.spookytrinkets.common.components.CameraPicture;

import java.util.UUID;

public class ModComponents {

    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(
            Registries.DATA_COMPONENT_TYPE, SpookyTrinkets.MODID
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<CameraPicture>> CAMERA_PICTURE = COMPONENTS.registerComponentType(
            "camera_picture",
            builder -> builder.persistent(CameraPicture.CODEC).networkSynchronized(CameraPicture.NETWORK_CODEC)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<UUID>> ATTACHED_ENTITY = COMPONENTS.registerComponentType(
            "attached_entity",
            builder -> builder.persistent(UUIDUtil.LENIENT_CODEC).networkSynchronized(UUIDUtil.STREAM_CODEC)
    );
}
