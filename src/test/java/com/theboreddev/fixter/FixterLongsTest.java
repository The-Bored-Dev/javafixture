package com.theboreddev.fixter;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FixterLongsTest {

    @Test
    public void shouldPopulateACollectionOfLongs() {

        List<Long> longs = new Fixter<Long>(10, Long.class).apply();

        assertThat(longs).hasSize(10).allMatch(number -> number >= 1.0 && number <= 100.0);
    }

    @Test
    public void shouldPopulateACollectionOfLongsWithCustomSupplier() {

        List<Long> longs = new Fixter<Long>(10, Long.class)
                .withSupplier(() -> Random.randomLong(10))
                .apply();

        assertThat(longs).hasSize(10).allMatch(number -> number >= 1.0 && number <= 10);
    }
}
