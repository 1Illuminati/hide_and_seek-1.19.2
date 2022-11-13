package thirteenNight.item.skill.murder;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.Location;
import thirteenNight.item.skill.AbstractSkill;

public class RedKillerSkill2 extends AbstractSkill {
    @Override
    public String getCode() {
        return "redKillerSkill2";
    }

    @Override
    protected boolean skill(NewPlayer newPlayer) {
        final Location loc = newPlayer.getLocation();
        boolean check = newPlayer.getBooleanValue(getCode());

        if (!check) {
            newPlayer.sendMessage("쉬프트 좌클릭 사용! 지금 서있는 자리에 투명와드를 박습니다.");
            newPlayer.setLocationValue(getCode(), loc);
            newPlayer.setBooleanValue(getCode(), true);
            newPlayer.setCoolTimeSecond(getCode(), 2);
            return false;
        } else {
            newPlayer.sendMessage("쉬프트 좌클릭 사용! 투명와드로 텔레포트 합니다.");
            newPlayer.teleport(newPlayer.getLocationValue(getCode()));
            newPlayer.setBooleanValue(getCode(), false);
            return true;
        }
    }

    @Override
    public double getCoolTime() {
        return 60;
    }
}
