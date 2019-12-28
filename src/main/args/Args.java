package args;

import args.parser.TypedArgument;
import args.parser.marshaller.BooleanMarshaller;
import args.parser.marshaller.DoubleMarshaller;
import args.parser.marshaller.IntegerMarshaller;
import args.parser.marshaller.StringArrayMarshaller;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class Args {
    private final Map<String, TypedArgument> arguments;

    public Args(Map<String, TypedArgument> arguments) {
        this.arguments = arguments;
    }

    public Optional<Boolean> getBoolean(String key) {
        BooleanMarshaller marshaller = new BooleanMarshaller();
        TypedArgument value = argumentFor(key);
        return marshaller.parse(value);
    }

    public Optional<Integer> getInt(String key) {
        IntegerMarshaller marshaller = new IntegerMarshaller();
        return marshaller.parse(argumentFor(key));

    }

    public Optional<Integer> getInt_with_casting(String key) {
        return getParsedObjectFor(key).map(Integer.class::cast);
    }

    public Optional<String> getString(String key) {
        return getParsedObjectFor(key)
                .map(String.class::cast);
    }

    public Optional<Double> getDouble(String key) {
        DoubleMarshaller marshaller = new DoubleMarshaller();
        return marshaller.parse(argumentFor(key));
    }

    public Optional<String[]> getArray(String key) {
        StringArrayMarshaller marshaller = new StringArrayMarshaller();
        return marshaller.parse(argumentFor(key));
    }

    private Optional<Object> getParsedObjectFor(String key) {
        return Optional.ofNullable(argumentFor(key))
                .flatMap(TypedArgument::parse);
    }

    private TypedArgument argumentFor(String key) {
        return arguments.get(key);
    }

    public Collection<String> keys() {
        return this.arguments.keySet();
    }
}
