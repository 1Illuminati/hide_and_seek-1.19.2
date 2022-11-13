package thirteenNight.item.skill;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.GameMode;

public abstract class AbstractSkill {
    public boolean run(NewPlayer newPlayer) {
        if (newPlayer.getGameMode() == GameMode.SPECTATOR) {
            return false;
        }

        if (!newPlayer.checkCoolTime(getCode())) {
            newPlayer.sendMessage("아직 쿨타임 입니다 : " + newPlayer.getLessCoolTime(getCode()));
            return false;
        }

        newPlayer.setCoolTimeSecond(getCode(), getCoolTime());
        return skill(newPlayer);
    }

    public abstract String getCode();

    protected abstract boolean skill(NewPlayer newPlayer);

    public abstract double getCoolTime();
}
