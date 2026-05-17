package com.kuronami.seen;

import com.kuronami.seen.command.SeenCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Seen — entry point (Fabric 1.21.1).
 *
 * <p>{@code /seen <player>} for vanilla Fabric servers, which have no
 * last-seen of their own and can't run the Paper plugin that does.
 * Login/logout stamp into persistent {@link SeenData}; one command
 * reads it. No mixin, no config, no game object.
 *
 * <p>Fabric variant: connection join/disconnect callbacks and
 * {@code CommandRegistrationCallback} drive the static helpers in
 * {@link SeenListener} and {@link SeenCommand} — the Forge/NeoForge
 * event-bus classes are not reused, but the logic is identical.
 */
public class SeenFabric implements ModInitializer {

    public static final String MOD_ID = "seen";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Seen ready — /seen <player>.");

        ServerPlayConnectionEvents.JOIN.register(
            (handler, sender, server) -> SeenListener.onJoin(handler.player));
        ServerPlayConnectionEvents.DISCONNECT.register(
            (handler, server) -> SeenListener.onDisconnect(handler.player));

        CommandRegistrationCallback.EVENT.register(
            (dispatcher, registryAccess, environment) ->
                SeenCommand.register(dispatcher));
    }
}
