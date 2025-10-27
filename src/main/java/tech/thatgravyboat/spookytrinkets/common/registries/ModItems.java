package tech.thatgravyboat.spookytrinkets.common.registries;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import tech.thatgravyboat.spookytrinkets.SpookyTrinkets;
import tech.thatgravyboat.spookytrinkets.common.items.CameraItem;
import tech.thatgravyboat.spookytrinkets.common.items.PolaroidItem;
import tech.thatgravyboat.spookytrinkets.common.items.VoodooDollItem;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(
            SpookyTrinkets.MODID
    );

    public static final Holder<Item> CAMERA = ITEMS.register("camera", () -> new CameraItem(new Item.Properties().durability(5)));
    public static final Holder<Item> POLAROID = ITEMS.register("polaroid", () -> new PolaroidItem(new Item.Properties().stacksTo(1)));
    public static final Holder<Item> VOODOO_DOLL = ITEMS.register("voodoo_doll", () -> new VoodooDollItem(new Item.Properties().stacksTo(1)));
    public static final Holder<Item> DEVIL_FRUIT = ITEMS.register("devil_fruit", () -> new Item(new Item.Properties().stacksTo(1)));


}
