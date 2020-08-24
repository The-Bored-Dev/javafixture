package com.theboreddev.fixture;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public abstract class Fixture {
    protected Class<?> type;
    protected Supplier<?> supplier;
    protected final Map<String, Supplier<?>> fieldSuppliers = new HashMap<>();


    public static FixtureCollectionsProvider of(int size, Class<?> type) {
        return new FixtureCollectionsProvider(size, type);
    }

    public static FixtureObjectProvider of(Class<?> type) {
        return new FixtureObjectProvider(type);
    }


    static class FixtureCollectionsProvider extends Fixture {

        private final int size;

        private FixtureCollectionsProvider(int size, Class<?> type) {
            this.size = size;
            this.type = type;
        }

        protected <T> FixtureCollectionsProvider withSupplier(Supplier<T> supplier) {
            this.supplier = supplier;
            return this;
        }

        protected <U> FixtureCollectionsProvider withFieldSupplier(String fieldName, Supplier<U> supplier) {
            if (type.isPrimitive()) {
                throw new IllegalArgumentException("Cannot set field supplier for a simple type!");
            }
            this.fieldSuppliers.put(fieldName, supplier);
            return this;
        }

        public <T> List<T> apply() {
            return (List<T>) Stream.generate(() ->
                    supplier != null ?
                            new ElementSupplier(supplier, type).supplyElement() :
                            new ElementSupplier<>(fieldSuppliers, type).supplyElement())
                    .limit(size)
                    .collect(toList());
        }
    }


    static class FixtureObjectProvider extends Fixture {

        private FixtureObjectProvider(Class<?> type) {
            this.type = type;
        }

        protected <T> FixtureObjectProvider withSupplier(Supplier<T> supplier) {
            this.supplier = supplier;
            return this;
        }

        protected <U> FixtureObjectProvider withFieldSupplier(String fieldName, Supplier<U> supplier) {
            if (type.isPrimitive()) {
                throw new IllegalArgumentException("Cannot set field supplier for a simple type!");
            }
            this.fieldSuppliers.put(fieldName, supplier);
            return this;
        }

        public <T> T apply() {
            return (T) (supplier != null ?
                                        new ElementSupplier(supplier, type).supplyElement() :
                                        new ElementSupplier<>(fieldSuppliers, type).supplyElement());
        }
    }
}
