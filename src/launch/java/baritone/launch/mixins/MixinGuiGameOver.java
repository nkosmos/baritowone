package baritone.launch.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.network.NetHandlerPlayClient;

@Mixin(GuiGameOver.class)
public class MixinGuiGameOver {
	
	@Inject(
			method = "initGui",
			at = @At("HEAD")
	)
	public void injectIntoConstructor(CallbackInfo callbackInfo) {
		for (IBaritone ibaritone : BaritoneAPI.getProvider().getAllBaritones()) {
    		EntityClientPlayerMP player = ibaritone.getPlayerContext().player();
            if (player != null && player.sendQueue == (NetHandlerPlayClient) (Object) this) {
                ibaritone.getGameEventHandler().onPlayerDeath();
            }
        }
	}

}
