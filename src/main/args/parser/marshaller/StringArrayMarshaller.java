package args.parser.marshaller;

import args.parser.ArgsType;
import args.parser.TypedArgument;

import java.util.Optional;

public class StringArrayMarshaller implements ArgsMarshaller<String[]> {

    @Override
    public Optional<String[]> parse(TypedArgument value) {
        return  Optional.ofNullable(value)
                .map(v-> v.getValue(ArgsType.StringArray))
                .map(this::parseStringArray);
    }

    private String[] parseStringArray(String string) {
        return string.trim().split(",");
    }
}