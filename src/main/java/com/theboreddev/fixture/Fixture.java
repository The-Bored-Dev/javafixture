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
            this.fieldSuppliers.put(fieldName, supplier);
            return this;
        }

        public <T> List<T> apply() {
            if (isNotAnObjectAndHasFieldSuppliersSet()) {
                throw new IllegalStateException("Cannot specify field supplier if type is not an object!");
            }
            return (List<T>) Stream.generate(() ->
                    supplier != null ?
                            new ElementSupplier(supplier, type).supplyElement() :
                            new ElementSupplier<>(fieldSuppliers, type).supplyElement())
                    .limit(size)
                    .collect(toList());
        }

        private boolean isNotAnObjectAndHasFieldSuppliersSet() {
            return !type.isMemberClass() && !fieldSuppliers.isEmpty();
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
            if (isNotAnObjectAndHasFieldSuppliersSet()) {
                throw new IllegalStateException("Cannot specify field supplier if type is not an object!");
            }
            this.fieldSuppliers.put(fieldName, supplier);
            return this;
        }

        public <T> T apply() {
            return (T) (supplier != null ?
                    new ElementSupplier(supplier, type).supplyElement() :
                    new ElementSupplier<>(fieldSuppliers, type).supplyElement());
        }

        private boolean isNotAnObjectAndHasFieldSuppliersSet() {
            return !type.isMemberClass() && !fieldSuppliers.isEmpty();
        }
    }
}
