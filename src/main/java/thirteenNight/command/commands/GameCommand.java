package thirteenNight.command.commands;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import thirteenNight.Game;
import thirteenNight.command.AbstractPlayerCommand;
import thirteenNight.item.GameItem;

import java.util.Objects;

public class GameCommand extends AbstractPlayerCommand {
    @Override
    public String getName() {
        return "game";
    }

    @Override
    public boolean onCommand(CommandSender sender, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§4Please input a subcommand");
            return true;
        }

        Game game = Game.getGame();
        NewPlayer player = NewPlayer.getNewPlayer((Player) sender);
        switch (args[0]) {
            case "join":
                if (game.containPlayer(player)) {
                    player.sendMessage("§cYou are already join the game");
                } else {
                    game.addPlayer(player);
                    Bukkit.broadcastMessage("§a" + player.getName() + "§e joined the game");
                }
            break;
            case "unjoin":
                if (!game.containPlayer(player)) {
                    player.sendMessage("§cYou are not join the game");
                } else {
                    game.removePlayer(player);
                    Bukkit.broadcastMessage("§a" + player.getName() + "§e unjoined the game");
                }
            break;
            case "loc":
                player.getInventory().addItem(GameItem.LOC_HELPER);
            break;
            case "start":
                if (!player.isOp()) {
                    player.sendMessage("§cYou are not op");
                    return true;
                }

                if (game.isStart()) {
                    player.sendMessage("§cThe game is already started");
                } else {
                    game.run();
                }
            break;
            case "stop":
                if (!player.isOp()) {
                    player.sendMessage("§cYou are not op");
                    return true;
                }

                if (!game.isStart()) {
                    player.sendMessage("§cThe game is not started");
                } else {
                    game.stop();
                }
            break;
            case "time":
                game.setTime(Integer.parseInt(args[1]));
            break;
            case "murder":
                if (!player.isOp()) {
                    player.sendMessage("§cYou are not op");
                    return true;
                }

                if (game.isStart()) {
                    player.sendMessage("§cThe game is already started");
                } else {
                    if (args[1].equals("null")) {
                        game.setMurder(null);
                        return true;
                    }
                    Player murder = Bukkit.getPlayer(args[1]);
                    game.setMurder(Objects.requireNonNull(murder).getUniqueId());
                }
            break;
            case "revive":
                if (!player.isOp()) {
                    player.sendMessage("§cYou are not op");
                    return true;
                }

                if (!game.isStart()) {
                    player.sendMessage("§cThe game is not started");
                } else {
                    Player murder = Bukkit.getPlayer(args[0]);
                    game.setMurder(Objects.requireNonNull(murder).getUniqueId());
                }
            break;
            default:
                sender.sendMessage("§a존재하지 않는 명령어 입니다.");
        }
        return true;
    }
}
