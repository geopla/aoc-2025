package de.geopla.learn.day1;

import de.geopla.learn.day1.DecoySafe.Dial;
import de.geopla.learn.day1.DecoySafe.Turn;
import org.jspecify.annotations.NullMarked;

import java.util.Iterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@NullMarked
public class DialSequence {

    static Stream<Dial> from(Stream<Turn> turns, DecoySafe safe) {
        Iterator<Turn> turnsIterator = turns.iterator();

        Iterator<Dial> dialsIterator = new Iterator<>() {
            final DecoySafe decoySafe = safe;

            @Override
            public boolean hasNext() {
                return turnsIterator.hasNext();
            }

            @Override
            public Dial next() {
                return decoySafe.next(turnsIterator.next());
            }
        };

        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(dialsIterator, 0), false);
    }
}
