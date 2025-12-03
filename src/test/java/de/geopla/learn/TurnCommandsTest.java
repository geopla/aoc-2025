package de.geopla.learn;

import de.geopla.learn.DecoySafe.Turn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import static de.geopla.learn.Direction.LEFT;
import static de.geopla.learn.Direction.RIGHT;
import static org.assertj.core.api.Assertions.assertThat;

class TurnCommandsTest {

    @DisplayName("Should create turns")
    @Test
    void shouldCreateTurns() {
        var commands = Stream.of("R74", "L5");

        Stream<Turn> turns = TurnCommands.from(commands);

        assertThat(turns).containsExactly(
                new Turn(RIGHT, 74),
                new Turn(LEFT, 5)
        );
    }

    @DisplayName("Should create some more turns")
    @Test
    void shouldCreateSomeMoreTurns() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("turn-commands-sample.txt");
        Stream<String> commands = new BufferedReader(new InputStreamReader(inputStream)).lines();

        Stream<Turn> turns = TurnCommands.from(commands);

        assertThat(turns).containsExactly(
            new Turn(LEFT, 68),
            new Turn(LEFT, 30),
            new Turn(RIGHT, 48),
            new Turn(LEFT, 5),
            new Turn(RIGHT, 60),
            new Turn(LEFT, 55),
            new Turn(LEFT, 1),
            new Turn(LEFT, 99),
            new Turn(RIGHT, 14),
            new Turn(LEFT, 82)
        );
    }
}