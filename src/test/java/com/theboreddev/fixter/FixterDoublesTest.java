package com.theboreddev.fixter;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FixterDoublesTest {

    @Test
    public void shouldPopulateACollectionOfDoubles() {

        List<Double> doubles = Fixter.of(10, Double.class).apply();

        assertThat(doubles).hasSize(10).allMatch(number -> number >= 1.0 && number <= 100.0);
    }

    @Test
    public void shouldPopulateACollectionOfDoublesWithCustomSupplier() {

        List<Double> doubles = Fixter.of(10, Double.class)
                .withSupplier(() -> Random.randomDouble(10))
                .apply();

        assertThat(doubles).hasSize(10).allMatch(number -> number >= 1.0 && number <= 10);
    }


}
