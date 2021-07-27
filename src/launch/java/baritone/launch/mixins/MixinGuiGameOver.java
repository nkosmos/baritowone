package baritone.launch.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import baritone.api.BaritoneAPI;
import net.minecraft.client.gui.GuiGameOver;

@Mixin(GuiGameOver.class)
public class MixinGuiGameOver {

	@Inject(method = "drawScreen", at = @At("HEAD"))
	public void drawScreen(CallbackInfo callbackInfos) {
		Minecraft.getMinecraft().thePlayer.respawnPlayer();
		Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
		BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().cancelEverything();
		BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().forceCancel();
	}
}
