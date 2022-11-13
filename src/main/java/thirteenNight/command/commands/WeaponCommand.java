package thirteenNight.command.commands;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import thirteenNight.command.AbstractPlayerCommand;
import thirteenNight.item.GameItem;

public class WeaponCommand extends AbstractPlayerCommand {
    @Override
    public String getName() {
        return "weapon";
    }

    @Override
    public boolean onCommand(CommandSender sender, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage("weapon give : 살인마 무기를 지급받습니다.");
            player.sendMessage("weapon clear [playerName] : 해당 플레이어의 살인마 스킬 사용 횟수를 초기화 합니다.");
            return false;
        }

        String sub = args[0];
        switch(sub) {
            case "give":
                player.getInventory().addItem(GameItem.murderWeapon.toArray(new ItemStack[0]));
                player.getInventory().addItem(GameItem.runnerWeapon.toArray(new ItemStack[0]));
                player.sendMessage("아이템이 지급되었습니다.");
            break;
            case "clear":
                if (args.length < 2) {
                    player.sendMessage("§c이름을 입력해주세요.");
                    return false;
                }

                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    player.sendMessage("§c존재하지 않는 플레이어 입니다.");
                    return false;
                }
                NewPlayer.getNewPlayer(target).setIntegerValue("murder", 0);
                player.sendMessage("초기화 완료.");
            break;
            default:
                player.sendMessage("§c존재하지 않는 명령어 입니다.");
        }

        return true;
    }
}
