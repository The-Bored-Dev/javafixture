package com.theboreddev.fixter;

import java.math.BigDecimal;
import java.math.BigInteger;

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

    public static long randomLong(long limit) {
        long min = 1;
        return (long) (Math.random() * (limit - min + 1) + min);
    }

    public static long randomLong(long min, long limit) {
        return (long) (Math.random() * (limit - min + 1) + min);
    }

    public static float randomFloat(float limit) {
        int min = 1;
        return min + new java.util.Random().nextFloat() * (limit - min);
    }

    public static float randomFloat(float min, float limit) {
        return min + new java.util.Random().nextFloat() * (limit - min);
    }

    public static boolean randomBoolean() {
        return Math.random() < 0.5;
    }

    public static BigDecimal randomBigDecimal(BigDecimal limit) {
        BigDecimal min = BigDecimal.ONE;
        return BigDecimal.valueOf(Math.random()).multiply(limit.subtract(min).add(BigDecimal.ONE)).add(min);
    }

    public static BigDecimal randomBigDecimal(BigDecimal min, BigDecimal limit) {
        return BigDecimal.valueOf(Math.random()).multiply(limit.subtract(min).add(BigDecimal.ONE)).add(min);
    }

    public static BigInteger randomBigInteger(BigInteger limit) {
        BigInteger min = BigInteger.ONE;
        return BigInteger.valueOf((long) Math.random()).multiply(limit.subtract(min).add(BigInteger.ONE)).add(min);
    }

    public static BigInteger randomBigInteger(BigInteger min, BigInteger limit) {
        return BigInteger.valueOf((long) Math.random()).multiply(limit.subtract(min).add(BigInteger.ONE)).add(min);
    }
}
