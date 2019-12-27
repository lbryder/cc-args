package args.parser.marshaller;

import args.parser.ArgsType;
import args.parser.TypedArgument;

import java.util.Optional;

public class IntegerMarshaller implements ArgsMarshaller<Integer> {

    @Override
    public Optional<Integer> parse(TypedArgument value) {
        return Optional.ofNullable(value)
                .map(v -> v.getValueOfType(ArgsType.Integer))
                .map(v -> v.replace("\\",""))
                .map(Integer::valueOf);
    }
}
