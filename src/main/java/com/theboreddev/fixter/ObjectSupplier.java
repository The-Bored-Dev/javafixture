package com.theboreddev.fixter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;

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
}
