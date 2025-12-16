package de.geopla.learn.day2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class IdRangeReaderTest {

    @Test
    @DisplayName("Should read two id ranges")
    void shouldReadTwoIdRanges() {
        var inputStream =  new ByteArrayInputStream("11-22,95-115".getBytes(StandardCharsets.UTF_8));

        Stream<IdRange> idRanges = IdRangeReader.from(inputStream);

        assertThat(idRanges).containsExactly(
                new IdRange("11", "22"),
                new IdRange("95", "115")
        );
    }
}