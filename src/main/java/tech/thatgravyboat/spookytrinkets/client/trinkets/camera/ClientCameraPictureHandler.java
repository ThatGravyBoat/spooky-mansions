package tech.thatgravyboat.spookytrinkets.client.trinkets.camera;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import tech.thatgravyboat.spookytrinkets.SpookyTrinkets;
import tech.thatgravyboat.spookytrinkets.common.components.CameraPicture;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EventBusSubscriber(Dist.CLIENT)
public class ClientCameraPictureHandler {

    private static final Map<UUID, ResourceLocation> CACHE = new HashMap<>();

    public static ResourceLocation get(CameraPicture picture) {
        return CACHE.computeIfAbsent(picture.id(), uuid -> {
            var size = (int) Math.sqrt(picture.data().size());
            if (size * size != picture.data().size()) return null;
            var texture = new DynamicTexture(size, size, false);
            var image = texture.getPixels();
            assert image != null;
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    int grayscale = Byte.toUnsignedInt(picture.data().getByte(x * size + y));
                    image.setPixelRGBA(x, y, FastColor.ARGB32.color(0xFF, grayscale, grayscale, grayscale));
                }
            }
            texture.upload();

            var location = SpookyTrinkets.id("camera/" + picture.id());
            Minecraft.getInstance().getTextureManager().register(location, texture);
            return location;
        });
    }

    @SubscribeEvent
    public static void onDisconnect(ClientPlayerNetworkEvent.LoggingOut event) {
        for (var value : CACHE.values()) {
            Minecraft.getInstance().getTextureManager().release(value);
        }
        CACHE.clear();
    }
}
