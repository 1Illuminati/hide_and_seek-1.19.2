package thirteenNight.item.weapon.runner;

import org.bukkit.inventory.ItemStack;
import thirteenNight.item.GameItem;
import thirteenNight.item.skill.AbstractSkill;
import thirteenNight.item.skill.runner.LocationChangeSkill;

public class LocationChange extends AbstractRunnerEvent {
    private static final LocationChangeSkill locationChangeSkill = new LocationChangeSkill();
    @Override
    public AbstractSkill skill1() {
        return locationChangeSkill;
    }

    @Override
    public String getCode() {
        return "locationChange";
    }

    @Override
    public boolean checkItem(ItemStack itemStack) {
        return GameItem.LOCATION_CHANGE.equals(itemStack);
    }
}
