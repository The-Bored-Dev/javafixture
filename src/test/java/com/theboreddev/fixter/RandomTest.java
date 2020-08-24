package com.theboreddev.fixter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomTest {

    @Test
    public void shouldGenerateARandomAlphanumericOfAGivenSize() {

        String randomAlphaNumeric = Random.randomAlphaNumeric(10);

        assertThat(randomAlphaNumeric).hasSize(10); //TODO - Improve matcher - check that it only contains characters or numbers?
    }

    @Test
    public void shouldGenerateARandomNumber() {

        int randomInt = Random.randomInt(100);

        assertThat(randomInt).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(100);
    }
}
