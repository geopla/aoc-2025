package de.geopla.learn.day2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class InvalidIdEvaluatorTest {

    @ParameterizedTest(name = "({0}, {1}) --> {2}")
    @MethodSource("parametersForShouldFindInvalidIdsInRange")
    @DisplayName("Should find invalid ids in range")
    void shouldFindInvalidIdsInRange(String firstId, String lastId, List<String> invalidIds) {
        var result = InvalidIdEvaluator.invalidIds(firstId, lastId);

        assertThat(result).containsExactlyElementsOf(invalidIds);
    }

    static Stream<Arguments> parametersForShouldFindInvalidIdsInRange() {
        return Stream.of(
          arguments("11", "22", List.of("11", "22")),
          arguments("95", "115", List.of("99")),
          arguments("1188511880", "1188511890", List.of("1188511885")),
          arguments("222220", "222224", List.of("222222")),
          arguments("1698522", "1698522", List.of()),
          arguments("446443", "446449", List.of("446446")),
          arguments("38593856", "38593862", List.of("38593859"))
        );
    }
}