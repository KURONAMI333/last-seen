# Seen

> `/seen <player>` — when was this player last online? The thing every vanilla NeoForge server is missing.

Recurring r/admincraft gap: "is there a plugin that shows when players were last online?" Vanilla has no last-seen, and the Paper plugin that does (EssentialsX) doesn't run on vanilla NeoForge/Fabric servers.

- 👀 `/seen <player>` — "online now", or the last few sightings with rough days-ago
- 🕒 Keeps the **last 3** sightings per player
- 💾 Persists across restarts
- 🤝 Open to all players (like Essentials' `/seen`)

## What it does / Usage

```
/seen Steve  → Steve is online right now.
/seen Alex   → Alex was last seen 2026-05-15 14:30 (about 1 day ago).
                 also seen 2026-05-13 22:10 (about 3 days ago)
```

Tracks login/logout server-side.

## Supported loaders / versions

| Minecraft | NeoForge | Forge | Fabric |
|---|:---:|:---:|:---:|
| 1.21.1 | ✅ | planned | planned |

Forge / Fabric / 1.20.1 ports planned; this release is NeoForge 1.21.1.

## Dependencies

None.

## Compatibility & scope

Server-side: a login/logout listener + persistent world data + one command. No mixin, no config, no blocks/items — can't conflict with other mods.

## Known limitations

Keeps the 3 most recent sightings per player. Single-player: leave and re-enter the world to populate it. `/seen` is intentionally open to all players (like EssentialsX's `/seen`) — anyone can query anyone's last-online, so it does reveal players' activity windows server-wide.

## Install

1. Install NeoForge for Minecraft 1.21.1.
2. Drop `seen-0.1.0.jar` into `mods/`. Server-side.

- Minecraft 1.21.1 · NeoForge · JDK 21

## Languages

Output localized in 9 languages (machine-baseline; native-speaker PRs welcome).

## License

MIT — modpack inclusion welcome, no credit required.

Author: KURONAMI
