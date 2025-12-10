package de.geopla.learn.day1;

import org.jspecify.annotations.NullMarked;

@NullMarked
public class DecoySafe {

    // 0 .. 99
    final static int MAX_DIALS = 100;

    public record Turn(Direction direction, int clicks) {
    }

    public record Dial(int pointsTo, int zeroPassCount) {
        public Dial(int pointsTo) {
            this(pointsTo, 0);
        }

        boolean hasFinalDestinationZero() {
            return pointsTo() == 0;
        }

        boolean hasZeroPassesOrFinalZeroDestination() {
            return hasFinalDestinationZero() || zeroPassCount() > 0;
        }

        long countZeroPassesAndZeroDestination() {
            var zeroDestinationCount = hasFinalDestinationZero() ? 1 : 0;

            return zeroPassCount() > 0 ? zeroPassCount() : zeroDestinationCount;
        }
    }

    private Dial dial;

    public DecoySafe(Dial dial) {
        this.dial = dial;
    }

    public Dial dial() {
        return dial;
    }

    public Dial next(Turn turn) {
        this.dial = new Dial(
                switch (turn.direction) {
                    case RIGHT -> computeRightDial(dial.pointsTo, turn.clicks);
                    case LEFT -> computeLeftDial(dial.pointsTo, turn.clicks);
                },
                computeZeroPasses(this.dial().pointsTo(), turn.direction(), turn.clicks())
        );

        return dial;
    }

    static int computeRightDial(int start, int clicks) {
        return (start + clicks) % MAX_DIALS;
    }

    static int computeLeftDial(int start, int clicks) {
        var effectiveClicks = clicks % MAX_DIALS;
        var equivalentRightClicks = MAX_DIALS - effectiveClicks;

        return computeRightDial(start, equivalentRightClicks);
    }

    static int computeZeroPasses(int start, Direction direction, int clicks) {
        return switch (direction) {
            case RIGHT -> (start + clicks) / MAX_DIALS;
            case LEFT -> {
                var zeroPasses = (clicks + MAX_DIALS - start) / MAX_DIALS;
                yield start == 0 ? zeroPasses - 1 : zeroPasses;
            }
        };
    }
}
