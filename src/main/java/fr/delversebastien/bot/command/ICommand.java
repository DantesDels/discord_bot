package fr.delversebastien.bot.command;

import java.util.List;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public interface ICommand {
    String getName();
    String getDescription();

    // Keeping both execute methods with default empty implementations 
    // allows us to implement only the relevant one in each command class, 
    // without forcing us to provide an implementation for both. This way, 
    // a command can choose to be either a message-based command, a slash command, or both, 
    // without unnecessary boilerplate.
    default void execute(MessageReceivedEvent event, List<String> args) {}

    // For the slash command version
    default void execute(SlashCommandInteractionEvent event) {}

    default CommandData getCommandData() {
        return Commands.slash(
            getName(), 
            getDescription()
        );
    }
}