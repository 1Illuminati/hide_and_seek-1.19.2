package thirteenNight.item.skill.runner;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import thirteenNight.item.skill.AbstractSkill;

public class CocaineSkill extends AbstractSkill {
    @Override
    public String getCode() {
        return "marathonSkill";
    }

    @Override
    protected boolean skill(NewPlayer newPlayer) {
        newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 140, 1));
        newPlayer.sendMessage("우클릭 스킬 사용! 7초간 기분이 좋아집니다. (신속 II)");
        return true;
    }

    @Override
    public double getCoolTime() {
        return 30;
    }
}
