package de.geopla.learn.inputs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

class StreamCharacterTest {

    @Test
    @DisplayName("Should stream ASCII characters")
    void shouldAsciiCharacters() {
        var asciiBytes =  new ByteArrayInputStream("Hello World!".getBytes(StandardCharsets.UTF_8));
        List<Character> characters;

        try (var cs = StreamCharacter.charactersFrom(asciiBytes)) {
            characters = cs.toList();
        }

        assertThat(characters).containsExactly(
           'H','e','l','l','o',' ','W','o','r','l','d','!'
        );
    }

    @Test
    @DisplayName("Should close InputStream when Stream<Character> is closed")
    void shouldCloseInputStream() {
        var content = "Hello World!".getBytes(StandardCharsets.UTF_8);
        var closed = new AtomicBoolean(false);

        var inputStream = new ByteArrayInputStream(content) {
            @Override
            public void close() throws IOException {
                super.close();
                closed.set(true);
            }
        };

        try (var cs = StreamCharacter.charactersFrom(inputStream)) {
            cs.count();
        }

        assertThat(closed.get()).isTrue();
    }
}