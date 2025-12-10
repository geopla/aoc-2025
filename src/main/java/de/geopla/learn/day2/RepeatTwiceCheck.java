package de.geopla.learn.day2;

final class RepeatTwiceCheck {

    static boolean hasRepetition(String value) {
        if (isNotToBeChecked(value)) {
            return false;
        }

        var middle = value.length() / 2;
        var firstHalf = value.substring(0, middle);
        var secondHalf = value.substring(middle);

        return firstHalf.equals(secondHalf);
    }

    private static boolean isNotToBeChecked(String value) {
        return (value.isEmpty()
            || value.startsWith("0")
            || value.length() % 2 != 0
        );
    }
}
