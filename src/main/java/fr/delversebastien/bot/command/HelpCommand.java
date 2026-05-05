package fr.delversebastien.bot.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.awt.Color;
import java.util.List;

public class HelpCommand implements ICommand {
    private final CommandManager manager;

    public HelpCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public String getName() { return "help"; }

    @Override
    public String getDescription() { return "Affiche la liste des commandes."; }

    @Override
    public void execute(MessageReceivedEvent event, List<String> args) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("🤖 Liste des commandes de Boris");
        embed.setColor(Color.CYAN);

        for (ICommand cmd : manager.getCommands()) {
            embed.addField("!" + cmd.getName(), cmd.getDescription(), false);
        }

        event.getChannel().sendMessageEmbeds(embed.build()).queue();
    }
}