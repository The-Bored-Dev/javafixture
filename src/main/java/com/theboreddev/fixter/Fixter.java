package com.theboreddev.fixter;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Fixter<T> {

    private static final int DEFAULT_STRING_SIZE = 5;

    private final int size;
    private final Supplier<T> supplier;


    private Fixter(Builder builder) {
        this.size = builder.size;
        this.supplier = builder.supplier;
    }

    public static Builder of(int size) {
        return new Builder<>(size);
    }


    static class Builder<T> {

        private final int size;
        private Supplier<T> supplier;

        public Builder(int size) {
            this.size = size;
        }

        public Builder withSupplier(Supplier<T> supplier) {
            this.supplier = supplier;
            return this;
        }

        public Fixter<T> build() {
            return new Fixter(this);
        }
    }

    public List<T> fill() {
        return (List<T>) Stream.generate(() -> supplyString())
                .limit(size)
                .collect(toList());

    }

    private Object supplyString() {
        return supplier != null ? supplier.get() : Random.randomAlphaNumeric(DEFAULT_STRING_SIZE);
    }
}
