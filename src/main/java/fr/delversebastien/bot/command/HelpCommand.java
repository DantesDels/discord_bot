package fr.delversebastien.bot.command;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class HelpCommand implements ICommand {
    private final CommandManager manager;

    public HelpCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public String getName() { return "help"; }

    @Override
    public String getDescription() { return "Affiche la liste des commandes."; }

    // Remplace l'import MessageReceivedEvent par :

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("🤖 Liste des commandes de Boris");
        embed.setColor(Color.CYAN);

        for (ICommand cmd : manager.getCommands()) {
            embed.addField("/" + cmd.getName(), cmd.getDescription(), false);
        }

        event.replyEmbeds(embed.build()).queue();
    }
}