package de.geopla.learn.day2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class IdChangeTest {

    @ParameterizedTest(name = "[{index}]: {0} to {1}")
    @CsvSource({
            "'0', '1'",
            "'1', '2'",
            "'2', '3'",
            "'3', '4'",
            "'4', '5'",
            "'5', '6'",
            "'6', '7'",
            "'7', '8'",
            "'8', '9'",

            "'9', '10'",
            "'98', '99'",
            "'99', '100'"
    })
    @DisplayName("Should increment")
    void shouldIncrement(String value, String increment) {
        assertThat(IdChange.increment(value)).isEqualTo(increment);
    }
}