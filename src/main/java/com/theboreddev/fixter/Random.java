package com.theboreddev.fixter;

public class Random {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static int randomInt(int limit) {
        return randomInt(1, limit);
    }

    public static int randomInt(int min, int limit) {
        return (int) (Math.random() * (limit - min + 1) + min);
    }

    public static double randomDouble(int limit) {
        double min = 1;
        return (int) (Math.random() * (limit - min + 1) + min);
    }

    public static double randomDouble(int min, int limit) {
        return (int) (Math.random() * (limit - min + 1) + min);
    }
}
