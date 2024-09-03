package com.applejuice.applejuice;

import com.applejuice.applejuice.fluids.AppleJuiceFluid;
import com.applejuice.applejuice.item.AppleJuiceBottle;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import static com.applejuice.applejuice.item.ModItems.register;

public class AppleJuice implements ModInitializer {
    public static FlowableFluid STILL_APPLE_JUICE;
    public static FlowableFluid FLOWING_APPLE_JUICE;
    public static Item APPLE_JUICE_BUCKET;
    public static Block APPLE_JUICE;
    public static String MOD_ID = "applejuice";
    public static final Item APPLE_JUICE_BOTTLE = register(
            new AppleJuiceBottle(new Item.Settings().food(new FoodComponent.Builder().alwaysEdible().nutrition(2).usingConvertsTo(Items.GLASS_BOTTLE).build())),
            "apple_juice_bottle"
    );
    @Override
    public void onInitialize() {
        STILL_APPLE_JUICE = Registry.register(Registries.FLUID, Identifier.of("applejuice", "apple_juice"), new AppleJuiceFluid.Still());
        FLOWING_APPLE_JUICE = Registry.register(Registries.FLUID, Identifier.of("applejuice", "flowing_apple_juice"), new AppleJuiceFluid.Flowing());
        APPLE_JUICE_BUCKET = Registry.register(Registries.ITEM, Identifier.of("applejuice", "apple_juice_bucket"),
                new BucketItem(STILL_APPLE_JUICE, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
        APPLE_JUICE = Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, "apple_juice"), new FluidBlock(STILL_APPLE_JUICE, FabricBlockSettings.copy(Blocks.WATER)) {});

    }
}
