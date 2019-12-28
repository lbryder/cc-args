package args.parser.marshaller;

import args.parser.ArgsType;
import args.parser.TypedArgument;

import java.util.Optional;

public class BooleanMarshaller implements ArgsMarshaller<Boolean> {

    @Override
    public Optional<Boolean> parse(TypedArgument value) {
        return Optional.ofNullable(value)
                .map(v -> v.getValueOfType(ArgsType.Bool))
                .map(Boolean::valueOf);
    }
}
