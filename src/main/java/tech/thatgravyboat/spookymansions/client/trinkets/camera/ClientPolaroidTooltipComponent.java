package tech.thatgravyboat.spookymansions.client.trinkets.camera;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import org.jetbrains.annotations.NotNull;
import tech.thatgravyboat.spookymansions.SpookyMansions;
import tech.thatgravyboat.spookymansions.common.components.CameraPicture;
import tech.thatgravyboat.spookymansions.common.items.PolaroidItem;

@EventBusSubscriber(Dist.CLIENT)
public class ClientPolaroidTooltipComponent implements ClientTooltipComponent {

    private static final ResourceLocation FRAME_SPRITE = SpookyMansions.id("frame");
    private static final int SIZE = 64;

    private final ResourceLocation image;

    public ClientPolaroidTooltipComponent(CameraPicture picture) {
        this.image = ClientCameraPictureHandler.get(picture);
    }

    @Override
    public void renderImage(@NotNull Font font, int x, int y, @NotNull GuiGraphics graphics) {
        graphics.blit(this.image, x, y, 0, 0, SIZE, SIZE, SIZE, SIZE);
        graphics.blitSprite(FRAME_SPRITE, x, y, SIZE, SIZE);
    }

    @Override
    public int getHeight() {
        return SIZE + 2;
    }

    @Override
    public int getWidth(@NotNull Font font) {
        return SIZE;
    }

    @SubscribeEvent
    public static void onRegisterTooltips(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(PolaroidItem.PolaroidTooltip.class, tooltip -> new ClientPolaroidTooltipComponent(tooltip.picture()));
    }
}
