package thirteenNight.item.weapon.runner;

import org.bukkit.inventory.ItemStack;
import thirteenNight.item.GameItem;
import thirteenNight.item.skill.AbstractSkill;
import thirteenNight.item.skill.runner.BetSkill;

public class Bet extends AbstractRunnerEvent {
    private final static BetSkill betSkill = new BetSkill();
    @Override
    public AbstractSkill skill1() {
        return betSkill;
    }

    @Override
    public String getCode() {
        return "bet";
    }

    @Override
    public boolean checkItem(ItemStack itemStack) {
        return itemStack.equals(GameItem.BET);
    }
}
