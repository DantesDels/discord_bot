package fr.delversebastien.bot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PingCommand implements ICommand {
    @Override
    public String getName() { return "ping"; }

    @Override
    public String getDescription() { return "Répond par Pong!"; }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("Pong! 🏓").queue();
    }
}