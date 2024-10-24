package org.zeith.modid.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.zeith.hammerlib.api.forge.BlockAPI;
import org.zeith.modid.entity.block.LightningGeneratorEntity;
import org.zeith.modid.init.EntityInit;

import javax.annotation.Nullable;

public class LightningGeneratorBlock extends BaseEntityBlock {
    public LightningGeneratorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(HorizontalDirectionalBlock.FACING);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState newState, boolean something) {
        BlockEntity generatorTile = level.getBlockEntity(pos);
        if (generatorTile instanceof LightningGeneratorEntity lightningGenerator) {
            lightningGenerator.updateConnected();
        }
        super.onPlace(state, level, pos, newState, something);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
        BlockEntity generatorTile = level.getBlockEntity(pos);
        if (generatorTile instanceof LightningGeneratorEntity lightningGenerator) {
            lightningGenerator.updateConnected();
        }
        return super.updateShape(state, direction, newState, level, pos, facingPos);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof LightningGeneratorEntity lightningGenerator)) return InteractionResult.PASS;

        int energyStored = lightningGenerator.getEnergyStorage().getEnergyStored();
        player.sendSystemMessage(Component.translatable("§7Stored energy: §a" + energyStored + " §fFE"));
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        return BlockAPI.ticker(level);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new LightningGeneratorEntity(EntityInit.LIGHTNING_GENERATOR_ENTITY, pos, state);
    }
}
