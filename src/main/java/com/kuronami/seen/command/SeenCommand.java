package com.kuronami.seen.command;

import com.kuronami.seen.SeenData;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

/**
 * {@code /seen <player>}. Open to everyone. Online players answer
 * "online now"; otherwise the last few sightings (most recent first),
 * each with a rough days-ago, localized.
 */
public class SeenCommand {

    private static final SimpleDateFormat FMT =
        new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ROOT);

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
            Commands.literal("seen")
                .then(Commands.argument("player", StringArgumentType.word())
                    .executes(this::seen)));
    }

    private int seen(CommandContext<CommandSourceStack> ctx) {
        CommandSourceStack src = ctx.getSource();
        MinecraftServer server = src.getServer();
        String name = StringArgumentType.getString(ctx, "player");

        if (server.getPlayerList().getPlayerByName(name) != null) {
            src.sendSuccess(() -> Component.translatable("seen.online", name)
                .withStyle(ChatFormatting.GREEN), false);
            return Command.SINGLE_SUCCESS;
        }

        long[] ts = SeenData.get(server).get(name);
        if (ts == null || ts.length == 0) {
            src.sendSuccess(() -> Component.translatable("seen.never", name)
                .withStyle(ChatFormatting.GRAY), false);
            return Command.SINGLE_SUCCESS;
        }

        long now = System.currentTimeMillis();
        // Most recent sighting.
        String when0 = FMT.format(new Date(ts[0]));
        long days0 = Math.max(0, (now - ts[0]) / 86_400_000L);
        src.sendSuccess(() -> Component.translatable(
            "seen.offline", name, when0, days0).withStyle(ChatFormatting.YELLOW),
            false);
        // Older sightings, if any.
        for (int i = 1; i < ts.length; i++) {
            String when = FMT.format(new Date(ts[i]));
            long days = Math.max(0, (now - ts[i]) / 86_400_000L);
            src.sendSuccess(() -> Component.translatable(
                "seen.entry", when, days).withStyle(ChatFormatting.GRAY), false);
        }
        return Command.SINGLE_SUCCESS;
    }
}
