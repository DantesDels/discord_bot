package fr.delversebastien.bot.command;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class JokeCommand implements ICommand {

    @Override
    public String getName() { return "joke"; }

    @Override
    public String getDescription() { return "Raconte une blague aléatoire."; }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        // 1. On "defer" la réponse pour éviter le timeout de 3 secondes
        event.deferReply().queue();

        HttpClient client = HttpClient.newHttpClient();
        
        // 2. Définition de la requête (bien à l'intérieur de la méthode)
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://official-joke-api.appspot.com/random_joke"))
                .build();

        // 3. Envoi asynchrone utilisant la variable 'request' définie juste au-dessus[cite: 1]
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(body -> {
                    try {
                        JSONObject json = new JSONObject(body);
                        String setup = json.getString("setup");
                        String punchline = json.getString("punchline");
                        
                        // 4. On utilise le Hook pour envoyer la blague une fois reçue[cite: 1]
                        event.getHook().sendMessage("**" + setup + "**\n*" + punchline + "*").queue();
                    } catch (Exception e) {
                        event.getHook().sendMessage("⚠️ Erreur lors de la lecture de la blague.").queue();
                    }
                })
                .exceptionally(ex -> {
                    event.getHook().sendMessage("⚠️ Impossible de contacter l'API.").queue();
                    return null;
                });
    }
}