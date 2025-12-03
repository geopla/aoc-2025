package de.geopla.learn;

import de.geopla.learn.DecoySafe.Dial;
import de.geopla.learn.DecoySafe.Turn;

import java.util.Optional;
import java.util.stream.Stream;

public class NorthPoleEntrance {

    private DecoySafe decoySafe;

    public NorthPoleEntrance(int safeDialPointsTo) {
        decoySafe = new DecoySafe(new Dial(safeDialPointsTo));
    }

    Optional<Long> zeroPositionsFor(Stream<Turn> turns) {
        var zeros = DialSequence.from(turns, decoySafe)
                .filter(dial -> dial.pointsTo() == 0)
                .count();

        return zeros > 0 ? Optional.of(zeros) : Optional.empty();
    }

    Optional<Long> zeroPositionsIncludingPassesFor(Stream<Turn> turns) {
        // TODO test an implement part 2
        return Optional.empty();
    }
}
