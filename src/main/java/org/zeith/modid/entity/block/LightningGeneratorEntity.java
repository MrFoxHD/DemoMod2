package org.zeith.modid.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import org.zeith.hammerlib.tiles.TileSyncableTickable;

import java.util.ArrayList;
import java.util.List;

public class LightningGeneratorEntity extends TileSyncableTickable {
    public final EnergyStorage energyStorage = new EnergyStorage(1000000);
    public final LazyOptional<EnergyStorage> energyCap = LazyOptional.of(() -> energyStorage);
    private List<IEnergyStorage> recipients = new ArrayList<>();

    public LightningGeneratorEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state, BlockEntity be) {
        if (!(be instanceof LightningGeneratorEntity lightningGenerator)) return;

        int radius = 8;
        int energyPerBolt = 16000;

        AABB aabb = new AABB(pos).inflate(radius);
        List<LightningBolt> bolts = level.getEntitiesOfClass(LightningBolt.class, aabb);
        for (LightningBolt bolt : bolts) {
            if (bolt.getTags().contains(lightningGenerator.hash())) continue;
            Vec3 position = bolt.position();
            double distance = position.distanceTo(lightningGenerator.getBlockPos().getCenter());
            if (distance > radius) continue;
            bolt.addTag(lightningGenerator.hash());
            int addedEnergy = energyPerBolt - (int) ((double) energyPerBolt / radius * Math.max(distance - 1, 0));
            lightningGenerator.getEnergyStorage().receiveEnergy(addedEnergy, false);
            System.out.println("addEnergy | curr=" + lightningGenerator.getEnergyStorage().getEnergyStored());
        }
        transferEnergy();
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.ENERGY) {
            return energyCap.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Energy", energyStorage.serializeNBT());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Energy")) this.energyStorage.deserializeNBT(tag.get("Energy"));
    }

    public EnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    public String hash() {
        BlockPos pos = this.getBlockPos();
        return "lge" + pos.getX() + pos.getY() + pos.getZ();
    }

    public void UpdateConnected() {
        if (level instanceof ServerLevel) {
            List<IEnergyStorage> recipients = new ArrayList<>();
            for (Direction direction : Direction.values()) {
                BlockEntity te = level.getBlockEntity(worldPosition.relative(direction));
                if (te != null) {
                    te.getCapability(ForgeCapabilities.ENERGY, direction.getOpposite()).ifPresent(recipients::add);
                }
            }
            this.recipients = recipients;
        }
    }

    private void transferEnergy() {
        if (this.level == null) return;
        energyCap.ifPresent(energyHandler -> {
            int capacity = energyHandler.getEnergyStored();
            if (capacity <= 0) return;
            for (IEnergyStorage handler : recipients) {
                if (handler.canReceive() && capacity > 0) {
                    int received = handler.receiveEnergy(capacity, false);
                    capacity -= received;
                    energyHandler.extractEnergy(received, false);
                    this.setChanged();
                }

                if (capacity <= 0) break;
            }

        });
    }
}
