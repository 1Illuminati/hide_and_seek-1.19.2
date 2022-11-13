package thirteenNight.item.weapon.runner;

import org.bukkit.inventory.ItemStack;
import thirteenNight.item.GameItem;
import thirteenNight.item.skill.AbstractSkill;
import thirteenNight.item.skill.runner.KoreaManSkill;

public class KoreaMan extends AbstractRunnerEvent {
    private static final KoreaManSkill koreaManSkill = new KoreaManSkill();

    @Override
    public String getCode() {
        return "koreanMan";
    }

    @Override
    public boolean checkItem(ItemStack itemStack) {
        return GameItem.KOREA_MAN.equals(itemStack);
    }

    @Override
    public AbstractSkill skill1() {
        return koreaManSkill;
    }
}
