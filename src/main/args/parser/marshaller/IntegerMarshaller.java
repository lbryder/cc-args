package args.parser.marshaller;

import args.parser.ArgsType;
import args.parser.MainArg;

import java.util.Optional;

public class IntegerMarshaller implements ArgsMarshaller<Integer> {

    @Override
    public Optional<Integer> parse(MainArg value) {
        return Optional.ofNullable(value)
                .map(v -> v.getValue(ArgsType.Integer))
                .map(Integer::valueOf);
    }
}
