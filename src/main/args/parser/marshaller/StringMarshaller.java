package args.parser.marshaller;

import args.parser.ArgsType;
import args.parser.MainArg;

import java.util.Optional;

public class StringMarshaller implements ArgsMarshaller<String> {

    @Override
    public Optional<String> parse(MainArg value) {
        return Optional.ofNullable(value)
                .map(v -> v.getValue(ArgsType.String));
    }
}
