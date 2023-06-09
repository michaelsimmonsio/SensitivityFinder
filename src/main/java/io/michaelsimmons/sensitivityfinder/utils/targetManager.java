package io.michaelsimmons.sensitivityfinder.utils;

import java.util.ArrayList;

public class targetManager {

    public static ArrayList<String> targets = new ArrayList<>();

    public static void addTarget(String target) {
        targets.add(target);
    }

    public static void removeTarget(String target) {
        targets.remove(target);
    }

    public static boolean isTarget(String target) {
        return targets.contains(target);
    }


}
