package de.geopla.learn;

import de.geopla.learn.DecoySafe.Dial;
import de.geopla.learn.DecoySafe.Turn;

import java.util.Optional;
import java.util.stream.Stream;

public class NorthPoleEntrance {

    private final DecoySafe decoySafe;

    public NorthPoleEntrance(int currentlyPointsTo) {
        decoySafe = new DecoySafe(new Dial(currentlyPointsTo));
    }

    Optional<Long> zeroPositionsFor(Stream<Turn> turns) {
        return Optional.of(DialSequence.from(turns, decoySafe)
                .filter(dial -> dial.pointsTo() == 0)
                .count())
                .filter(count -> count > 0);
    }

    Optional<Long> zeroPositionsIncludingZeroPassesFor(Stream<Turn> turns) {
        return Optional.of(DialSequence.from(turns, decoySafe)
                .filter(Dial::hasZeroPassesOrFinalZeroDestination)
                .map(Dial::countZeroPassesAndZeroDestination)
                .reduce(0L, Long::sum))
                .filter(count -> count > 0);
    }

}
