package fr.delversebastien.bot.command;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class PollCommand implements ICommand {

    @Override
    public String getName() { return "poll"; }

    @Override
    public String getDescription() { return "Crée un sondage interactif."; }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(getName(), getDescription())
                .addOptions(
                    new OptionData(OptionType.STRING, "question", "La question du sondage", true),
                    new OptionData(OptionType.STRING, "choix1", "La première option", true),
                    new OptionData(OptionType.STRING, "choix2", "La deuxième option", true)
                );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        // 1. Récupération des options saisies par l'utilisateur
        String question = event.getOption("question").getAsString();
        String option1 = event.getOption("choix1").getAsString();
        String option2 = event.getOption("choix2").getAsString();

        // 2. Construction de l'Embed
        EmbedBuilder embed = new EmbedBuilder()
            .setTitle("📊 Sondage")
            .setDescription(question)
            .addField("Option 1", "1️⃣ " + option1, false)
            .addField("Option 2", "2️⃣ " + option2, false)
            .setColor(Color.ORANGE)
            .setFooter("Votez en cliquant sur les réactions ci-dessous !");

        // 3. Envoi de la réponse
        // On utilise reply() car c'est une Slash Command[cite: 1]
        event.replyEmbeds(embed.build()).queue(interactionHook -> {
            // Une fois le message envoyé, on ajoute les réactions sur le message original[cite: 1]
            interactionHook.retrieveOriginal().queue(message -> {
                message.addReaction(Emoji.fromUnicode("1️⃣")).queue();
                message.addReaction(Emoji.fromUnicode("2️⃣")).queue();
            });
        });
    }
}