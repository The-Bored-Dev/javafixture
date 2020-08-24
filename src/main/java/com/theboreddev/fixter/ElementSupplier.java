package com.theboreddev.fixter;

import java.util.function.Supplier;

public class ElementSupplier<T> {
    private static final int DEFAULT_STRING_SIZE = 5;
    private static final int DEFAULT_LIMIT = 100;

    private final Supplier<T> supplier;
    private final Class<?> type;

    public ElementSupplier() {
        this.supplier = null;
        this.type = String.class;
    }

    public ElementSupplier(Class<?> type) {
        this.supplier = null;
        this.type = type;
    }

    public ElementSupplier(Supplier<T> supplier, Class<?> type) {
        this.supplier = supplier;
        this.type = type;
    }


    public T supplyElement() {
        return supplier != null ? supplier.get() : (T) supplyDefaultValueFor(type);
    }

    private Object supplyDefaultValueFor(Class<?> type) {
        if (type.equals(String.class)) {
            return Random.randomAlphaNumeric(DEFAULT_STRING_SIZE);
        } else if (type.equals(Integer.class)) {
            return Random.randomInt(DEFAULT_LIMIT);
        } else if (type.equals(Double.class)) {
            return Random.randomDouble(DEFAULT_LIMIT);
        }
        throw new IllegalArgumentException("Type not supported!");
    }
}
