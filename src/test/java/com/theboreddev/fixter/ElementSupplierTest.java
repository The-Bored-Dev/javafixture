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
}
