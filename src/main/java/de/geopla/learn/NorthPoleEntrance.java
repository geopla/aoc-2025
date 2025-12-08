package de.geopla.learn;

import de.geopla.learn.DecoySafe.Dial;
import de.geopla.learn.DecoySafe.Turn;

import java.util.Optional;
import java.util.stream.Stream;

public class NorthPoleEntrance {

    private final DecoySafe decoySafe;

    public NorthPoleEntrance(int safeDialPointsTo) {
        decoySafe = new DecoySafe(new Dial(safeDialPointsTo));
    }

    Optional<Long> zeroPositionsFor(Stream<Turn> turns) {
        var zeros = DialSequence.from(turns, decoySafe)
                .filter(dial -> dial.pointsTo() == 0)
                .count();

        return zeros > 0 ? Optional.of(zeros) : Optional.empty();
    }

    Optional<Long> zeroPositionsIncludingZeroPassesFor(Stream<Turn> turns) {
        var zeros = DialSequence.from(turns, decoySafe)
                .filter(dial -> dial.pointsTo() == 0 || dial.zeroPassCount() > 0)
                .map(this::countZerosForPassesAndFinalZeroDestination)
                .reduce(0L, Long::sum);

        return zeros > 0 ? Optional.of(zeros) : Optional.empty();
    }

    long countZerosForPassesAndFinalZeroDestination(Dial dial) {
        int zeroDestinationCount = dial.pointsTo() == 0 ? 1 : 0;

        return dial.zeroPassCount() > 0 ? dial.zeroPassCount() : zeroDestinationCount;
    }
}
