package fr.delversebastien.bot.command;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.json.JSONObject;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public class JokeCommand implements ICommand {
    @Override
    public String getName() { return "joke"; }

    @Override
    public String getDescription() { return "Raconte une blague aléatoire."; }

    @Override
    public void execute(MessageReceivedEvent event, List<String> args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://official-joke-api.appspot.com/random_joke"))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(body -> {
                    try {
                        JSONObject json = new JSONObject(body);
                        String setup = json.getString("setup");
                        String punchline = json.getString("punchline");
                        event.getChannel().sendMessage("**" + setup + "**\n*" + punchline + "*").queue();
                    } catch (Exception e) {
                        event.getChannel().sendMessage("⚠️ Erreur lors de la récupération de la blague...").queue();
                        System.err.println("Erreur JSON Joke API : " + e.getMessage());
                    }
                });
    }
}