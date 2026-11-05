package dev.misg.shiftclickfix.mixin;

import dev.misg.shiftclickfix.ShiftInputState;
import net.minecraft.client.Keyboard;
import net.minecraft.client.input.KeyInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public abstract class KeyboardMixin {
    @Inject(
        method = "onKey(JILnet/minecraft/client/input/KeyInput;)V",
        at = @At("HEAD")
    )
    private void amethystShiftClickFix$trackShift(long window, int action, KeyInput input, CallbackInfo ci) {
        ShiftInputState.onKeyEvent(input.key(), action);
    }
}
