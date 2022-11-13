package thirteenNight.item.weapon.murder;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.inventory.ItemStack;
import thirteenNight.item.GameItem;
import thirteenNight.item.skill.AbstractSkill;
import thirteenNight.item.skill.murder.RedKillerSkill1;
import thirteenNight.item.skill.murder.RedKillerSkill2;

public class RedKillerDaggerEvent extends AbstractMurderEvent {
    private static final RedKillerSkill1 redKillerSkill1 = new RedKillerSkill1();
    private static final RedKillerSkill2 redKillerSkill2 = new RedKillerSkill2();

    @Override
    public String getCode() {
        return "redKillerDagger";
    }

    @Override
    public boolean checkItem(ItemStack itemStack) {
        return itemStack.equals(GameItem.RED_KILLER_DAGGER);
    }

    @Override
    public AbstractSkill skill1(NewPlayer newPlayer) {
        return redKillerSkill1;
    }

    @Override
    public AbstractSkill skill2(NewPlayer newPlayer) {
        return redKillerSkill2;
    }
}
