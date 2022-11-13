package thirteenNight.item.skill.runner;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import thirteenNight.item.skill.AbstractSkill;

public class InvincibleShieldSkill extends AbstractSkill {
    @Override
    public String getCode() {
        return "invincibleShieldSkill";
    }

    @Override
    protected boolean skill(NewPlayer newPlayer) {
        newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 160, 4));
        newPlayer.sendMessage("우클릭 스킬 사용! 8초동안 무적이 됩니다.");
        return true;
    }

    @Override
    public double getCoolTime() {
        return 30;
    }
}
