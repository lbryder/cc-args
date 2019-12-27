package args.parser.marshaller;

import args.parser.ArgsType;
import args.parser.MainArg;

import java.util.Optional;

public class BooleanMarshaller implements ArgsMarshaller<Boolean> {

    @Override
    public Optional<Boolean> parse(MainArg value) {
        return Optional.ofNullable(value)
                .map(v -> v.getValue(ArgsType.Bool))
                .map(Boolean::valueOf);
    }
}
