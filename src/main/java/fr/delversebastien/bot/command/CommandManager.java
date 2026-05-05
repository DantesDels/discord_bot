package fr.delversebastien.bot.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

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
        String commandName = split.get(0).toLowerCase();
        List<String> args = split.subList(1, split.size());

        if (commands.containsKey(commandName)) {
            commands.get(commandName).execute(event, args);
        }
    }
}