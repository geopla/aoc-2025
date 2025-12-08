package de.geopla.learn;

import de.geopla.learn.DecoySafe.Turn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import static de.geopla.learn.Direction.LEFT;
import static de.geopla.learn.Direction.RIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class NorthPoleEntranceTest {

    @Test
    @DisplayName("Should find single zero dial position")
    void shouldFindSingleZeroPosition() {
        var entrance = new NorthPoleEntrance(50);
        var dialTurns = Stream.of(
                new Turn(LEFT, 50)
        );

        var zeroDialPositions = entrance.zeroPositionsFor(dialTurns);

        assertThat(zeroDialPositions).hasValue(1L);
    }

    @Test
    @DisplayName("Should recognize non existing zero dial positions")
    void shouldName() {
        var entrance = new NorthPoleEntrance(50);
        var dialTurns = Stream.of(
                new Turn(LEFT, 10)
        );

        var zeroDialPositions = entrance.zeroPositionsFor(dialTurns);

        assertThat(zeroDialPositions).isEmpty();
    }

    @Test
    @DisplayName("Should count zero dial positions from sample")
    void shouldCountZeroDialPositionsFromSample() {
        var entrance = new NorthPoleEntrance(50);

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("turn-commands-sample.txt");
        Stream<String> commands = new BufferedReader(new InputStreamReader(inputStream)).lines();

        Stream<Turn> dialTurns = TurnCommands.from(commands);

        var zeroDialPositions = entrance.zeroPositionsFor(dialTurns);

        assertThat(zeroDialPositions).hasValue(3L);
    }

    @Test
    @DisplayName("Should count zero dial positions from puzzle")
    void shouldCountZeroDialPositionsFromPuzzle() {
        var entrance = new NorthPoleEntrance(50);

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("turn-commands-puzzle.txt");
        Stream<String> commands = new BufferedReader(new InputStreamReader(inputStream)).lines();

        Stream<Turn> dialTurns = TurnCommands.from(commands);

        var zeroDialPositions = entrance.zeroPositionsFor(dialTurns);

        assertThat(zeroDialPositions).hasValue(1165L);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Should count zero passes and final destination zero starting from dial 50")
    void shouldCountZeroPasses(Stream<Turn> dialTurns, long zeroPasses) {
        var entrance = new NorthPoleEntrance(50);
        var zeroDialPositions = entrance.zeroPositionsIncludingZeroPassesFor(dialTurns);

        if (zeroPasses == 0) {
            assertThat(zeroDialPositions).isEmpty();
        }
        else {
            assertThat(zeroDialPositions).hasValue(zeroPasses);
        }
    }

    static Stream<Arguments> shouldCountZeroPasses() {
        return Stream.of(
          arguments(Stream.of(new Turn(RIGHT, 10)), 0L),
          arguments(Stream.of(new Turn(RIGHT, 50)), 1L),
          arguments(Stream.of(new Turn(RIGHT, 150)), 2L),
          arguments(Stream.of(new Turn(LEFT, 50)), 1L),
          arguments(Stream.of(new Turn(LEFT, 150)), 2L),
          arguments(Stream.of(new Turn(LEFT, 250)), 3L)
        );
    }

    @Test
    @DisplayName("Should count zero passes and final destination zero from sample input")
    void shouldCountZeroPassesSample() {
        var entrance = new NorthPoleEntrance(50);

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("turn-commands-sample.txt");
        Stream<String> commands = new BufferedReader(new InputStreamReader(inputStream)).lines();

        Stream<Turn> dialTurns = TurnCommands.from(commands);

        var zeroDialPositions = entrance.zeroPositionsIncludingZeroPassesFor(dialTurns);

        assertThat(zeroDialPositions).hasValue(6L);
    }

    @Test
    @DisplayName("Should count zero passes and final destination zero from puzzle input")
    void shouldCountZeroPassesPuzzle() {
        var entrance = new NorthPoleEntrance(50);

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("turn-commands-puzzle.txt");
        Stream<String> commands = new BufferedReader(new InputStreamReader(inputStream)).lines();

        Stream<Turn> dialTurns = TurnCommands.from(commands);

        var zeroDialPositions = entrance.zeroPositionsIncludingZeroPassesFor(dialTurns);

        assertThat(zeroDialPositions).hasValue(6496L);
    }
}