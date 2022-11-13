package thirteenNight.item.skill.murder;

import doublePlugin.entity.ArmorStandBuilder;
import doublePlugin.entity.player.NewPlayer;
import doublePlugin.scheduler.RunnableEx;
import doublePlugin.scheduler.Scheduler;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import thirteenNight.Game;
import thirteenNight.item.skill.AbstractSkill;

public class RedKillerSkill1 extends AbstractSkill {
    @Override
    public String getCode() {
        return "redKillerSkill1";
    }

    @Override
    protected boolean skill(NewPlayer newPlayer) {
        if (newPlayer.getIntegerValue(getCode()) >= 3) {
            newPlayer.sendMessage("§c함정은 3개 이상 설치가 불가능합니다.");
            newPlayer.removeCoolTime(getCode());
            return false;
        }

        ArmorStand armorStand = new ArmorStandBuilder(newPlayer.getLocation()).setGravity(false).setInvulnerable(true).setAI(false).make();
        armorStand.setInvisible(true);
        newPlayer.addIntegerValue(getCode(), 1);
        newPlayer.sendMessage("우클릭 사용! 지금 당신이 서있는 자리에 함정이 설치되었습니다!");
        Scheduler.infiniteRepeatScheduler(new RunnableEx() {
            private final Player player = newPlayer.getPlayer();
            public void remove() {
                armorStand.remove();
                stop();
                newPlayer.addIntegerValue(getCode(), -1);
            }

            @Override
            public void function() {
                for (Entity entity : armorStand.getNearbyEntities(1, 1, 1)) {
                    if (entity instanceof Player) {
                        NewPlayer newPlayer = NewPlayer.getNewPlayer((Player) entity);
                        GameMode gameMode = newPlayer.getGameMode();
                        if (gameMode != GameMode.SPECTATOR && gameMode != GameMode.CREATIVE && !player.getUniqueId().equals(newPlayer.getUniqueId())) {
                            Location loc = newPlayer.getLocation();

                            newPlayer.sendTitle("§4§l[ §fㅋㅋㅋ §4§l]", "");
                            newPlayer.spawnParticle(Particle.EXPLOSION_HUGE, newPlayer.getLocation(), 1);
                            newPlayer.playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 10, 10);
                            newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 400, 0));
                            newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 2));
                            newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 400, 0));
                            newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 400, 0));
                            newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 0));

                            for (int i = 0; i < 15; i++) {
                                player.sendMessage("§c누군가가 함정에 걸렸습니다! x " + loc.getBlockX() + " y " + loc.getBlockY() + " z " + loc.getBlockZ());
                            }

                            remove();
                        }
                    }
                }



                if (!player.isOnline() || !Game.getGame().isStart()) {
                    remove();
                }
            }
        }, 0, 2);
        return true;
    }

    @Override
    public double getCoolTime() {
        return 30;
    }
}
