package io.michaelsimmons.sensitivityfinder.listeners;

import io.michaelsimmons.sensitivityfinder.utils.mathUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import io.michaelsimmons.sensitivityfinder.utils.targetManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SensitivityListener implements Listener {


    targetManager tm = new targetManager();

    private float prevPitchDelta = 0;
    private float currentPitchDelta = 0;

    HashMap<String, ArrayList<Double>> avgSensitivity = new HashMap<>();


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (tm.isTarget(e.getPlayer().getName())) {
            Location from = e.getFrom();
            Location to = e.getTo();

            prevPitchDelta = currentPitchDelta;
            currentPitchDelta = Math.abs(from.getPitch() - to.getPitch());

            final float gcd = (float) mathUtils.getGcd(currentPitchDelta, prevPitchDelta);
            final double sensitivityModifier = Math.cbrt(0.8333 * gcd);
            final double sensitivityStepTwo = (1.666 * sensitivityModifier) - 0.3333;
            final double finalSensitivity = sensitivityStepTwo * 200;

            sanitizeSensitivity(e.getPlayer().getName(), finalSensitivity);

        }
    }

    public void sanitizeSensitivity(String user, double val) {

        if (!avgSensitivity.containsKey(user)) {
            avgSensitivity.put(user, new ArrayList<Double>());
        }

        if ((val < 0) || (val > 200)) { return; }

        if (avgSensitivity.get(user).size() >= 100) {

            ArrayList<Double> retreivedArrayList = avgSensitivity.get(user);
            HashMap<Double, Integer> frequencyMap = new HashMap<>();

            DecimalFormat df = new DecimalFormat("#.#####");

            for (double sens : retreivedArrayList) {
                double roundedValue = Double.parseDouble(df.format(sens));
                if (frequencyMap.containsKey(roundedValue)) {
                    frequencyMap.put(roundedValue, frequencyMap.get(roundedValue) + 1);
                } else {
                    frequencyMap.put(roundedValue, 1);
                }
            }

            double mostCommonValue = Double.NaN;
            int maxCount = 0;
            // Finds the most common read sensitivity
            // This helps to eliminate outliers
            for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {

                double value = entry.getKey();
                int count = entry.getValue();
                if (count > maxCount) {
                    mostCommonValue = value;
                    maxCount = count;
                }
            }

            String message = ChatColor.GREEN + "Sensitivity analysis finished: " + user + "'s sensitivity is: " + ChatColor.DARK_GREEN + mostCommonValue + " (" + Math.round(mostCommonValue) + ")";
            Bukkit.broadcast(message, "sensitivity.notify");
            Bukkit.broadcastMessage(message);


            avgSensitivity.remove(user);
        }

        if (avgSensitivity.containsKey(user)) { avgSensitivity.get(user).add(val); }


    }

}