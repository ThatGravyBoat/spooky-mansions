package tech.thatgravyboat.spookymansions.client.trinkets.camera;

import com.mojang.blaze3d.platform.NativeImage;
import it.unimi.dsi.fastutil.bytes.ByteArrayList;
import it.unimi.dsi.fastutil.bytes.ByteList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.util.FastColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import tech.thatgravyboat.spookymansions.common.network.serverbound.ServerboundPicturePacket;

@EventBusSubscriber(Dist.CLIENT)
public class ClientCameraPicture {

    private static final int MAX_SIZE = 64;
    private static boolean shouldTakeScreenshot = false;

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLastRender(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_LEVEL) return;
        if (!shouldTakeScreenshot) return;
        shouldTakeScreenshot = false;
        PacketDistributor.sendToServer(new ServerboundPicturePacket(takeScreenshot()));
    }

    public static void takePicture() {
        shouldTakeScreenshot = true;
    }

    private static ByteList takeScreenshot() {
        var image = Screenshot.takeScreenshot(Minecraft.getInstance().getMainRenderTarget());

        ByteList bytes = new ByteArrayList(MAX_SIZE * MAX_SIZE);

        try (NativeImage resized = new NativeImage(MAX_SIZE, MAX_SIZE, false)) {
            int size = Math.min(image.getWidth(), image.getHeight());
            image.resizeSubRectTo(
                    (image.getWidth() - size) / 2, (image.getHeight() - size) / 2,
                    size, size,
                    resized
            );

            for (int x = 0; x < MAX_SIZE; x++) {
                for (int y = 0; y < MAX_SIZE; y++) {
                    int color = resized.getPixelRGBA(x, y);
                    int grayscale = (int) (
                            FastColor.ABGR32.red(color) * 0.3f +
                            FastColor.ABGR32.green(color) * 0.59f +
                            FastColor.ABGR32.blue(color) * 0.11f
                    );
                    bytes.add((byte) grayscale);
                }
            }
        } finally {
            image.close();
        }
        return bytes;
    }
}
