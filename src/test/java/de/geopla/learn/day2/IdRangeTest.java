package de.geopla.learn.day2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class IdRangeTest {

    @ParameterizedTest(name = "[{index}] ({0}, {1}) --> {2}")
    @MethodSource("parametersForShouldStreamSomeIds")
    @DisplayName("Should stream some ids")
    void shouldStreamSomeIds(String firstId, String secondId, List<String> ids) {
        Stream<String> result = new IdRange(firstId, secondId).ids();

        assertThat(result.toList()).containsExactlyElementsOf(ids);
    }

    static Stream<Arguments> parametersForShouldStreamSomeIds() {
        return Stream.of(
                arguments("10", "12", List.of("10", "11", "12")),
                arguments("10", "11", List.of("10", "11")),
                arguments("10", "10", List.of("10"))
        );
    }
}