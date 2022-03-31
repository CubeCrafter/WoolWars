package me.cubecrafter.woolwars;

import lombok.Getter;
import me.cubecrafter.woolwars.commands.CommandManager;
import me.cubecrafter.woolwars.config.FileManager;
import me.cubecrafter.woolwars.core.GameManager;
import me.cubecrafter.woolwars.core.ScoreboardAdapter;
import me.cubecrafter.woolwars.database.Database;
import me.cubecrafter.woolwars.database.MongoManager;
import me.cubecrafter.woolwars.hooks.PlaceholderHook;
import me.cubecrafter.woolwars.libs.Metrics;
import me.cubecrafter.woolwars.listeners.MenuListener;
import me.cubecrafter.woolwars.utils.TextUtil;
import me.cubecrafter.woolwars.utils.scoreboard.Assemble;
import me.cubecrafter.woolwars.utils.scoreboard.AssembleStyle;
import org.bukkit.plugin.java.JavaPlugin;

public final class WoolWars extends JavaPlugin {

    @Getter private static WoolWars instance;
    @Getter private GameManager gameManager;
    @Getter private FileManager fileManager;
    @Getter private CommandManager commandManager;
    @Getter private Database database;
    @Getter private MongoManager mongoManager;
    @Getter private Assemble scoreboard;

    @Override
    public void onEnable() {
        instance = this;

        new Metrics(this, 14788);
        registerHooks();

        fileManager = new FileManager();

        database = new Database();

        gameManager = new GameManager();

        commandManager = new CommandManager(this);

        getServer().getPluginManager().registerEvents(new MenuListener(), this);

        // mongoManager = new MongoManager();
        // mongoManager.load();
        setupScoreboard();
    }

    @Override
    public void onDisable() {
        database.close();
        getServer().getScheduler().cancelTasks(this);
    }

    private void registerHooks() {
        if (isPAPIEnabled()) {
            new PlaceholderHook().register();
            TextUtil.info("Hooked into PlaceholderAPI!");
        }
        if (isVaultEnabled()) {
            // TODO Vault Hook
            TextUtil.info("Hooked into Vault!");
        }
    }

    public boolean isPAPIEnabled() {
        return getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");
    }

    public boolean isVaultEnabled() {
        return getServer().getPluginManager().isPluginEnabled("Vault");
    }

    public void setupScoreboard(){
        scoreboard = new Assemble(this, new ScoreboardAdapter());
        scoreboard.setTicks(2);
        scoreboard.setAssembleStyle(AssembleStyle.MODERN);
    }
}
