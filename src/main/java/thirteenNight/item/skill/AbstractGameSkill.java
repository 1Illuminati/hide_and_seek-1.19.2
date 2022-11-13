package thirteenNight.item.skill;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.GameMode;
import thirteenNight.Game;

public abstract class AbstractGameSkill extends AbstractSkill {
    public boolean run(NewPlayer newPlayer) {
        if (newPlayer.getGameMode() == GameMode.SPECTATOR) {
            return false;
        }

        Game game = Game.getGame();

        if (!game.isStart()) {
            newPlayer.sendMessage("§c게임 플레이 도중에만 사용가능한 스킬.");
            return false;
        }

        if (!newPlayer.checkCoolTime(getCode())) {
            newPlayer.sendMessage("아직 쿨타임 입니다 : " + newPlayer.getLessCoolTime(getCode()));
            return false;
        }

        boolean check = skill(newPlayer);

        if (check) {
            newPlayer.setCoolTimeSecond(getCode(), getCoolTime());
        }

        return check;
    }
}
