package com.theboreddev.fixter;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FixterStringsTest {

    @Test
    public void shouldPopulateACollectionOfStrings() {

        List<String> strings = new Fixter<String>(10, String.class).apply();

        assertThat(strings).hasSize(10).allMatch(string -> string.length() == 5);
    }

    @Test
    public void shouldPopulateACollectionOfStringsWithACustomSupplier() {

        List<String> strings = new Fixter<String>(10, String.class).withSupplier(() -> String.valueOf(Random.randomInt(100))).apply();

        assertThat(strings).hasSize(10).allMatch(string -> Integer.parseInt(string) > 0);
    }
}
