package dev.misg.shiftclickfix.mixin;

import dev.misg.shiftclickfix.ShiftInputState;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public abstract class KeyboardMixin {
    @Inject(
        method = "onKey(JIIII)V",
        at = @At("HEAD")
    )
    private void amethystShiftClickFix$trackShift(long window, int key, int scanCode, int action, int modifiers, CallbackInfo ci) {
        ShiftInputState.onKeyEvent(key, action);
    }
}
