package baritone.launch.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import baritone.api.BaritoneAPI;
import net.minecraft.client.gui.GuiGameOver;

@Mixin(GuiGameOver.class)
public class MixinGuiGameOver {
	
	@Inject(
			method = "initGui",
			at = @At("HEAD")
	)
	public void injectIntoConstructor(CallbackInfo callbackInfo) {
		BaritoneAPI.getProvider().getPrimaryBaritone().getGameEventHandler().onPlayerDeath();
	}

}
