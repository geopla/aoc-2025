package de.geopla.learn.day2;

import org.jspecify.annotations.NullMarked;

import java.util.stream.Stream;

@NullMarked
class RangeStream {

    private final String firstId;
    private final String secondId;

    RangeStream(String firstId, String secondId) {
        this.firstId = firstId;
        this.secondId = IdChange.increment(secondId);
    }

    Stream<String> ids() {
       return Stream.iterate(
               firstId,
               id -> !id.equals(secondId),
               IdChange::increment
       );
    }

}
