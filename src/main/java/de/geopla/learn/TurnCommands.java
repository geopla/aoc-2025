package de.geopla.learn;

import de.geopla.learn.DecoySafe.Turn;
import org.jspecify.annotations.NullMarked;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@NullMarked
public class TurnCommands {

    static final Pattern COMMAND = Pattern.compile("(?<direction>[LR]{1})(?<clicks>[\\d)]{1,2})");

    static Stream<Turn> from(Stream<String> commands) {
        return commands.map(TurnCommands::parse);
    }

    static Turn parse(String command) {
        var matcher = COMMAND.matcher(command);

        if (matcher.matches()) {
            return parse(
                    matcher.group("direction"),
                    matcher.group("clicks")
            );
        }
        throw new IllegalArgumentException("not a legal turn command: '%s'".formatted(command));
    }

    static Turn parse(String direction, String clicks) {
        return new Turn(
                Direction.from(direction),
                Integer.parseInt(clicks));
    }
}
