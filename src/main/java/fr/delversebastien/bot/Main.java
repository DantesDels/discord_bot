package fr.delversebastien.bot;

import fr.delversebastien.bot.listener.PingListener;
import io.github.cdimascio.dotenv.Dotenv;
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

        JDABuilder.createDefault(token)
            .enableIntents(GatewayIntent.MESSAGE_CONTENT) // Requis pour lire !ping
            .addEventListeners(new PingListener())
            .build();
    }
}