package io.michaelsimmons.sensitivityfinder.commands;

import io.michaelsimmons.sensitivityfinder.utils.targetManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class AnalyzeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {

        if (!s.hasPermission("sensitivity.admin")) {
            s.sendMessage(ChatColor.RED + "Error: You do not have permission to use this command.");
        }

        if (args.length != 1) {
            s.sendMessage(ChatColor.RED + "Error: You must specify a player to analyze.");
            s.sendMessage(ChatColor.RED + "Usage: /sensitivity <player>");
            return false;
        }

        if (Bukkit.getPlayer(args[0]) == null) {
            s.sendMessage(ChatColor.RED + "Error: Player not found.");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        assert target != null;
        if (targetManager.isTarget(target.getName())) {
            s.sendMessage(ChatColor.RED + "Stopping sensitivity analysis on " + args[0] + ".");
            targetManager.removeTarget(target.getName());
        } else {
            targetManager.addTarget(target.getName());
            s.sendMessage(ChatColor.GREEN + "Starting sensitivity analysis on " + args[0] + ".");

        }

        return false;
    }
}
