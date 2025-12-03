package de.geopla.learn;

import de.geopla.learn.DecoySafe.Dial;
import de.geopla.learn.DecoySafe.Turn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    void shouldDialToTheRight(int startPosition, int clicks, int nextDialPosition) {
        var decoySafe = new DecoySafe(new Dial(startPosition));
        var turn = new Turn(RIGHT, clicks);

        decoySafe.next(turn);

        assertThat(decoySafe.dial().pointsTo()).isEqualTo(nextDialPosition);
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
    void shouldDialToTheLeft(int startPosition, int clicks, int nextDialPosition) {
        var decoySafe = new DecoySafe(new Dial(startPosition));
        var turn = new Turn(LEFT, clicks);

        decoySafe.next(turn);

        assertThat(decoySafe.dial().pointsTo()).isEqualTo(nextDialPosition);
    }


    @DisplayName("Should count marker passes to the right (including final destination)")
    @ParameterizedTest(name = "[{index}]: {1} clicks right from position {0} passes {2} times(s) zero ")
    @CsvSource({
            "50,  49, 0",
            "50,  50, 1",
            "50,  70, 1",
            "50, 270, 3",
    })
    void shouldCountMarkerPassesToTheRight(int start, int clicks, int zeroPasses) {
        var decoySafe = new DecoySafe(new Dial(start));
        var turn = new Turn(RIGHT, clicks);

        decoySafe.next(turn);

        assertThat(decoySafe.dial().zeroPassCount()).isEqualTo(zeroPasses);
    }

    @DisplayName("Should count marker passes to the left (including final destination)")
    @ParameterizedTest(name = "[{index}]: {1} clicks left from position {0} passes {2} times(s) zero ")
    @CsvSource({
            "50,  49, 0",
            "50,  50, 1",
            "50,  70, 1",
            "50, 270, 3",
    })
    void shouldCountMarkerPassesToTheLeft(int start, int clicks, int zeroPasses) {
        var decoySafe = new DecoySafe(new Dial(start));
        var turn = new Turn(LEFT, clicks);

        decoySafe.next(turn);

        assertThat(decoySafe.dial().zeroPassCount()).isEqualTo(zeroPasses);
    }
}