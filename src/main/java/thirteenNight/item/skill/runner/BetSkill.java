package thirteenNight.item.skill.runner;

import doublePlugin.entity.player.NewPlayer;
import doublePlugin.scheduler.RunnableEx;
import doublePlugin.scheduler.Scheduler;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import thirteenNight.item.skill.AbstractSkill;

public class BetSkill extends AbstractSkill {
    @Override
    public String getCode() {
        return "betSkill";
    }

    @Override
    protected boolean skill(NewPlayer newPlayer) {
        ItemStack item = newPlayer.getItemInHand();
        item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);

        Scheduler.delayScheduler(new RunnableEx() {
            @Override
            public void function() {
                item.removeEnchantment(Enchantment.KNOCKBACK);
            }
        }, 60);
        newPlayer.sendMessage("우클릭 스킬 사용! 3초간 무기에 밀치기 인챈트 10이 걸립니다.");
        return true;
    }

    @Override
    public double getCoolTime() {
        return 30;
    }
}
