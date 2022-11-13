package thirteenNight.item.skill.runner;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.Location;
import thirteenNight.Game;
import thirteenNight.item.skill.AbstractGameSkill;

public class LocationChangeSkill extends AbstractGameSkill {
    @Override
    public String getCode() {
        return "locationChange";
    }

    @Override
    protected boolean skill(NewPlayer newPlayer) {
        Game game = Game.getGame();
        NewPlayer murder = game.getMurder();
        Location temp = murder.getLocation().clone();
        murder.teleport(newPlayer.getLocation());
        newPlayer.teleport(temp);
        newPlayer.sendMessage("우클릭 스킬 사용! 술래와 나의 위치가 바뀌었습니다.");
        return true;
    }

    @Override
    public double getCoolTime() {
        return 45;
    }

}
