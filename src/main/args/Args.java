package args;

import args.parser.ArgsException;
import args.parser.MainArg;
import args.parser.marshaller.BooleanMarshaller;
import args.parser.marshaller.DoubleMarshaller;
import args.parser.marshaller.IntegerMarshaller;
import args.parser.marshaller.StringMarshaller;

import java.util.HashMap;
import java.util.Optional;

public class Args {
    private final HashMap<String, MainArg> arguments;

    public Args(HashMap<String, MainArg> arguments) {
        this.arguments = arguments;
    }

    public Optional<Boolean> getBoolean(String key) {
        BooleanMarshaller marshaller = new BooleanMarshaller();
        return marshaller.parse(argumentFor(key));
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

    private MainArg argumentFor(String key) {
        return arguments.get(key);
    }
}
