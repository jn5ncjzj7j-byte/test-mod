package dev.misg.shiftclickfix;

import org.lwjgl.glfw.GLFW;

public final class ShiftInputState {
    private static volatile boolean leftShiftDown;
    private static volatile boolean rightShiftDown;
    private static volatile int pendingMouseButton = -1;
    private static volatile boolean pendingShiftClick;
    private static volatile boolean lastClickForcedQuickMove;

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
        return leftShiftDown || rightShiftDown;
    }

    public static void captureClick(int button, boolean clickHasShift) {
        pendingMouseButton = button;
        pendingShiftClick = button == GLFW.GLFW_MOUSE_BUTTON_1 && (clickHasShift || isShiftDown());
        lastClickForcedQuickMove = false;
    }

    public static boolean consumeShiftLeftClick() {
        boolean forceQuickMove = pendingMouseButton == GLFW.GLFW_MOUSE_BUTTON_1 && pendingShiftClick;
        pendingMouseButton = -1;
        pendingShiftClick = false;
        lastClickForcedQuickMove = forceQuickMove;
        return forceQuickMove;
    }

    public static boolean wasLastClickForcedQuickMove() {
        return lastClickForcedQuickMove;
    }
}
