package fr.delversebastien.bot;

import fr.delversebastien.bot.command.CommandManager;
import fr.delversebastien.bot.command.HelpCommand;
import fr.delversebastien.bot.command.JokeCommand;
import fr.delversebastien.bot.command.PingCommand;
import fr.delversebastien.bot.command.PollCommand;
import fr.delversebastien.bot.listener.CommandListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("TOKEN");

        if (token == null || token.isEmpty()) {
            System.err.println("ERREUR : Le TOKEN est absent du fichier .env");
            return;
        }

        CommandManager manager = new CommandManager();
        manager.addCommand(new PingCommand());
        manager.addCommand(new HelpCommand(manager));
        manager.addCommand(new JokeCommand());
        manager.addCommand(new PollCommand());

       JDA jda = JDABuilder.createDefault(token)
        .enableIntents(GatewayIntent.MESSAGE_CONTENT)
        .addEventListeners(new CommandListener(manager))
        .build();

        try {
            jda.awaitReady();
        } catch (Exception e) {
            System.err.println("ERREUR : Impossible de se connecter à Discord");
            e.printStackTrace();
            return;
        }; 

        jda.updateCommands().addCommands(manager.getSlashCommandsData()).queue();

        System.out.println("Slash Commands synchronysed !");
        System.out.println("Boris the Bot is using JDK 21 and Gradle 9.5.0, Remember that...");
    }
}