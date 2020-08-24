package com.theboreddev.fixter;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

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
    public void shouldSupplyBigDecimalUsingDefaultSupplier() {

        ElementSupplier<BigDecimal> supplier = new ElementSupplier<>(BigDecimal.class);

        assertThat(supplier.supplyElement()).isGreaterThan(new BigDecimal(0)).isLessThanOrEqualTo(new BigDecimal(100));
    }

    @Test
    public void shouldSupplyBigDecimalUsingCustomSupplier() {

        BigDecimal min = new BigDecimal(1_000_000_000_000L);
        BigDecimal max = new BigDecimal(1_000_000_000_000_000L);

        ElementSupplier<BigDecimal> supplier = new ElementSupplier<>(() -> Random.randomBigDecimal(min, max), BigDecimal.class);

        assertThat(supplier.supplyElement()).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(max);
    }

    @Test
    public void shouldSupplyBigIntegerUsingDefaultSupplier() {

        ElementSupplier<BigInteger> supplier = new ElementSupplier<>(BigInteger.class);

        assertThat(supplier.supplyElement()).isGreaterThan(new BigInteger("0")).isLessThanOrEqualTo(new BigInteger("100"));
    }

    @Test
    public void shouldSupplyBigIntegerUsingCustomSupplier() {

        BigInteger min = new BigInteger("1000000000000");
        BigInteger max = new BigInteger("1000000000000000");

        ElementSupplier<BigInteger> supplier = new ElementSupplier<>(() -> Random.randomBigInteger(min, max), BigInteger.class);

        assertThat(supplier.supplyElement()).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(max);
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

    @Test
    public void shouldSupplyEnumUsingDefaultSupplier() {

        ElementSupplier<EmployeeType> supplier = new ElementSupplier<>(EmployeeType.class);

        assertThat(supplier.supplyElement()).isIn(EmployeeType.PERMANENT, EmployeeType.CONTRACTOR);
    }

    @Test
    public void shouldSupplyEnumUsingCustomSupplier() {

        ElementSupplier<EmployeeType> supplier = new ElementSupplier<>(() -> EmployeeType.CONTRACTOR, EmployeeType.class);

        assertThat(supplier.supplyElement()).isEqualTo(EmployeeType.CONTRACTOR);
    }


    enum EmployeeType {
        PERMANENT,
        CONTRACTOR
    }
}
