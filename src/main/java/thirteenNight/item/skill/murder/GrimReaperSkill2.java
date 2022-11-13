package thirteenNight.item.skill.murder;

import doublePlugin.entity.player.NewPlayer;
import doublePlugin.scheduler.RunnableEx;
import doublePlugin.scheduler.Scheduler;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import thirteenNight.ThirteenNight;
import thirteenNight.item.skill.AbstractSkill;

public class GrimReaperSkill2 extends AbstractSkill {
    @Override
    public String getCode() {
        return "grimReaperSkill2";
    }

    @Override
    protected boolean skill(NewPlayer newPlayer) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.hidePlayer(ThirteenNight.getPlugin(), newPlayer.getPlayer());
        }
        newPlayer.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, newPlayer.getLocation().clone().add(0, 1, 0), 1000, 0, 0, 0, 0.1);
        newPlayer.sendMessage("쉬프트 좌클릭 스킬 사용! 이제 그 누구도 당신을 볼수 없습니다.");

        Scheduler.delayScheduler(new RunnableEx() {
            @Override
            public void function() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.showPlayer(ThirteenNight.getPlugin(), newPlayer.getPlayer());
                }
                newPlayer.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, newPlayer.getLocation().clone().add(0, 1, 0), 1000, 0, 0, 0, 0.1);
            }
        }, 300);
        return true;
    }

    @Override
    public double getCoolTime() {
        return 60;
    }
}
