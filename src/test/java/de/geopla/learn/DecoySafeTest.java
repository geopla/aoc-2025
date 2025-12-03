package de.geopla.learn;

import de.geopla.learn.DecoySafe.Dial;
import de.geopla.learn.DecoySafe.Turn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static de.geopla.learn.DecoySafe.next;
import static de.geopla.learn.Direction.LEFT;
import static de.geopla.learn.Direction.RIGHT;
import static org.assertj.core.api.Assertions.assertThat;

class DecoySafeTest {

    @DisplayName("Should dial to the right")
    @ParameterizedTest(name = "[{index}]: {1} clicks right from position {0} leads to {2}")
    @CsvSource({
            "50,   0, 50",
            "50,   7, 57",
            "50,  50,  0",
            "50,  77, 27",
            "50, 177, 27",
    })
    void shouldDialToTheRight(int startPosition, int clicks, int expectedNextDialPosition) {
        var turn = new Turn(RIGHT, clicks);
        var dial = new Dial(startPosition);

        assertThat(next(turn, dial).pointsTo()).isEqualTo(expectedNextDialPosition);
    }

    @DisplayName("Should dial to the left")
    @ParameterizedTest(name = "[{index}]: {1} clicks left from position {0} leads to {2}")
    @CsvSource({
            "50,   0, 50",
            "50,   8, 42",
            "50,  50,  0",
            "50,  58, 92",
            "50, 158, 92",
    })
    void shouldDialToTheLeft(int startPosition, int clicks, int expectedNextDialPosition) {
        var turn = new Turn(LEFT, clicks);
        var dial = new Dial(startPosition);

        assertThat(next(turn, dial).pointsTo()).isEqualTo(expectedNextDialPosition);
    }



}