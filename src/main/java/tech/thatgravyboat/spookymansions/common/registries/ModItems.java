package tech.thatgravyboat.spookymansions.common.registries;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import tech.thatgravyboat.spookymansions.SpookyMansions;
import tech.thatgravyboat.spookymansions.common.items.CameraItem;
import tech.thatgravyboat.spookymansions.common.items.PolaroidItem;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(
            SpookyMansions.MODID
    );

    public static final Holder<Item> CAMERA = ITEMS.register("camera", () -> new CameraItem(new Item.Properties().durability(5)));
    public static final Holder<Item> POLAROID = ITEMS.register("polaroid", () -> new PolaroidItem(new Item.Properties().stacksTo(1)));


}
