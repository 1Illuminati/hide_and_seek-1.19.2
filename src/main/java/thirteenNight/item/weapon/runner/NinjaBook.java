package thirteenNight.item.weapon.runner;

import org.bukkit.inventory.ItemStack;
import thirteenNight.item.GameItem;
import thirteenNight.item.skill.AbstractSkill;
import thirteenNight.item.skill.runner.NinjaBookSkill;

public class NinjaBook extends AbstractRunnerEvent {
    private static final NinjaBookSkill skill = new NinjaBookSkill();
    @Override
    public String getCode() {
        return "ninjaBook";
    }

    @Override
    public boolean checkItem(ItemStack itemStack) {
        return GameItem.NINJA_BOOK.equals(itemStack);
    }

    @Override
    public AbstractSkill skill1() {
        return skill;
    }
}
