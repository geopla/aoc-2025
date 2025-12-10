package de.geopla.learn.day2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class RepeatTwiceCheckTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "22",
            "1010",
            "1188511885",
            "222222",
            "446446",
            "38593859"
    })
    @DisplayName("Should detect repetitive id's")
    void shouldRecognizeRepetitions(String value) {
        var result = RepeatTwiceCheck.hasRepetition(value);
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "0101",
            "101",
            ""
    })
    @DisplayName("Should ignore NOT repetitive id's")
    void shouldIgnoreNotRepetitiveIds(String value) {
        var result = RepeatTwiceCheck.hasRepetition(value);
        assertThat(result).isFalse();
    }

}