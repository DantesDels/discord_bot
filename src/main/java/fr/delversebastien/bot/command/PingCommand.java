package fr.delversebastien.bot.command;

import java.util.List;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PingCommand implements ICommand {
    @Override
    public String getName() { return "ping"; }

    @Override
    public String getDescription() { return "Répond par Pong!"; }

    @Override
    public void execute(MessageReceivedEvent event, List<String> args) {
        event.getChannel().sendMessage("Pong! JE FONCTIONNE CONNARD MWHAHAHAHAHA ! JE SUIS VIVANT !!!").queue();
    }
}