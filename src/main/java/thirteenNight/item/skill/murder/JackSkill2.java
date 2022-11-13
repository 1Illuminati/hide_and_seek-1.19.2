package thirteenNight.item.skill.murder;

import doublePlugin.entity.player.NewPlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import thirteenNight.item.skill.AbstractSkill;

public class JackSkill2 extends AbstractSkill {
    @Override
    public String getCode() {
        return "JackSkill2";
    }

    @Override
    protected boolean skill(NewPlayer newPlayer) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.equals(newPlayer.getPlayer()) || player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) {
                continue;
            }

            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 300, 1));
            newPlayer.sendMessage("쉬프트 좌클릭 사용! 이제 도망자의 위치가 보입니다.");
        }

        return true;
        /*Player target = null;
        double distance = Double.MAX_VALUE;
        Location location = newPlayer.getLocation().clone();

        for (Player player : Bukkit.getOnlinePlayers()) {

            if (player.getName().equals(newPlayer.getName()))
                continue;

            GameMode gameMode = player.getGameMode();
            if (gameMode != GameMode.CREATIVE && gameMode != GameMode.SPECTATOR) {
                double tempDistance = player.getLocation().distance(location);
                if (tempDistance < distance) {
                    target = player;
                    distance = tempDistance;
                }
            }
        }

        if (target == null) {
            newPlayer.sendMessage("§4주변에 플레이어가 없습니다.");
            return;
        }

        location.setDirection(target.getLocation().subtract(newPlayer.getLocation()).toVector());
        List<Vector> vecList = Util.circle(7, 7, 0);

        Location targetLoc = target.getLocation();
        while (true) {
            int rand = new Random().nextInt(vecList.size());
            Vector vec = vecList.get(rand);
            vecList.remove(rand);
            Location check = targetLoc.clone().add(vec);
            if (check.getBlock().isEmpty() && check.add(0,1,0).getBlock().isEmpty() && !check.add(0,-1,0).getBlock().isEmpty()
                    && check.add(0,-1,0).getBlock().getType() != Material.LAVA) {
                targetLoc.add(vec);
                break;
            }

            if (vecList.size() == 0) {
                newPlayer.sendMessage("§4ERROR!!");
                break;
            }
        }

        newPlayer.teleport(targetLoc);*/
    }

    @Override
    public double getCoolTime() {
        return 60;
    }
}
