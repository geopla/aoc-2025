package de.geopla.learn.day2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RangeStreamTest {

    @Test
    @DisplayName("Should stream some ids")
    void shouldStreamSomeIds() {
        Stream<String> ids = new RangeStream("10", "12").ids();

        assertThat(ids).containsExactly("10", "11", "12");
    }
}