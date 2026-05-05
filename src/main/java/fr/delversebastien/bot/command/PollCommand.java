package fr.delversebastien.bot.command;

import java.awt.Color;
import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PollCommand implements ICommand {
    @Override
    public String getName() { return "poll"; }

    @Override
    public String getDescription() { return "Crée un sondage. Usage: !poll Question | Choix 1 | Choix 2"; }

    @Override
    public void execute(MessageReceivedEvent event, List<String> args) {
        String content = String.join(" ", args);
        String[] parts = content.split("\\|");

        if (parts.length < 3) {
            event.getChannel().sendMessage("Usage correct : `!poll Question | Option 1 | Option 2`").queue();
            return;
        }

        String question = parts[0].trim();
        String option1 = parts[1].trim();
        String option2 = parts[2].trim();

        EmbedBuilder embed = new EmbedBuilder()
            .setTitle("📊 Sondage")
            .setDescription(question)
            .addField("Option 1", "1️⃣ " + option1, false)
            .addField("Option 2", "2️⃣ " + option2, false)
            .setColor(Color.ORANGE)
            .setFooter("Votez en cliquant sur les réactions ci-dessous !");

        event.getChannel().sendMessageEmbeds(embed.build()).queue(message -> {
            message.addReaction(Emoji.fromUnicode("1️⃣")).queue();
            message.addReaction(Emoji.fromUnicode("2️⃣")).queue();
        });
    }
}