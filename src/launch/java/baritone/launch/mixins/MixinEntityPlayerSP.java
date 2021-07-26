package baritone.launch.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.event.events.SprintStateEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerCapabilities;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP {
	
	@Redirect(
            method = "onLivingUpdate",
            at = @At(
                    value = "FIELD",
                    target = "net/minecraft/entity/player/PlayerCapabilities.allowFlying:Z"
            )
    )
    private boolean isAllowFlying(PlayerCapabilities capabilities) {
        IBaritone baritone = BaritoneAPI.getProvider().getBaritoneForPlayer((EntityPlayerSP) (Object) this);
        if (baritone == null) {
            return capabilities.allowFlying;
        }
        return !baritone.getPathingBehavior().isPathing() && capabilities.allowFlying;
    }

    @Redirect(
            method = "onLivingUpdate",
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/client/settings/KeyBinding.getIsKeyPressed()Z"
            )
    )
    private boolean isKeyDown(KeyBinding keyBinding) {
        IBaritone baritone = BaritoneAPI.getProvider().getBaritoneForPlayer((EntityPlayerSP) (Object) this);
        if (baritone == null) {
            return keyBinding.getIsKeyPressed();
        }
        SprintStateEvent event = new SprintStateEvent();
        baritone.getGameEventHandler().onPlayerSprintState(event);
        if (event.getState() != null) {
            return event.getState();
        }
        if (baritone != BaritoneAPI.getProvider().getPrimaryBaritone()) {
            // hitting control shouldn't make all bots sprint
            return false;
        }
        return keyBinding.getIsKeyPressed();
    }

}
