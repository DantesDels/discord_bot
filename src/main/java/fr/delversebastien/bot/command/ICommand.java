package fr.delversebastien.bot.command;

import java.util.List;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface ICommand {
    String getName();
    String getDescription();
    void execute(MessageReceivedEvent event, List<String> args);
}