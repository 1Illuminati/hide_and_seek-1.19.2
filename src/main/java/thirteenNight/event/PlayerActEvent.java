package thirteenNight.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import thirteenNight.Game;

public class PlayerActEvent implements Listener {
    @EventHandler
    public void playerDropEvent(org.bukkit.event.player.PlayerDropItemEvent event) {
        Game game = Game.getGame();
        if (game.isStart()) {
            event.getPlayer().sendMessage("§c게임이 시작되었으므로 아이템을 버릴 수 없습니다.");
            event.setCancelled(true);
        }
    }
}
