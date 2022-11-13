package thirteenNight.item.skill.runner;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import thirteenNight.Game;
import thirteenNight.item.GameItem;
import thirteenNight.item.skill.AbstractGameSkill;

public class KoreaManSkill extends AbstractGameSkill {
    @Override
    public String getCode() {
        return "koreaManSkill";
    }

    @Override
    protected boolean skill(NewPlayer newPlayer) {
        Game game = Game.getGame();
        if (game.getSurvivalPlayer().size() <= 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendTitle("§6§l[ §f마지막 §e§l희망 §6§l]", "");
            }

            newPlayer.getInventory().clear();
            newPlayer.getInventory().addItem(GameItem.runnerWeapon.toArray(new ItemStack[0]));

            return true;
        } else {
            newPlayer.sendMessage("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
            return false;
        }
    }

    @Override
    public double getCoolTime() {
        return 9999;
    }

}
