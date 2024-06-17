package net.sydokiddo.auditory.mixin.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.sydokiddo.auditory.Auditory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Lead Attaching Sounds

@Mixin(Leashable.class)
public interface LeadAttachSoundMixin {

    @Inject(method = "setLeashedTo(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerChunkCache;broadcast(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/network/protocol/Packet;)V"))
    private static <E extends Entity & Leashable> void auditory_leashSound(E entity, Entity entity2, boolean bl, CallbackInfo ci) {
        if (Auditory.getConfig().item_sounds.lead_sounds) {
            entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.LEASH_KNOT_PLACE, SoundSource.NEUTRAL, 0.5F, 0.8f + entity.level().random.nextFloat() * 0.4F);
        }
    }

    @Inject(method = "tickLeash", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Leashable;dropLeash(Lnet/minecraft/world/entity/Entity;ZZ)V", shift = Shift.AFTER))
    private static <E extends Entity & Leashable> void auditory_unleashSound(E entity, CallbackInfo ci) {
        if (Auditory.getConfig().item_sounds.lead_sounds) {
            entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.LEASH_KNOT_BREAK, SoundSource.NEUTRAL, 0.5F, 0.8f + entity.level().random.nextFloat() * 0.4F);
        }
    }
}
