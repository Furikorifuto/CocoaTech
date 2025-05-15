package com.ib.cocoa_tech.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class WetBrick extends HorizontalDirectionalBlock {
    public static final MapCodec<WetBrick> CODEC = simpleCodec(WetBrick::new);
    protected static final VoxelShape SHAPE_H = Block.box(1.0, 0.0, 4.0, 15.0, 4.0, 12.0);
    protected static final VoxelShape SHAPE_V = Block.box(4.0, 0.0, 1.0, 12.0, 4.0, 15.0);
    public WetBrick(Properties properties) {
        super(properties
                .instabreak()
                .randomTicks()
                .sound(SoundType.FROGLIGHT));
    }
    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return switch(state.getValue(FACING)){
            case NORTH, SOUTH -> SHAPE_V;
            default -> SHAPE_H;
        };
    }
    @Override
    protected @NotNull MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    protected void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if(!canSurvive(state,level,pos)){
            level.destroyBlock(pos,true);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        BlockState blockState = level.getBlockState(pos.below());
        return blockState.isSolidRender();
    }
}
