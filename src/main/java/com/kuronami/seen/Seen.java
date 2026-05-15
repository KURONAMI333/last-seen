package com.kuronami.seen;

import com.kuronami.seen.command.SeenCommand;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Seen — entry point.
 *
 * <p>{@code /seen <player>} for vanilla NeoForge servers, which have no
 * last-seen of their own and can't run the Paper plugin that does. A
 * login/logout listener writes to persistent {@link SavedData}; one
 * command reads it. No mixin, no config, no game object.
 */
@Mod(Seen.MOD_ID)
public class Seen {

    public static final String MOD_ID = "seen";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public Seen(IEventBus modBus, ModContainer container) {
        LOGGER.info("Seen ready — /seen <player>.");
        NeoForge.EVENT_BUS.register(new SeenListener());
        NeoForge.EVENT_BUS.register(new SeenCommand());
    }
}
