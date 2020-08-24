package com.theboreddev.fixter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Fixter<T> {
    private final int size;
    private final Class<?> type;
    private Supplier<T> supplier;
    private final Map<String, Supplier<?>> fieldSuppliers = new HashMap<>();

    public Fixter(int size, Class<?> type) {
        this.size = size;
        this.type = type;
    }

    public Fixter<T> withSupplier(Supplier<T> supplier) {
        this.supplier = supplier;
        return this;
    }

    public <U> Fixter<T> withFieldSupplier(String fieldName, Supplier<U> supplier) {
        if (type.isPrimitive()) {
            throw new IllegalArgumentException("Cannot set field supplier for a simple type!");
        }
        this.fieldSuppliers.put(fieldName, supplier);
        return this;
    }

    public List<T> apply() {
        return (List<T>) Stream.generate(() -> supplier != null ? new ElementSupplier(supplier, type).supplyElement() : new ElementSupplier<>(fieldSuppliers, type).supplyElement())
                .limit(size)
                .collect(toList());
    }
}
