package com.theboreddev.fixter;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FixterIntegersTest {

    @Test
    public void shouldPopulateACollectionOfIntegers() {

        List<Integer> integers = new Fixter<Integer>(10, Integer.class).apply();

        assertThat(integers).hasSize(10).allMatch(number -> number > 0 && number <= 100);
    }

    @Test
    public void shouldPopulateACollectionOfIntegersWithCustomSupplier() {

        List<Integer> integers = new Fixter<Integer>(10, Integer.class)
                .withSupplier(() -> Random.randomInt(10))
                .apply();

        assertThat(integers).hasSize(10).allMatch(number -> number > 0 && number <= 10);
    }


}
