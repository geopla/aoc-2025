package de.geopla.learn.day2;

import org.jspecify.annotations.NullMarked;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

@NullMarked
class IdRangeReader {

    static Stream<IdRange> from(InputStream inputStream) {

        return Stream.empty();
    }
}
