# Amethyst Shift-Click Fix

A client-side Fabric mod targeting Minecraft 1.21.11. It is intended as a workaround for Shift + left-click quick-move failures with external keyboards and mice on Amethyst iOS.

## What it changes

- Tracks left and right Shift key press/release events from Minecraft's keyboard event handler.
- Captures the mouse button used for each handled-screen click.
- Converts only Shift + left-click slot actions to vanilla `QUICK_MOVE`.
- Leaves ordinary left-clicks and right-clicks unchanged.
- Uses vanilla screen-handler transfer rules for inventories, chests, and compatible crafting result slots.

## Compatibility

- Minecraft: 1.21.11
- Mod loader: Fabric
- Environment: client-side
- Java: 21

## Build

Run:

```sh
gradle build
```

The mod jar is created in `build/libs/`.

## Install

Put the built jar in the Minecraft instance's `mods` folder alongside Fabric Loader.

## Why this workaround exists

The related launcher-side issue is caused by modifier state not being propagated correctly to mouse input. Fold Craft Launcher addressed the same class of issue by updating its GLFW modifier state when physical keyboard events arrive: [FCL fix commit](https://github.com/FCL-Team/FoldCraftLauncher/commit/08c0716a194dfec6fc88a62fb303cee1eb0fb925).

This mod tries to compensate inside Minecraft by tracking Shift key events and applying the quick-move action directly. It cannot recover key events that the launcher never sends to Minecraft.

## Testing status

This is experimental and has not yet been verified on a physical iPad running Amethyst. Test in a creative world first:

1. Open the player inventory.
2. Hold Shift and left-click an item.
3. Open a chest and repeat in both directions.
4. Try Shift-clicking a compatible crafting output.

If it does not work, report the Amethyst version, Fabric Loader version, and whether Shift still works for crouching or sprinting.

## License

MIT
