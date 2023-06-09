package io.michaelsimmons.sensitivityfinder;

import io.michaelsimmons.sensitivityfinder.commands.AnalyzeCommand;
import io.michaelsimmons.sensitivityfinder.listeners.SensitivityListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SensitivityFinder extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new SensitivityListener(), this);
        this.getCommand("sensitivity").setExecutor(new AnalyzeCommand());
        System.out.println("[SensitivityFinder] Enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println("[SensitivityFinder] Disabled.");
    }
}
