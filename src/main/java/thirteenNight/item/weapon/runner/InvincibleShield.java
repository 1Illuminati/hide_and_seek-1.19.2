package thirteenNight.item.weapon.runner;

import org.bukkit.inventory.ItemStack;
import thirteenNight.item.GameItem;
import thirteenNight.item.skill.AbstractSkill;
import thirteenNight.item.skill.runner.InvincibleShieldSkill;

public class InvincibleShield extends AbstractRunnerEvent {
    private static final InvincibleShieldSkill invincibleShieldSkill = new InvincibleShieldSkill();
    @Override
    public String getCode() {
        return "invincibleShield";
    }

    @Override
    public boolean checkItem(ItemStack itemStack) {
        return GameItem.INVINCIBLE_SHIELD.equals(itemStack);
    }

    @Override
    public AbstractSkill skill1() {
        return invincibleShieldSkill;
    }
}
