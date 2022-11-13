package thirteenNight.item.weapon.murder;

import doublePlugin.entity.player.NewPlayer;
import doublePlugin.item.ItemEvent;
import thirteenNight.item.skill.AbstractSkill;

public abstract class AbstractMurderEvent extends ItemEvent {
    @Override
    public boolean leftClick(NewPlayer newPlayer) {
        return false;
    }

    @Override
    public boolean rightClick(NewPlayer newPlayer) {
        skill1(newPlayer).run(newPlayer);
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
        if (this.getSkill2UseNum(newPlayer) >= 5) {
            newPlayer.sendMessage("더이상 사용할 수 없습니다.");
            return false;
        }

        if (skill2(newPlayer).run(newPlayer)) {
            newPlayer.addIntegerValue("murder", 1);
            newPlayer.sendMessage("현재 " + this.getSkill2UseNum(newPlayer) + "번 사용하셨습니다.");
        }
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

    protected int getSkill2UseNum(NewPlayer newPlayer) {
        return newPlayer.getIntegerValue("murder");
    }

    public abstract AbstractSkill skill1(NewPlayer newPlayer);

    public abstract AbstractSkill skill2(NewPlayer newPlayer);
}
