package thirteenNight.item.skill.runner;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import thirteenNight.Game;
import thirteenNight.item.skill.AbstractGameSkill;

public class ArtificialEyeOfGodSkill extends AbstractGameSkill {
    @Override
    public String getCode() {
        return "artificialEyeOfGodSkill";
    }

    @Override
    protected boolean skill(NewPlayer newPlayer) {
        Game.getGame().getMurder().addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 200, 0));
        newPlayer.sendMessage("우클릭 스킬 사용! 10초간 살인마에게 발광이 걸립니다.");
        return true;
    }

    @Override
    public double getCoolTime() {
        return 30;
    }
}
