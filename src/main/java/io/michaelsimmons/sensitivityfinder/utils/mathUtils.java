package io.michaelsimmons.sensitivityfinder.utils;

public class mathUtils {


    public static double getGcd(final double a, final double b) {
        if (a < b) {
            return getGcd(b, a);
        }
        if (Math.abs(b) < 0.001) {
            return a;
        } else {
            return getGcd(b, a - Math.floor(a / b) * b);
        }
    }

}
