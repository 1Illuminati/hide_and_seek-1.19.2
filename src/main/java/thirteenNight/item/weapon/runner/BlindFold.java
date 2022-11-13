package thirteenNight.item.weapon.runner;

import org.bukkit.inventory.ItemStack;
import thirteenNight.item.GameItem;
import thirteenNight.item.skill.AbstractSkill;
import thirteenNight.item.skill.runner.BlindFoldSkill;

public class BlindFold extends AbstractRunnerEvent {
    private static final BlindFoldSkill blindFoldSkill = new BlindFoldSkill();
    @Override
    public String getCode() {
        return "blindFold";
    }

    @Override
    public boolean checkItem(ItemStack itemStack) {
        return GameItem.BLINDFOLD.equals(itemStack);
    }

    @Override
    public AbstractSkill skill1() {
        return blindFoldSkill;
    }
}
