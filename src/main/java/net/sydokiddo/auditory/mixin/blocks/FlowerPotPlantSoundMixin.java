package net.sydokiddo.auditory.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.sydokiddo.auditory.Auditory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Plays a sound when a plant is put into a flower pot

@Mixin(FlowerPotBlock.class)
public abstract class FlowerPotPlantSoundMixin {

    @Inject(method = "useItemOn",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;gameEvent(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/Holder;Lnet/minecraft/core/BlockPos;)V",
            shift = Shift.AFTER
        )
    )
    private void auditory_pottingSound(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult, CallbackInfoReturnable<ItemInteractionResult> cir) {
        if (Auditory.getConfig().block_sounds.flower_pot_sounds) {
            level.playSound(player, blockPos, SoundEvents.HANGING_ROOTS_PLACE, SoundSource.BLOCKS, 1.0f, 0.8f + level.random.nextFloat() * 0.4F);
        }
    }
}