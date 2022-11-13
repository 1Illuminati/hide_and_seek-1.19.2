package thirteenNight.item;

import doublePlugin.entity.player.NewPlayer;
import doublePlugin.item.ItemEvent;
import org.bukkit.inventory.ItemStack;
import thirteenNight.Game;

public class LocHelperEvent extends ItemEvent {
    @Override
    public String getCode() {
        return "locHelper";
    }

    @Override
    public boolean leftClick(NewPlayer newPlayer) {
        return false;
    }

    @Override
    public boolean rightClick(NewPlayer newPlayer) {
        Game game = Game.getGame();
        game.setSpawn(newPlayer.getLocation());
        return true;
    }

    @Override
    public boolean swapHand(NewPlayer newPlayer) {
        return false;
    }

    @Override
    public boolean dropItem(NewPlayer newPlayer) {
        return false;
    }

    @Override
    public boolean shiftLeftClick(NewPlayer newPlayer) {
        return false;
    }

    @Override
    public boolean shiftRightClick(NewPlayer newPlayer) {
        return false;
    }

    @Override
    public boolean shiftSwapHand(NewPlayer newPlayer) {
        return false;
    }

    @Override
    public boolean shiftDropItem(NewPlayer newPlayer) {
        return false;
    }

    @Override
    public boolean checkItem(ItemStack itemStack) {
        return itemStack.equals(GameItem.LOC_HELPER);
    }
}
