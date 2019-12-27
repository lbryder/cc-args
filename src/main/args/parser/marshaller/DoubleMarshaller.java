package args.parser.marshaller;

import args.parser.ArgsException;
import args.parser.ArgsType;
import args.parser.MainArg;

import java.util.Optional;

public class DoubleMarshaller implements ArgsMarshaller<Double> {

    @Override
    public Optional<Double> parse(MainArg value, String key) {
        try {
            return Optional.ofNullable(value)
                    .map(v -> v.getValue(ArgsType.Double))
                    .map(Double::valueOf);
        } catch (NumberFormatException e) {
            throw new ArgsException("Invalid Double for param: " + key + " was " + value.getValue(ArgsType.Double));
        }
    }
}
