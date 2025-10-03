package tech.thatgravyboat.spookymansions.common.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import tech.thatgravyboat.spookymansions.common.network.serverbound.ServerboundPicturePacket;

@EventBusSubscriber
public class ModNetwork {

    private static final String PROTOCOL_VERSION = "1";

    @SubscribeEvent
    public static void onRegisterPayloads(RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar(PROTOCOL_VERSION);

        registrar.playToServer(
                ServerboundPicturePacket.TYPE,
                ServerboundPicturePacket.CODEC,
                ServerboundPicturePacket::handle
        );
    }
}
