package args.parser.marshaller;

import args.parser.ArgsException;
import args.parser.ArgsType;
import args.parser.TypedArgument;

import java.util.Optional;

public class DoubleMarshaller implements ArgsMarshaller<Double> {

    @Override
    public Optional<Double> parse(TypedArgument value) {
        try {
            return Optional.ofNullable(value)
                    .map(v -> v.getValue(ArgsType.Double))
                    .map(Double::valueOf);
        } catch (NumberFormatException e) {
            throw new ArgsException("Invalid Double for param: " + value.getKey() + " , it was: " + value.getString());
        }
    }
}
