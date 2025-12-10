package de.geopla.learn.day1;

import de.geopla.learn.day1.DecoySafe.Dial;
import de.geopla.learn.day1.DecoySafe.Turn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DialSequenceTest {

    @Test
    @DisplayName("Should create dial sequence from turn commands")
    void shouldCreateDialSequence() {
        var safe = new DecoySafe(new Dial(50));
        var turns = Stream.of(
                new Turn(Direction.RIGHT,2),
                new Turn(Direction.RIGHT,3),
                new Turn(Direction.LEFT, 60)
        );

        var dials = DialSequence.from(turns, safe);

        assertThat(dials.map(Dial::pointsTo)).containsExactly(52, 55, 95);
    }

    @Test
    @DisplayName("Should create empty dial sequence on empty turn commands")
    void shouldCreateEmptyStreamForNoTurns() {
        var safe = new DecoySafe(new Dial(50));
        var turns = Stream.<Turn>empty();

        var dials = DialSequence.from(turns, safe);

        assertThat(dials).isEmpty();
    }
}