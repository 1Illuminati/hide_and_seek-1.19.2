package thirteenNight;

import doublePlugin.entity.player.NewPlayer;
import doublePlugin.scheduler.RunnableEx;
import doublePlugin.scheduler.Scheduler;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import thirteenNight.item.GameItem;

import java.util.*;

public class Game implements Runnable {
    private static Game game;
    private static final NamespacedKey bossBarKey = new NamespacedKey(ThirteenNight.getPlugin(), "bossBar");

    public static Game getGame() {
        if (game == null) {
            game = new Game();
        }

        return game;
    }

    private UUID gameCode;
    private boolean isStart = false;
    public Location spawn;
    private int chestCount = 0;
    private final ArrayList<UUID> joinPlayer = new ArrayList<>();
    private UUID murder;
    private World world;
    private int time = 300;

    private KeyedBossBar timer;

    private final HashMap<GameRule, Object> gameRuleMap = new HashMap<>();
    private final ArrayList<UUID> survivePlayer = new ArrayList<>();
    private final ArrayList<UUID> deadPlayer = new ArrayList<>();
    private final ArrayList<UUID> specterPlayer = new ArrayList<>();

    private Game() {

    }

    public ArrayList<UUID> getSurvivalPlayer() {
        return this.survivePlayer;
    }

    public ArrayList<UUID> getDeadPlayer() {
        return this.deadPlayer;
    }

    public void setTime(int second) {
        this.time = second;
    }

    public ArrayList<UUID> getSpecterPlayer() {
        return this.specterPlayer;
    }

    public int getChestCount() {
        return this.chestCount;
    }

    public void setChestCount(int chestCount) {
        this.chestCount = chestCount;
    }

    public void setSpawn(Location loc) {
        this.spawn = loc;
    }

    public Location getSpawn() {
        return this.spawn;
    }

    public void addPlayer(NewPlayer player) {
        joinPlayer.add(player.getUniqueId());
    }

    public boolean containPlayer(NewPlayer player) {
        return joinPlayer.contains(player.getUniqueId());
    }

    public void removePlayer(NewPlayer player) {
    	joinPlayer.remove(player.getUniqueId());
    }

    public ArrayList<UUID> getJoinPlayer() {

        return joinPlayer;
    }

    public NewPlayer getMurder() {
        return NewPlayer.getNewPlayerByUUID(this.murder);
    }

    public boolean isStart() {
        return this.isStart;
    }

    public boolean checkMurder(NewPlayer player) {
        return this.murder.equals(player.getUniqueId());
    }

    public void setMurder(UUID uuid) {
        this.murder = uuid;
    }

    public void deadPlayer(NewPlayer player) {
        UUID uuid = player.getUniqueId();
        if (!this.isStart) {
            throw new IllegalArgumentException("Game is not start");
        }

        if (this.deadPlayer.contains(uuid)) {
            throw new IllegalArgumentException(player.getName() + " is already dead");
        }

        if (this.specterPlayer.contains(uuid)) {
            throw new IllegalArgumentException(player.getName() + " is specter");
        }

        player.setGameMode(GameMode.SPECTATOR);
        this.survivePlayer.remove(uuid);
        this.deadPlayer.add(uuid);

        ThirteenNight.sendLog("" + this.survivePlayer.size());
        ThirteenNight.sendLog(this.survivePlayer.toString());
        if (survivePlayer.size() == 0) {
            this.victoryMurder();
        }
    }

    public void revive(NewPlayer player) {
        UUID uuid = player.getUniqueId();
        if (!this.deadPlayer.contains(uuid)) {
            throw new IllegalArgumentException("This player is not dead or specter player");
        }

        this.deadPlayer.remove(uuid);
        this.survivePlayer.add(uuid);
        player.setGameMode(GameMode.ADVENTURE);
    }

    public void revive(UUID uuid) {
        revive(NewPlayer.getNewPlayerByUUID(uuid));
    }

    public void victoryMurder() {
        for (UUID uuid : this.specterPlayer) {
            NewPlayer player = NewPlayer.getNewPlayerByUUID(uuid);
            player.sendTitle("§c§l[ §f살인마가 승리하였습니다! §c§l]", "");
        }

        NewPlayer.getNewPlayerByUUID(this.murder).sendTitle("§a§l[ §f승리하셨습니다! §a§l]", "");

        for (UUID uuid : this.deadPlayer) {
            NewPlayer player = NewPlayer.getNewPlayerByUUID(uuid);
            player.sendTitle("§c§l[ §f패배하셨습니다 §c§l]", "");
        }
        stop();
    }

    public void victoryRunner() {
        for (UUID uuid : this.specterPlayer) {
            NewPlayer player = NewPlayer.getNewPlayerByUUID(uuid);
            player.sendTitle("§c§l[ §f도망자들이 승리하였습니다! §c§l]", "");
        }

        NewPlayer.getNewPlayerByUUID(this.murder).sendTitle("§c§l[ §f패배하셨습니다 §c§l]", "");

        for (UUID uuid : this.deadPlayer) {
            NewPlayer player = NewPlayer.getNewPlayerByUUID(uuid);
            player.sendTitle("§a§l[ §f승리하셨습니다! §a§l]", "");
        }
        for (UUID uuid : this.survivePlayer) {
            NewPlayer player = NewPlayer.getNewPlayerByUUID(uuid);
            player.sendTitle("§a§l[ §f승리하셨습니다! §a§l]", "");
        }
        stop();
    }

    private <T> void setGameRule(GameRule<T> rule, T value) {
        this.gameRuleMap.put(rule, world.getGameRuleValue(rule));
        world.setGameRule(rule, value);
    }

    public boolean playerIsSpecter(NewPlayer player) {
        return this.deadPlayer.contains(player.getUniqueId()) || this.specterPlayer.contains(player.getUniqueId());
    }

    public void stop() {
        if (!isStart) {
            throw new IllegalArgumentException("Game is not start");
        }

        for (GameRule rule : this.gameRuleMap.keySet()) {
            this.world.setGameRule(rule, this.gameRuleMap.get(rule));
        }

        this.gameRuleMap.clear();
        this.timer.removeAll();

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:effect clear @a");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:clear @a");
        Team runner = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("runner");
        for (UUID uuid : joinPlayer) {
            runner.removePlayer(Bukkit.getOfflinePlayer(uuid));
        }
        this.gameCode = null;
        this.murder = null;
        this.joinPlayer.clear();
        this.survivePlayer.clear();
        this.deadPlayer.clear();
        this.specterPlayer.clear();
        this.isStart = false;
        this.timer = null;
        ThirteenNight.sendLog("Game Stop");
    }

    public void run() {
        if (spawn == null) {
            throw new NullPointerException("spawn is null");
        }

        if (joinPlayer.size() < 2) {
            throw new IllegalArgumentException("join player must be more than 2");
        }

        ThirteenNight.sendLog("Game Starting...");
        timer = Bukkit.createBossBar(Game.bossBarKey, "제한시간동안 살인마로 부터 살아남으세요!", BarColor.RED, BarStyle.SOLID);

        this.gameCode = UUID.randomUUID();
        Scheduler.repeatDelayScheduler(new RunnableEx() {
            private final UUID code = gameCode;
            @Override
            public void function() {
                if (!isStart || timer == null) {
                    stop();
                }

                try {
                    timer.setProgress(((double) this.getRepeat() - this.getCount()) / this.getRepeat());
                } catch (NullPointerException e) {
                    stop();
                }

                if (this.getCount() >= this.getRepeat() - 1) {
                    if (gameCode != null) {
                        if (code.equals(gameCode)) {
                            victoryRunner();
                        }
                    }
                }
            }
        }, 20, this.time);

        isStart = true;
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle("§c§l[ §f게임이 시작됩니다 §c§l]", "");
            player.setGameMode(GameMode.SPECTATOR);
            this.specterPlayer.add(player.getUniqueId());
            timer.addPlayer(player);
        }
        this.world = spawn.getWorld();

        this.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        this.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        this.setGameRule(GameRule.FALL_DAMAGE, false);
        this.setGameRule(GameRule.NATURAL_REGENERATION, false);
        this.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        this.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
        this.setGameRule(GameRule.KEEP_INVENTORY, true);

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Team runner = board.getTeam("runner");
        if (runner == null) {
            runner = board.registerNewTeam("runner");
            runner.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        }

        if (murder == null) {
            murder = joinPlayer.get(new Random().nextInt(joinPlayer.size()));
        }

        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            Scheduler.delayScheduler(new RunnableEx() {
                @Override
                public void function() {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle("§c§l[ §f" + finalI + " §c§l]", "");
                    }
                }
            }, 80 - (i * 20));
        }

        ArrayList<ItemStack> runnerWeapon = (ArrayList<ItemStack>) GameItem.runnerWeapon.clone();
        Team finalRunner = runner;
        Scheduler.delayScheduler(new RunnableEx() {
            @Override
            public void function() {
                for (UUID uuid : joinPlayer) {
                    NewPlayer player = NewPlayer.getNewPlayerByUUID(uuid);
                    player.setGameMode(GameMode.ADVENTURE);
                    player.getInventory().clear();
                    player.teleport(spawn);
                    specterPlayer.remove(uuid);
                    finalRunner.addPlayer(player.getPlayer());

                    if (murder.equals(uuid)) {
                        player.getInventory().addItem(GameItem.murderWeapon.get(rand(GameItem.murderWeapon.size())));
                        player.setIntegerValue("murder", 0);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 5));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 400, 130));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 5));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 400, 5));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 5));
                        player.sendTitle("§c§l[ §f당신은 §4살인마 §f입니다 §c§l]", "§7지금부터 모든 도망자들을 죽이세요");
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000, 4));
                        continue;
                    }

                    ItemStack item = runnerWeapon.get(rand(runnerWeapon.size()));
                    player.getInventory().addItem(item);
                    runnerWeapon.remove(item);
                    player.sendTitle("§c§l[ §f당신은 도망자 입니다 §c§l]", "§7지금부터 살인마로부터 살아남으세요");
                    survivePlayer.add(uuid);
                }

                for (UUID uuid : specterPlayer) {
                    NewPlayer player = NewPlayer.getNewPlayerByUUID(uuid);
                    player.sendTitle("§8§l[ §f당신은 §7관전자 입니다 §8§l]", "§7지금부터 팝콘을 뜯으세요");
                }
            }
        }, 80);

        Scheduler.delayScheduler(new RunnableEx() {
            @Override
            public void function() {
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("§c§l술래가 풀려났습니다!!");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("");
            }
        }, 480);
    }

    public int rand(int bound) {
        return new Random().nextInt(bound);
    }

    private int rand(int a, int b) {
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        return rand(max - min + 1) + min;
    }
}
