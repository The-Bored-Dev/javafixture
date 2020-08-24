package com.theboreddev.fixter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class ObjectSupplier {

    public static <T> T supplyObject(Class<T> type) {
        Constructor<?>[] declaredConstructors = type.getDeclaredConstructors();
        Optional<T> first = (Optional<T>) Arrays.stream(declaredConstructors)
                .map(constructor -> {
                    Class<?>[] parameterTypes = constructor.getParameterTypes();
                    Object[] parameters = Arrays.stream(parameterTypes)
                            .map(parameterType -> new ElementSupplier(parameterType).supplyElement())
                            .toArray();
                    Object newInstance = null;
                    try {
                        newInstance = constructor.newInstance(parameters);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return newInstance;
                })
                .filter(value -> value != null)
                .findFirst();
        return first.orElseThrow(() -> new IllegalStateException("Could not instantiate object of type " + type.getSimpleName()));
    }

    public static <T> T supplyObject(Class<T> type, Map<String, Supplier<?>> fieldSuppliers) {
        Constructor<?>[] declaredConstructors = type.getDeclaredConstructors();
        Optional<T> first = (Optional<T>) Arrays.stream(declaredConstructors)
                .map(constructor -> {
                    Class<?>[] parameterTypes = constructor.getParameterTypes();
                    Object[] parameters = Arrays.stream(parameterTypes)
                            .map(parameterType -> new ElementSupplier(parameterType).supplyElement())
                            .toArray();
                    Object newInstance = null;
                    try {
                        newInstance = constructor.newInstance(parameters);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return newInstance;
                })
                .filter(value -> value != null)
                .findFirst();

        T object = first.orElseThrow(() -> new IllegalStateException("Could not instantiate object of type " + type.getSimpleName()));
        fieldSuppliers.keySet()
                .forEach(fieldName -> {
                    try {
                        Field declaredField = object.getClass().getDeclaredField(fieldName);
                        declaredField.setAccessible(true);
                        declaredField.set(object, fieldSuppliers.get(fieldName).get());
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        return object;
    }
}
