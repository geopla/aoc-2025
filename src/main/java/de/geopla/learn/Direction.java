package de.geopla.learn;

public enum Direction {
    LEFT,
    RIGHT
    ;

    public static Direction from(String letter) {
        return switch (letter) {
            case "L" -> LEFT;
            case "R" -> RIGHT;
            default -> throw new IllegalArgumentException("illegal direction letter: '%s'".formatted(letter));
        };
    }
}
