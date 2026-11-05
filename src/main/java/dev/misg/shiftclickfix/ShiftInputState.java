package dev.misg.shiftclickfix;

import org.lwjgl.glfw.GLFW;

public final class ShiftInputState {
    private static volatile boolean leftShiftDown;
    private static volatile boolean rightShiftDown;
    private static volatile boolean clickHasShift;
    private static volatile int lastMouseButton = -1;

    private ShiftInputState() {
    }

    public static void onKeyEvent(int keyCode, int action) {
        boolean down = action != GLFW.GLFW_RELEASE;

        if (keyCode == GLFW.GLFW_KEY_LEFT_SHIFT) {
            leftShiftDown = down;
        } else if (keyCode == GLFW.GLFW_KEY_RIGHT_SHIFT) {
            rightShiftDown = down;
        }
    }

    public static boolean isShiftDown() {
        return leftShiftDown || rightShiftDown || clickHasShift;
    }

    public static void captureClick(int button, boolean shiftModifier) {
        lastMouseButton = button;
        clickHasShift = shiftModifier;
    }

    public static boolean consumeShiftLeftClick() {
        int button = lastMouseButton;
        lastMouseButton = -1;
        boolean shift = isShiftDown();
        clickHasShift = false;
        return button == GLFW.GLFW_MOUSE_BUTTON_1 && shift;
    }
}
