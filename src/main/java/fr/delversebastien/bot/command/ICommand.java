package fr.delversebastien.bot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public interface ICommand {
    /**
     * @return Le nom de la commande (ex: "ping")
     */
    String getName();

    /**
     * @return La description affichée dans le menu Discord
     */
    String getDescription();

    /**
     * Méthode d'exécution principale pour les Slash Commands.
     * @param event L'événement d'interaction envoyé par Discord.
     */
    void execute(SlashCommandInteractionEvent event);

    /**
     * Génère les données de la commande pour l'enregistrement auprès de Discord.
     * @return Un objet CommandData.
     */
    default CommandData getCommandData() {
        return Commands.slash(getName(), getDescription());
    }
}