# Seen

> `/seen <player>` — when was this player last online? The thing every vanilla NeoForge server is missing.

## What it does

```
/seen Steve
→ Steve is online right now.

/seen Alex
→ Alex was last seen 2026-05-15 14:30 (about 1 day ago).
    also seen 2026-05-13 22:10 (about 3 days ago)
    also seen 2026-05-12 08:05 (about 4 days ago)
```

Tracks login/logout, keeps the **last 3 sightings** per player, and **persists across restarts**. Open to all players (it's the kind of thing players ask each other — same as Essentials' `/seen` on Paper).

## Why

Vanilla has no last-seen. The Paper plugin that does (EssentialsX) doesn't run on vanilla NeoForge/Fabric servers — a recurring r/admincraft gap ("is there a plugin that shows when players were last online?").

## Install

Drop `seen-<version>.jar` into `mods/`. Server-side. No dependencies.

- Minecraft 1.21.1 · NeoForge · JDK 21

## Scope

A login/logout listener + persistent world data + one command. No mixin, no config, no blocks/items. 9 languages (machine-baseline; native PRs welcome).

## License

MIT — modpack inclusion welcome, no credit required.

Author: KURONAMI
