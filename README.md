# Amethyst Shift-Click Fix

A client-side Fabric mod targeting Minecraft 1.21.11 for testing Shift + left-click quick-move behavior with external keyboards and mice on Amethyst iOS.

## What it changes

- Tracks left and right Shift key press/release events in Minecraft's keyboard event path.
- When a left mouse click is made on a handled inventory slot while Shift is held, changes that slot action to vanilla `QUICK_MOVE`.
- Uses Minecraft's normal screen-handler quick-move behavior, so inventories, chests, and compatible crafting result slots use their existing transfer rules.
- Leaves right-click and ordinary left-click behavior unchanged.

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

## Important testing note

This is an experimental compatibility workaround. It has not yet been verified on a physical iPad running Amethyst. A Fabric mod can only react to keyboard and mouse events that Amethyst passes into Minecraft; if Amethyst drops the Shift key event before Minecraft receives it, the launcher input layer itself will also need a fix.

Test in a creative world first. Check player inventory, a chest, and a crafting output. Report the Minecraft version, Fabric Loader version, Amethyst version, and whether the Shift key press is detected.

## License

MIT
