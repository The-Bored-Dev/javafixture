package com.theboreddev.fixter;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Fixter<T> {
    private final int size;
    private final Class<?> type;
    private Supplier<T> supplier;

    public Fixter(int size, Class<?> type) {
        this.size = size;
        this.type = type;
    }

    public Fixter<T> withSupplier(Supplier<T> supplier) {
        this.supplier = supplier;
        return this;
    }

    public List<T> apply() {
        return (List<T>) Stream.generate(() -> new ElementSupplier(supplier, type).supplyElement())
                .limit(size)
                .collect(toList());
    }
}
