package args;

import args.parser.TypedArgument;
import args.parser.marshaller.*;

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

    public Optional<String> getString(String key) {
        StringMarshaller marshaller = new StringMarshaller();
        return marshaller.parse(argumentFor(key));
    }

    public Optional<Double> getDouble(String key) {
        DoubleMarshaller marshaller = new DoubleMarshaller();
        return marshaller.parse(argumentFor(key));
    }

    public Optional<String[]> getArray(String key) {
        StringArrayMarshaller marshaller = new StringArrayMarshaller();
        return marshaller.parse(argumentFor(key));
    }

    private TypedArgument argumentFor(String key) {
        return arguments.get(key);
    }

    public Collection<String> keys() {
        return this.arguments.keySet();
    }
}
