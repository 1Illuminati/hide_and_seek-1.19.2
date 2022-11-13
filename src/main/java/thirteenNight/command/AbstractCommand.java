package thirteenNight.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class AbstractCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals(getName())) {
            return onCommand(sender, label, args);
        }
        return false;
    }

    public abstract String getName();

    public abstract boolean onCommand(CommandSender sender, String label, String[] args);
}