package tech.thatgravyboat.spookymansions.common.network.serverbound;

import it.unimi.dsi.fastutil.bytes.ByteArrayList;
import it.unimi.dsi.fastutil.bytes.ByteList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.spookymansions.SpookyMansions;
import tech.thatgravyboat.spookymansions.common.components.CameraPicture;
import tech.thatgravyboat.spookymansions.common.registries.ModComponents;
import tech.thatgravyboat.spookymansions.common.registries.ModItems;

import java.util.UUID;

public record ServerboundPicturePacket(ByteList data) implements CustomPacketPayload {

    public static final Type<ServerboundPicturePacket> TYPE = new Type<>(SpookyMansions.id("picture"));
    public static final StreamCodec<FriendlyByteBuf, ServerboundPicturePacket> CODEC = StreamCodec.of(
            (buf, packet) ->
                    buf.writeCollection(packet.data, FriendlyByteBuf::writeByte),
            (buf) -> new ServerboundPicturePacket(
                    buf.readCollection(FriendlyByteBuf.limitValue(ByteArrayList::new, CameraPicture.MAX_SIZE), FriendlyByteBuf::readByte)
            )
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ServerboundPicturePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            var stack = new ItemStack(ModItems.POLAROID);
            stack.set(ModComponents.CAMERA_PICTURE, new CameraPicture(UUID.randomUUID(), packet.data()));
            stack.set(DataComponents.ITEM_NAME, Component.literal("Developed Polaroid"));
            context.player().addItem(stack);
        });
    }
}
