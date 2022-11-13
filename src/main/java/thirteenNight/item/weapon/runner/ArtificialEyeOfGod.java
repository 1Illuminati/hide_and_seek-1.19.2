package thirteenNight.item.weapon.runner;

import org.bukkit.inventory.ItemStack;
import thirteenNight.item.GameItem;
import thirteenNight.item.skill.AbstractSkill;
import thirteenNight.item.skill.runner.ArtificialEyeOfGodSkill;

public class ArtificialEyeOfGod extends AbstractRunnerEvent {
    private static final ArtificialEyeOfGodSkill artificialEyeOfGodSkill = new ArtificialEyeOfGodSkill();

    @Override
    public String getCode() {
        return "artificialEyeOfGod";
    }

    @Override
    public boolean checkItem(ItemStack itemStack) {
        return GameItem.ARTIFICIAL_EYE_OF_GOD.equals(itemStack);
    }

    @Override
    public AbstractSkill skill1() {
        return artificialEyeOfGodSkill;
    }
}
