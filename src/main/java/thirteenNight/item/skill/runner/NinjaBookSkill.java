package thirteenNight.item.skill.runner;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import thirteenNight.item.skill.AbstractSkill;

public class NinjaBookSkill extends AbstractSkill {
    @Override
    public String getCode() {
        return "ninjaBookSkill";
    }

    @Override
    protected boolean skill(NewPlayer newPlayer) {
        newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 400, 0));
        newPlayer.sendMessage("우클릭 스킬 사용! 20초동안 투명화 상태가 됩니다.");
        return true;
    }

    @Override
    public double getCoolTime() {
        return 50;
    }
}
