package fr.delversebastien.bot.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class CommandManager {
    private final Map<String, ICommand> commands = new HashMap<>();

    public void addCommand(ICommand cmd) {
        commands.put(cmd.getName().toLowerCase(), cmd);
    }

    public Collection<ICommand> getCommands() {
    return commands.values();
    }

    public void handle(MessageReceivedEvent event) {
        Dotenv dotenv = Dotenv.load();
        String PREFIX = dotenv.get("PREFIX");
        String raw = event.getMessage().getContentRaw();

        if (!raw.startsWith(PREFIX)) return;

        List<String> split = Arrays.asList(raw.replaceFirst(PREFIX, "").split("\\s+"));
        if (split.isEmpty()) return;

        String commandName = split.get(0).toLowerCase();
        List<String> args = split.subList(1, split.size());

        System.out.println("----------------------------------------------");
        System.out.println("LOG [" + java.time.LocalTime.now().withNano(0) + "]");
        System.out.println("COMMANDE : " + commandName);
        System.out.println("AUTEUR   : " + event.getAuthor().getName());
        System.out.println("----------------------------------------------");

        if (commands.containsKey(commandName)) {
            commands.get(commandName).execute(event, args);
        } else {
            event.getChannel().sendMessage("❌ Commande inconnue.").queue();
        }
    }

    public void handleSlash(SlashCommandInteractionEvent event) {
        ICommand cmd = commands.get(event.getName().toLowerCase());
        if (cmd != null) {
            cmd.execute(event);
        }
    }

    public List<CommandData> getSlashCommandsData() {
        return commands.values().stream()
                .map(ICommand::getCommandData)
                .toList();
    }
}
