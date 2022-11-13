package thirteenNight.item.skill.runner;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import thirteenNight.Game;
import thirteenNight.item.skill.AbstractSkill;

public class BlindFoldSkill extends AbstractSkill {
    @Override
    public String getCode() {
        return "blindFoldSkill";
    }

    @Override
    protected boolean skill(NewPlayer newPlayer) {
        Game.getGame().getMurder().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 80, 0));
        newPlayer.sendMessage("우클릭 스킬 사용! 4초간 살인마에게 실명이 걸립니다.");
        return true;
    }

    @Override
    public double getCoolTime() {
        return 30;
    }
}
