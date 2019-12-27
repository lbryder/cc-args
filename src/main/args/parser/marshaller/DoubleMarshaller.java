package args.parser.marshaller;

import args.parser.ArgsType;
import args.parser.MainArg;

import java.util.Optional;

public class DoubleMarshaller implements ArgsMarshaller<Double> {

    @Override
    public Optional<Double> parse(MainArg value) {
        return Optional.ofNullable(value)
                .map(v -> v.getValue(ArgsType.Double))
                .map(Double::valueOf);
    }
}
