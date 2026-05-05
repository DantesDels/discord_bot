package fr.delversebastien.bot.command;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class CommandManager {
    private final Map<String, ICommand> commands = new HashMap<>();

    public void addCommand(ICommand cmd) {
        commands.put(cmd.getName().toLowerCase(), cmd);
    }

    public Collection<ICommand> getCommands() {
        return commands.values();
    }

    public void handleSlash(SlashCommandInteractionEvent event) {
        String name = event.getName().toLowerCase();
        ICommand cmd = commands.get(name);

        if (cmd != null) {
            cmd.execute(event);
            
            System.out.println("----------------------------------------------");
            System.out.println("LOG [" + java.time.LocalTime.now().withNano(0) + "]");
            System.out.println("COMMANDE : /" + name);
            System.out.println("AUTEUR   : " + event.getUser().getName());
            System.out.println("----------------------------------------------");
        }
    }

    public List<CommandData> getSlashCommandsData() {
        return commands.values().stream()
                .map(ICommand::getCommandData)
                .toList();
    }
}