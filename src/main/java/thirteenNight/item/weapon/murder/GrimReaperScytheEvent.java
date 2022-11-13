package thirteenNight.item.weapon.murder;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.inventory.ItemStack;
import thirteenNight.item.GameItem;
import thirteenNight.item.skill.AbstractSkill;
import thirteenNight.item.skill.murder.GrimReaperSkill1;
import thirteenNight.item.skill.murder.GrimReaperSkill2;

public class GrimReaperScytheEvent extends AbstractMurderEvent {
    private static final GrimReaperSkill1 grimReaperSkill1 = new GrimReaperSkill1();
    private static final GrimReaperSkill2 grimReaperSkill2 = new GrimReaperSkill2();
    @Override
    public AbstractSkill skill1(NewPlayer newPlayer) {
        return grimReaperSkill1;
    }

    @Override
    public AbstractSkill skill2(NewPlayer newPlayer) {
        return grimReaperSkill2;
    }

    @Override
    public String getCode() {
        return "grimReaperScythe";
    }

    @Override
    public boolean checkItem(ItemStack itemStack) {
        return itemStack.equals(GameItem.GRIM_REAPER_SCYTHE);
    }
}
