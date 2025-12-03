package de.geopla.learn;

class DecoySafe {

    // 0 .. 99
    final static int MAX_DIALS = 100;

    record Turn(Direction direction, int clicks) { }
    record Dial(int pointsTo) {  }

    static Dial next(Turn turn, Dial dial) {
        var positionAfter = switch (turn.direction) {
            case RIGHT -> computeRightDial(dial.pointsTo, turn.clicks);
            case LEFT -> computeLeftDial(dial.pointsTo, turn.clicks);
        };

        return new Dial(positionAfter);
    }

    static int computeRightDial(int start, int clicks) {
        return (start + clicks) % MAX_DIALS;
    }

    static int computeLeftDial(int start, int clicks) {
        var effectiveClicks = clicks % MAX_DIALS;
        var equivalentRightClicks = MAX_DIALS - effectiveClicks;

        return computeRightDial(start, equivalentRightClicks);
    }
}
