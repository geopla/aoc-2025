package de.geopla.learn.inputs;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.PrimitiveIterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/// # Stream UTF-8 encoded content as `Character`.
/// Supports BMP only, no emojis or other strange stuff
public class StreamCharacter {

    static Stream<Character> charactersFrom(InputStream inputStream) {

        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        PrimitiveIterator.OfInt charIterator = new PrimitiveIterator.OfInt() {
            int lookup = readNext();

            int readNext() {
                try {
                    return reader.read();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public boolean hasNext() {
                return lookup != -1;
            }

            @Override
            public int nextInt() {
                int next = lookup;
                lookup = readNext();

                return next;
            }
        };

        return StreamSupport.intStream(Spliterators.spliteratorUnknownSize(charIterator, 0), false)
                .mapToObj(i -> (char) i)
                .onClose(() -> {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
