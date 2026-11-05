package dev.misg.shiftclickfix.mixin;

import dev.misg.shiftclickfix.ShiftInputState;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.slot.SlotActionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin {
    @Inject(method = "mouseClicked(Lnet/minecraft/client/gui/Click;Z)Z", at = @At("HEAD"))
    private void amethystShiftClickFix$captureMouseButton(Click click, boolean doubled, CallbackInfoReturnable<Boolean> cir) {
        ShiftInputState.captureClick(click.button(), click.hasShift());
    }

    @ModifyVariable(
        method = "onMouseClick(Lnet/minecraft/screen/slot/Slot;Lnet/minecraft/screen/slot/SlotActionType;)V",
        at = @At("HEAD"),
        argsOnly = true
    )
    private SlotActionType amethystShiftClickFix$useQuickMove(SlotActionType original) {
        if (ShiftInputState.consumeShiftLeftClick()) {
            return SlotActionType.QUICK_MOVE;
        }

        return original;
    }
}
