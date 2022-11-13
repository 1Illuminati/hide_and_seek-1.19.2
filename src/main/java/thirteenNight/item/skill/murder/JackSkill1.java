package thirteenNight.item.skill.murder;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import thirteenNight.item.skill.AbstractSkill;

public class JackSkill1 extends AbstractSkill {
    @Override
    public String getCode() {
        return "jackSkill1";
    }

    @Override
    protected boolean skill(NewPlayer newPlayer) {
        newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
        newPlayer.sendMessage("우클릭 스킬 사용! 10초간 이동속도가 대폭 증가합니다.");
        return true;
    }

    @Override
    public double getCoolTime() {
        return 30;
    }
}
