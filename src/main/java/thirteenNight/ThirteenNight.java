package thirteenNight;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import thirteenNight.command.AbstractCommand;
import thirteenNight.command.commands.GameCommand;
import thirteenNight.command.commands.WeaponCommand;
import thirteenNight.event.PlayerActEvent;
import thirteenNight.event.PlayerChatEvent;
import thirteenNight.event.PlayerDeathEvent;
import thirteenNight.item.LocHelperEvent;
import thirteenNight.item.weapon.murder.GrimReaperScytheEvent;
import thirteenNight.item.weapon.murder.JackAxeEvent;
import thirteenNight.item.weapon.murder.RedKillerDaggerEvent;
import thirteenNight.item.weapon.runner.*;

public final class ThirteenNight extends JavaPlugin implements Listener {
    private static ThirteenNight instance;
    public static final String PluginName = "ThirteenNight";

    @Override
    public void onEnable() {
        instance = this;
        setCommand();
        setEvent();
        new JackAxeEvent();
        new RedKillerDaggerEvent();
        new GrimReaperScytheEvent();
        new LocHelperEvent();
        new ArtificialEyeOfGod();
        new Bet();
        new BlindFold();
        new Cocaine();
        new InvincibleShield();
        new KoreaMan();
        new MedicalBag();
        new NinjaBook();
        new LocationChange();
    }

    @Override
    public void onDisable() {
        Game game = Game.getGame();
        if (game.isStart()) {
            game.stop();
        }
    }

    public static ThirteenNight getPlugin() {
        return instance;
    }

    private void setEvent() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new PlayerDeathEvent(), this);
        pm.registerEvents(new PlayerChatEvent(), this);
        pm.registerEvents(new PlayerActEvent(), this);
        sendLog("All Event Registered");
    }

    private void registerCommand(AbstractCommand command) {
        this.getCommand(command.getName()).setExecutor(command);
    }

    private void setCommand() {
        this.registerCommand(new WeaponCommand());
        this.registerCommand(new GameCommand());
        sendLog("All Command Registered");
    }

    public static void sendLog(String message) {
        Bukkit.getConsoleSender().sendMessage("[" + PluginName + "] " + message);
    }
}
