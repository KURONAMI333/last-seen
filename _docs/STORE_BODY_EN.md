# Player Last Seen

`/seen [player]` — when was this player last online? The last-seen lookup that vanilla NeoForge and Fabric servers don't have.

Vanilla has no last-seen, and the plugin that does (EssentialsX) only runs on Paper, not on vanilla NeoForge/Fabric servers. This adds the command as a mod.

```
/seen Steve  → Steve is online right now.
/seen Alex   → Alex was last seen 2026-05-15 14:30 (about 1 day ago).
                 also seen 2026-05-13 22:10 (about 3 days ago)
```

It tracks login/logout server-side, keeps the last 3 sightings per player, and persists across restarts. `/seen` is open to all players, like EssentialsX's — anyone can query anyone's last-online, so it does reveal players' activity windows server-wide. In single-player, leave and re-enter the world to populate it. Output is localized in 9 languages.

It's a login/logout listener, persistent world data, and one command — no mixin, no config, no blocks or items.

Server-side — install on the server only.

Free to use in any modpack. Source and issues: https://github.com/KURONAMI333/last-seen
