package thirteenNight.item.weapon.runner;

import org.bukkit.inventory.ItemStack;
import thirteenNight.item.GameItem;
import thirteenNight.item.skill.AbstractSkill;
import thirteenNight.item.skill.runner.CocaineSkill;

public class Cocaine extends AbstractRunnerEvent {
    private static final CocaineSkill cocaineSkill = new CocaineSkill();
    @Override
    public String getCode() {
        return "cocaine";
    }

    @Override
    public boolean checkItem(ItemStack itemStack) {
        return GameItem.COCAINE.equals(itemStack);
    }

    @Override
    public AbstractSkill skill1() {
        return cocaineSkill;
    }
}
