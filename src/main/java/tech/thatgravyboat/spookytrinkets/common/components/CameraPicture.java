package tech.thatgravyboat.spookytrinkets.common.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.bytes.ByteImmutableList;
import it.unimi.dsi.fastutil.bytes.ByteList;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import tech.thatgravyboat.spookytrinkets.common.utils.ModCodecs;

import java.util.UUID;

public record CameraPicture(
        UUID id,
        ByteList data
) {

    public static final int MAX_SIZE = 64 * 64; // 4096 bytes

    public static final Codec<CameraPicture> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            UUIDUtil.LENIENT_CODEC.fieldOf("id").forGetter(CameraPicture::id),
            ModCodecs.COMPRESSED_BYTE_LIST.fieldOf("data").forGetter(CameraPicture::data)
    ).apply(instance, CameraPicture::new));

    public static final StreamCodec<FriendlyByteBuf, CameraPicture> NETWORK_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, CameraPicture::id,
            ModCodecs.BYTE_LIST_NETWORK_CODEC, CameraPicture::data,
            CameraPicture::new
    );

    public CameraPicture(UUID id, ByteList data) {
        this.id = id;
        this.data = new ByteImmutableList(data);
    }
}
