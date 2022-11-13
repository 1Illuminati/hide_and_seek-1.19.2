package thirteenNight.item.weapon.runner;

import doublePlugin.entity.player.NewPlayer;
import doublePlugin.item.ItemEvent;
import thirteenNight.item.skill.AbstractSkill;

public abstract class AbstractRunnerEvent extends ItemEvent {
    @Override
    public boolean leftClick(NewPlayer newPlayer) {
        return false;
    }

    @Override
    public boolean rightClick(NewPlayer newPlayer) {
        skill1().run(newPlayer);
        return false;
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
        skill1().run(newPlayer);
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

    public abstract AbstractSkill skill1();
}
