package com.kuronami.seen;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

/**
 * Stamps a player's name on every login and logout. Recording login too
 * (not just logout) means a currently-online player is still "known",
 * and a name that was never recorded can be answered honestly as
 * "never seen here".
 */
public class SeenListener {

    @SubscribeEvent
    public void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        stamp(event.getEntity());
    }

    @SubscribeEvent
    public void onLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        stamp(event.getEntity());
    }

    private void stamp(Player player) {
        MinecraftServer server = player.getServer();
        if (server == null) {
            return; // client-side Player instance — ignore.
        }
        SeenData.get(server).touch(player.getGameProfile().getName());
    }
}
