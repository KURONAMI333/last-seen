package com.kuronami.seen;

import com.kuronami.seen.command.SeenCommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Seen — entry point (Forge 1.21.1).
 *
 * <p>{@code /seen <player>} for vanilla Forge servers, which have no
 * last-seen of their own and can't run the Paper plugin that does. A
 * login/logout listener writes to persistent {@link SeenData}; one
 * command reads it. No mixin, no config, no game object.
 */
@Mod(Seen.MOD_ID)
public class Seen {

    public static final String MOD_ID = "seen";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public Seen(FMLJavaModLoadingContext context) {
        LOGGER.info("Seen ready — /seen <player>.");
        MinecraftForge.EVENT_BUS.register(new SeenListener());
        MinecraftForge.EVENT_BUS.register(new SeenCommand());
    }
}
