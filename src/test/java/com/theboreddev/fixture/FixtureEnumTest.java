package com.theboreddev.fixture;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FixtureEnumTest {

    @Test
    public void shouldPopulateACollectionOfEnums() {

        List<EmployeeType> types = Fixture.of(30, EmployeeType.class).apply();

        assertThat(types).hasSize(30).allMatch(type -> Arrays.asList(EmployeeType.values()).contains(type))
                .anyMatch(type -> type.equals(EmployeeType.PERMANENT))
                .anyMatch(type -> type.equals(EmployeeType.CONTRACTOR))
                .anyMatch(type -> type.equals(EmployeeType.TEMPORAL));
    }

    @Test
    public void shouldPopulateACollectionOfEnumsWithACustomSupplier() {

        List<EmployeeType> types = Fixture.of(10, EmployeeType.class).withSupplier(() -> EmployeeType.CONTRACTOR).apply();

        assertThat(types).hasSize(10).allMatch(type -> type.equals(EmployeeType.CONTRACTOR));
    }


    enum EmployeeType {
        PERMANENT,
        TEMPORAL,
        CONTRACTOR;
    }
}
