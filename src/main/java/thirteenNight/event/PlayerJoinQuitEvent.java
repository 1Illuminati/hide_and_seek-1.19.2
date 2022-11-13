package thirteenNight.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import thirteenNight.Game;

public class PlayerJoinQuitEvent implements Listener {
    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event) {
        Game game = Game.getGame();
    }

    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent event) {
        Game game = Game.getGame();

    }
}
