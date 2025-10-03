package tech.thatgravyboat.spookymansions.common.utils;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import it.unimi.dsi.fastutil.bytes.ByteArrayList;
import it.unimi.dsi.fastutil.bytes.ByteImmutableList;
import it.unimi.dsi.fastutil.bytes.ByteList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ModCodecs {

    public static final Codec<ByteList> COMPRESSED_BYTE_LIST = Codec.STRING.flatXmap(
            it -> {
                var bytes = Base64.getDecoder().decode(it);
                try (var gzip = new GZIPInputStream(new ByteArrayInputStream(bytes))) {
                    return DataResult.success(new ByteImmutableList(gzip.readAllBytes()));
                } catch (Exception e) {
                    return DataResult.error(e::getMessage);
                }
            },
            it -> {
                var output = new ByteArrayOutputStream();
                try (var gzip = new GZIPOutputStream(output)) {
                    gzip.write(it.toByteArray());
                    gzip.finish();
                } catch (Exception e) {
                    return DataResult.error(e::getMessage);
                }
                return DataResult.success(Base64.getEncoder().encodeToString(output.toByteArray()));
            }
    );

    public static final StreamCodec<FriendlyByteBuf, ByteList> BYTE_LIST_NETWORK_CODEC = StreamCodec.of(
            (buf, list) -> buf.writeCollection(list, FriendlyByteBuf::writeByte),
            buf -> buf.readCollection(ByteArrayList::new, FriendlyByteBuf::readByte)
    );

}
