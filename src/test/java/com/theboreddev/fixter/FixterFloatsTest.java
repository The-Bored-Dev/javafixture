package com.theboreddev.fixter;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FixterFloatsTest {

    @Test
    public void shouldPopulateACollectionOfLongs() {

        List<Float> floats = Fixter.of(10, Float.class).apply();

        assertThat(floats).hasSize(10).allMatch(number -> number >= 1.0 && number <= 100.0);
    }

    @Test
    public void shouldPopulateACollectionOfLongsWithCustomSupplier() {

        List<Float> floats = Fixter.of(10, Float.class)
                .withSupplier(() -> Random.randomFloat(10))
                .apply();

        assertThat(floats).hasSize(10).allMatch(number -> number >= 1.0 && number <= 10);
    }
}
