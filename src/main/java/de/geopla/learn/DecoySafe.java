package de.geopla.learn;

import org.jspecify.annotations.NullMarked;

@NullMarked
class DecoySafe {

    // 0 .. 99
    final static int MAX_DIALS = 100;

    record Turn(Direction direction, int clicks) {
    }

    record Dial(int pointsTo, int zeroPassCount) {
        Dial(int pointsTo) {
            this(pointsTo, 0);
        }
    }

    private Dial dial;

    DecoySafe(Dial dial) {
        this.dial = dial;
    }

    Dial dial() {
        return dial;
    }

    Dial next(Turn turn) {
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
            case LEFT -> (clicks + MAX_DIALS - start) / MAX_DIALS;
        };
    }
}
