package thirteenNight.event;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import thirteenNight.Game;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerChatEvent implements Listener {

    @EventHandler
    public void playerChatEvent(AsyncPlayerChatEvent event) {
        Game game = Game.getGame();
        if (game.isStart()) {
            NewPlayer chatPlayer = NewPlayer.getNewPlayer(event.getPlayer());

            if (game.playerIsSpecter(chatPlayer)) {
                ArrayList<UUID> list = (ArrayList<UUID>) game.getDeadPlayer().clone();
                list.addAll(game.getSpecterPlayer());
                for (UUID uuid : list) {
                    Bukkit.getPlayer(uuid).sendMessage("§7§l[§f " + chatPlayer.getName() + " §7§l]  §f" + event.getMessage());
                }
            } else {
                chatPlayer.sendMessage("§c살인마와 도망자는 채팅을 칠수도 볼수도 없습니다.");
            }

            event.setCancelled(true);
        }
    }
}
