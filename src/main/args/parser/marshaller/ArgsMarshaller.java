package args.parser.marshaller;

import args.parser.ArgsException;
import args.parser.TypedArgument;

import java.util.Optional;

public interface ArgsMarshaller<T> {

    Optional<T> parse(TypedArgument value);

    default void test(TypedArgument value) throws ArgsException {
        this.parse(value);
    };
}
