package fr.delversebastien.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class Bot {
    public static void main(String[] args) throws Exception {
        String token = "TON_TOKEN_ICI";

        JDA jda = JDABuilder.createDefault(token)
                .setActivity(Activity.playing("Mon bot Java"))
                .build();

        jda.awaitReady();
        System.out.println("Bot prêt !");
    }
}