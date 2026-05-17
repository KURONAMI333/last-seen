package com.kuronami.seen;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;

/**
 * Stamps a player's name on every login and logout. Recording login too
 * (not just logout) means a currently-online player is still "known",
 * and a name that was never recorded can be answered honestly as
 * "never seen here".
 *
 * <p>Fabric variant: static helpers driven by the
 * {@code ServerPlayConnectionEvents} JOIN/DISCONNECT callbacks wired in
 * {@link SeenFabric}.
 */
public final class SeenListener {

    private SeenListener() {
    }

    public static void onJoin(Player player) {
        stamp(player);
    }

    public static void onDisconnect(Player player) {
        stamp(player);
    }

    private static void stamp(Player player) {
        MinecraftServer server = player.getServer();
        if (server == null) {
            return; // client-side Player instance — ignore.
        }
        SeenData.get(server).touch(player.getGameProfile().getName());
    }
}
