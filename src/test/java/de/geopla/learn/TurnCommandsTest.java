package de.geopla.learn;

import de.geopla.learn.DecoySafe.Turn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}