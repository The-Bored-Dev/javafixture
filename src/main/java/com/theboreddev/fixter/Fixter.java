package com.theboreddev.fixter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public abstract class Fixter {
    protected Class<?> type;
    protected Supplier<?> supplier;
    protected final Map<String, Supplier<?>> fieldSuppliers = new HashMap<>();

    static class FixterCollectionsProvider extends Fixter {
        private final int size;

        private FixterCollectionsProvider(int size, Class<?> type) {
            this.size = size;
            this.type = type;
        }

        protected <T> FixterCollectionsProvider withSupplier(Supplier<T> supplier) {
            this.supplier = supplier;
            return this;
        }

        protected <U> FixterCollectionsProvider withFieldSupplier(String fieldName, Supplier<U> supplier) {
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

    static class FixterObjectProvider extends Fixter {

        private FixterObjectProvider(Class<?> type) {
            this.type = type;
        }

        protected <T> FixterObjectProvider withSupplier(Supplier<T> supplier) {
            this.supplier = supplier;
            return this;
        }

        protected <U> FixterObjectProvider withFieldSupplier(String fieldName, Supplier<U> supplier) {
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



    public static FixterCollectionsProvider of(int size, Class<?> type) {
        return new FixterCollectionsProvider(size, type);
    }

    public static FixterObjectProvider of(Class<?> type) {
        return new FixterObjectProvider(type);
    }
}
