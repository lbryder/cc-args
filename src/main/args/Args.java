package args;

import args.parser.MainArg;
import args.parser.marshaller.BooleanMarshaller;
import args.parser.marshaller.DoubleMarshaller;
import args.parser.marshaller.IntegerMarshaller;
import args.parser.marshaller.StringMarshaller;

import java.util.Map;
import java.util.Optional;

public class Args {
    private final Map<String, MainArg> arguments;

    public Args(Map<String, MainArg> arguments) {
        this.arguments = arguments;
    }

    public Optional<Boolean> getBoolean(String key) {
        BooleanMarshaller marshaller = new BooleanMarshaller();
        MainArg value = argumentFor(key);
        return marshaller.parse(value, key);
    }

    public Optional<Integer> getInt(String key) {
        IntegerMarshaller marshaller = new IntegerMarshaller();
        return marshaller.parse(argumentFor(key), key);

    }

    public Optional<String> getString(String key) {
        StringMarshaller marshaller = new StringMarshaller();
        return marshaller.parse(argumentFor(key), key);
    }

    public Optional<Double> getDouble(String key) {
        DoubleMarshaller marshaller = new DoubleMarshaller();
        return marshaller.parse(argumentFor(key), key);
    }

    private MainArg argumentFor(String key) {
        return arguments.get(key);
    }
}
