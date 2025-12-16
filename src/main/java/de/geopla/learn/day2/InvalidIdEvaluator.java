package de.geopla.learn.day2;

import java.util.stream.Stream;

class InvalidIdEvaluator {

    static Stream<String> invalidIds(String firstId, String lastId) {
        var rangeStream = new IdRange(firstId, lastId);

        return invalidIds(rangeStream.ids());
    }

    static Stream<String> invalidIds(Stream<String> ids) {
        return ids.filter(RepeatTwiceCheck::hasRepetition);
    }
}
