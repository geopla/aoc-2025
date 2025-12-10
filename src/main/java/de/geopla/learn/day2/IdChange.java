package de.geopla.learn.day2;

final class IdChange {

    static final char[] NEXT_DIGIT = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

    static String increment(String value) {
        char[] valueDigits = value.toCharArray();
        int current = valueDigits.length - 1;

        while (current >= 0) {
            if (valueDigits[current] != '9') {
                valueDigits[current] = NEXT_DIGIT[valueDigits[current] - '0'];
                // no overflow, we are done
                break;
            } else {
                valueDigits[current] = '0';
                // overflow, we need to increment the previous digit
                current--;
            }
        }
        if (current < 0) {
            // run out of digits
            return new StringBuilder("1").append(valueDigits).toString();
        }

        return new String(valueDigits);
    }
}
