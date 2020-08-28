package com.theboreddev.fixture;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.Collections.emptyMap;

public class ElementSupplier<T> {
    private static final int DEFAULT_STRING_SIZE = 5;
    private static final int DEFAULT_LIMIT = 100;

    private final Supplier<T> supplier;
    private final Map<String, Supplier<?>> fieldSuppliers;
    private final Class<?> type;

    public ElementSupplier() {
        this.supplier = null;
        this.type = String.class;
        this.fieldSuppliers = emptyMap();
    }

    public ElementSupplier(Class<?> type) {
        this.supplier = null;
        this.type = type;
        this.fieldSuppliers = emptyMap();
    }

    public ElementSupplier(Supplier<T> supplier, Class<?> type) {
        this.supplier = supplier;
        this.type = type;
        this.fieldSuppliers = emptyMap();
    }

    public ElementSupplier(Map<String, Supplier<?>> fieldSuppliers, Class<?> type) {
        this.fieldSuppliers = fieldSuppliers;
        this.type = type;
        this.supplier = null;
    }


    public T supplyElement() {
        return supplier != null ? supplier.get() : (T) supplyDefaultValueFor(type, fieldSuppliers);
    }

    private Object supplyDefaultValueFor(Class<?> type, Map<String, Supplier<?>> fieldSuppliers) {
        if (type.equals(String.class)) {
            return Random.randomAlphaNumeric(DEFAULT_STRING_SIZE);
        } else if (type.equals(Integer.class) || type.equals(int.class)) {
            return Random.randomInt(DEFAULT_LIMIT);
        } else if (type.equals(Double.class) || type.equals(double.class)) {
            return Random.randomDouble(DEFAULT_LIMIT);
        } else if (type.equals(Long.class) || type.equals(long.class)) {
            return Random.randomLong(DEFAULT_LIMIT);
        } else if (type.equals(Float.class) || type.equals(float.class)) {
            return Random.randomFloat(DEFAULT_LIMIT);
        } else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
            return Random.randomBoolean();
        } else if (type.equals(BigDecimal.class)) {
            return Random.randomBigDecimal(new BigDecimal(DEFAULT_LIMIT));
        } else if (type.equals(BigInteger.class)) {
            return Random.randomBigInteger(new BigInteger(String.valueOf(DEFAULT_LIMIT)));
        } else if(type.isEnum()) {
            int randomInt = Random.randomInt(0, type.getEnumConstants().length - 1);
            return type.getEnumConstants()[randomInt];
        } else if (type.equals(Date.class)) {
            return Date.from(Instant.now());
        } else {
            return ObjectSupplier.supplyObject(type, fieldSuppliers);
        }
    }
}
