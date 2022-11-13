package thirteenNight.item.skill.runner;

import doublePlugin.entity.player.NewPlayer;
import thirteenNight.Game;
import thirteenNight.item.skill.AbstractGameSkill;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class MedicalBagSkill extends AbstractGameSkill {
    @Override
    public String getCode() {
        return "healerSkill";
    }

    @Override
    protected boolean skill(NewPlayer newPlayer) {
        Game game = Game.getGame();
        ArrayList<UUID> deadPlayers = game.getDeadPlayer();

        if (deadPlayers.size() == 0) {
            newPlayer.sendMessage("§c죽은 플레이어가 없습니다.");
            return false;
        }

        UUID arrivePlayerUUID = deadPlayers.get(new Random().nextInt(deadPlayers.size()));
        NewPlayer arrivePlayer = NewPlayer.getNewPlayerByUUID(arrivePlayerUUID);
        game.revive(arrivePlayerUUID);
        arrivePlayer.teleport(newPlayer.getLocation());
        newPlayer.sendMessage("우클릭 스킬 사용! 랜덤한 죽은 도망자중 한명이 부활하였습니다.");
        return true;
    }

    @Override
    public double getCoolTime() {
        return 240;
    }
}
