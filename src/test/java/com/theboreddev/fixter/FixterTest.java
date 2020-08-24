package com.theboreddev.fixter;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FixterTest {

    @Test
    public void shouldPopulateACollectionOfStrings() {

        Fixter fixter = Fixter.of(10).build();

        List<String> strings = fixter.fill();

        assertThat(strings).hasSize(10).allMatch(string -> string.length() == 5);
    }

    @Test
    public void shouldPopulateACollectionOfStringsWithOnlyNumbers() {

        Fixter fixter = Fixter.of(10).withSupplier(() -> String.valueOf(Random.randomInt(100))).build();

        List<String> strings = fixter.fill();

        assertThat(strings).hasSize(10).allMatch(string -> Integer.parseInt(string) > 0);
    }


}
