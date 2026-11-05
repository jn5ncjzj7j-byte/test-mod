package dev.misg.shiftclickfix.mixin;

import dev.misg.shiftclickfix.ShiftInputState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin {
    @Inject(method = "onMouseClick(Lnet/minecraft/client/gui/Click;)V", at = @At("HEAD"))
    private void amethystShiftClickFix$captureMouseButton(Click click, CallbackInfo ci) {
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

    @Inject(method = "render", at = @At("TAIL"))
    private void amethystShiftClickFix$renderStatus(DrawContext context, int mouseX, int mouseY, float deltaTicks, CallbackInfo ci) {
        String shiftStatus = ShiftInputState.isShiftDown() ? "ON" : "OFF";
        String clickStatus = ShiftInputState.wasLastClickForcedQuickMove() ? "QUICK_MOVE" : "NORMAL";
        String status = "Shift: " + shiftStatus + " | Last click: " + clickStatus;
        context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, Text.literal(status), 6, 6, 0xFFFFFF);
    }
}
