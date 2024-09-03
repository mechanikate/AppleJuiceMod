package com.applejuice.applejuice.fluids;

import com.applejuice.applejuice.AppleJuice;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import static com.applejuice.applejuice.AppleJuice.*;

public abstract class AppleJuiceFluid extends TutorialFluid {
    @Override
    public Fluid getStill() {
        return STILL_APPLE_JUICE;
    }

    @Override
    public Fluid getFlowing() {
        return FLOWING_APPLE_JUICE;
    }

    @Override
    public Item getBucketItem() {
        return APPLE_JUICE_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return AppleJuice.APPLE_JUICE.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
    }

    public static class Flowing extends AppleJuiceFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        protected boolean isInfinite(World world) {
            return false;
        }

        @Override
        protected int getMaxFlowDistance(WorldView world) {
            return 4;
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }

    public static class Still extends AppleJuiceFluid {
        @Override
        protected boolean isInfinite(World world) {
            return false;
        }

        @Override
        protected int getMaxFlowDistance(WorldView world) {
            return 4;
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }
    }
}