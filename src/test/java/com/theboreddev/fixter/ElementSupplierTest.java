package com.theboreddev.fixter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ElementSupplierTest {

    @Test
    public void shouldSupplyStringUsingDefaultSupplier() {

        ElementSupplier<String> supplier = new ElementSupplier<>();

        assertThat(supplier.supplyElement()).hasSize(5);
    }

    @Test
    public void shouldSupplyStringUsingCustomSupplier() {

        ElementSupplier<String> supplier = new ElementSupplier<>(() -> "hello world", String.class);

        assertThat(supplier.supplyElement()).hasSize(11).isEqualTo("hello world");
    }

    @Test
    public void shouldSupplyIntegerUsingDefaultSupplier() {

        ElementSupplier<Integer> supplier = new ElementSupplier<>(Integer.class);

        assertThat(supplier.supplyElement()).isGreaterThan(0).isLessThanOrEqualTo(100);
    }

    @Test
    public void shouldSupplyIntegerUsingCustomSupplier() {

        ElementSupplier<Integer> supplier = new ElementSupplier<>(() -> Random.randomInt(10), Integer.class);

        assertThat(supplier.supplyElement()).isGreaterThan(0).isLessThanOrEqualTo(10);
    }

    @Test
    public void shouldSupplyDoubleUsingDefaultSupplier() {

        ElementSupplier<Double> supplier = new ElementSupplier<>(Double.class);

        assertThat(supplier.supplyElement()).isGreaterThan(0).isLessThanOrEqualTo(100);
    }

    @Test
    public void shouldSupplyDoubleUsingCustomSupplier() {

        ElementSupplier<Double> supplier = new ElementSupplier<>(() -> Random.randomDouble(10), Double.class);

        assertThat(supplier.supplyElement()).isGreaterThan(0).isLessThanOrEqualTo(10);
    }

    @Test
    public void shouldSupplyLongUsingDefaultSupplier() {

        ElementSupplier<Long> supplier = new ElementSupplier<>(Long.class);

        assertThat(supplier.supplyElement()).isGreaterThan(0).isLessThanOrEqualTo(100);
    }

    @Test
    public void shouldSupplyLongUsingCustomSupplier() {

        long min = 1_000_000_000_000L;

        ElementSupplier<Long> supplier = new ElementSupplier<>(() -> Random.randomLong(min, Long.MAX_VALUE), Long.class);

        assertThat(supplier.supplyElement()).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(Long.MAX_VALUE);
    }

    @Test
    public void shouldSupplyFloatUsingDefaultSupplier() {

        ElementSupplier<Float> supplier = new ElementSupplier<>(Float.class);

        assertThat(supplier.supplyElement()).isGreaterThan(0).isLessThanOrEqualTo(100);
    }

    @Test
    public void shouldSupplyFloatUsingCustomSupplier() {

        long min = 1_000_000_000_000L;

        ElementSupplier<Float> supplier = new ElementSupplier<>(() -> Random.randomFloat(min, Float.MAX_VALUE), Long.class);

        assertThat(supplier.supplyElement()).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(Float.MAX_VALUE);
    }

    @Test
    public void shouldSupplyBooleanUsingDefaultSupplier() {

        ElementSupplier<Boolean> supplier = new ElementSupplier<>(Boolean.class);

        assertThat(supplier.supplyElement()).isIn(true, false);
    }

    @Test
    public void shouldSupplyBooleanUsingCustomSupplier() {

        ElementSupplier<Boolean> supplier = new ElementSupplier<>(() -> true, Boolean.class);

        assertThat(supplier.supplyElement()).isTrue();
    }
}
