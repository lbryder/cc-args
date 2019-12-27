package args.parser.marshaller;

import args.parser.ArgsException;
import args.parser.MainArg;

import java.util.Optional;

public interface ArgsMarshaller<T> {

    Optional<T> parse(MainArg value, String key);

    default void test(MainArg value) throws ArgsException {
        this.parse(value, null);
    };
}
