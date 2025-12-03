package de.geopla.learn;

import de.geopla.learn.DecoySafe.Turn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import static de.geopla.learn.Direction.LEFT;
import static org.assertj.core.api.Assertions.assertThat;

class NorthPoleEntranceTest {

    @Test
    @DisplayName("Should find single zero dial position")
    void shouldFindSingleZeroPosition() {
        var entrance = new NorthPoleEntrance(50);
        var dialTurns = Stream.of(
                new Turn(LEFT, 50)
        );

        var zeroDialPositions = entrance.zeroPositionsFor(dialTurns);

        assertThat(zeroDialPositions).contains(1L);
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

        assertThat(zeroDialPositions).contains(3L);
    }
}