package thirteenNight.item.weapon.murder;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.inventory.ItemStack;
import thirteenNight.item.GameItem;
import thirteenNight.item.skill.AbstractSkill;
import thirteenNight.item.skill.murder.JackSkill1;
import thirteenNight.item.skill.murder.JackSkill2;

public class JackAxeEvent extends AbstractMurderEvent {
    private static final JackSkill1 jackSkill1 = new JackSkill1();
    private static final JackSkill2 jackSkill2 = new JackSkill2();
    @Override
    public String getCode() {
        return "jackAxe";
    }

    @Override
    public boolean checkItem(ItemStack itemStack) {
        return itemStack.equals(GameItem.JACK_AXE);
    }

    @Override
    public AbstractSkill skill1(NewPlayer newPlayer) {
        return jackSkill1;
    }

    @Override
    public AbstractSkill skill2(NewPlayer newPlayer) {
        return jackSkill2;
    }
}
