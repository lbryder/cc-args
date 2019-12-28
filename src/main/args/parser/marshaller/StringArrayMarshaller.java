package args.parser.marshaller;

import args.parser.ArgsType;
import args.parser.TypedArgument;

import java.util.Arrays;
import java.util.Optional;

public class StringArrayMarshaller implements ArgsMarshaller<String[]> {

    @Override
    public Optional<String[]> parse(TypedArgument value) {
        return Optional.ofNullable(value)
                .map(v -> v.getValueOfType(ArgsType.StringArray))
                .map(this::parseStringArray);
    }

    private String[] parseStringArray(String string) {
        String[] split = string.split(",");

        return Arrays.stream(split)
                .map(String::trim)
                .toArray(String[]::new);
    }
}
