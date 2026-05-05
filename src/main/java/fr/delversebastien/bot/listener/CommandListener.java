package fr.delversebastien.bot.listener;

import org.jetbrains.annotations.NotNull;

import fr.delversebastien.bot.command.CommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
    private final CommandManager manager;

    public CommandListener(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        manager.handle(event);
    }
}