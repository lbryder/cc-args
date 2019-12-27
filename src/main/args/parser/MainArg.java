package args.parser;

public class MainArg {

    private String value;
    private ArgsType type;

    public static MainArg of(String value, ArgsType type) {
        return new MainArg(value, type);
    }

    private MainArg(String value, ArgsType type) {
        this.value = value;
        this.type = type;
    }

    public String getValue(ArgsType expectedType) {
        if (expectedType != this.type) {
            throw new ArgsException("Type not as expected");
        }
        return value;
    }
}
