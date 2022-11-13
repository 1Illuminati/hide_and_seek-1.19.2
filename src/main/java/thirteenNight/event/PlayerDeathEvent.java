package thirteenNight.event;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import thirteenNight.Game;

public class PlayerDeathEvent implements Listener {

    @EventHandler
    public void playerDeathEvent(org.bukkit.event.entity.PlayerDeathEvent event) {
        Game game = Game.getGame();

        if (game.isStart()) {
            NewPlayer deathPlayer = NewPlayer.getNewPlayer(event.getPlayer());
            NewPlayer killer = NewPlayer.getNewPlayer(deathPlayer.getKiller());
            if (game.checkMurder(killer)) {
                game.deadPlayer(deathPlayer);

                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("§c누군가가 살인마에게 살해당하셨습니다.");
                Bukkit.broadcastMessage("§c현재 남은 플레이어는 " + game.getSurvivalPlayer().size() + "명 입니다.");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("");
            }
        }
    }
}
