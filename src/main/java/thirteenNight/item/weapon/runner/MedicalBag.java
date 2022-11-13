package thirteenNight.item.weapon.runner;

import org.bukkit.inventory.ItemStack;
import thirteenNight.item.GameItem;
import thirteenNight.item.skill.AbstractSkill;
import thirteenNight.item.skill.runner.MedicalBagSkill;

public class MedicalBag extends AbstractRunnerEvent {
    private static final AbstractSkill medicalBagSkill = new MedicalBagSkill();

    @Override
    public String getCode() {
        return "medicalBag";
    }

    @Override
    public boolean checkItem(ItemStack itemStack) {
        return GameItem.MEDICAL_BAG.equals(itemStack);
    }


    @Override
    public AbstractSkill skill1() {
        return medicalBagSkill;
    }
}
