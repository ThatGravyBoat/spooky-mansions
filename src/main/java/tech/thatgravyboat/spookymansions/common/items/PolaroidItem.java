package tech.thatgravyboat.spookymansions.common.items;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import tech.thatgravyboat.spookymansions.common.components.CameraPicture;
import tech.thatgravyboat.spookymansions.common.registries.ModComponents;

import java.util.Optional;

public class PolaroidItem extends Item {

    public PolaroidItem(Properties properties) {
        super(properties);
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        var picture = stack.get(ModComponents.CAMERA_PICTURE);
        return picture != null ? Optional.of(new PolaroidTooltip(picture)) : Optional.empty();
    }

    public record PolaroidTooltip(CameraPicture picture) implements TooltipComponent {
    }
}
