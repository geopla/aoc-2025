package de.geopla.learn.day2;

import org.jspecify.annotations.NullMarked;

import java.util.stream.Stream;

@NullMarked
record IdRange(String firstId, String lastId) {

    IdRange {
        lastId = IdChange.increment(lastId);
        // TODO make sure firstId <= lastId
    }

    Stream<String> ids() {
        return Stream.iterate(
                firstId,
                id -> !id.equals(lastId),
                IdChange::increment
        );
    }
}
