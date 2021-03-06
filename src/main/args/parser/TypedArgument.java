package args.parser;

import java.util.Optional;

public class TypedArgument {

    private String key;
    private String value;
    private ArgsType type;

    public static TypedArgument of(String key, String value, ArgsType type) {
        return new TypedArgument(key, value, type);
    }

    private TypedArgument(String key, String value, ArgsType type) {
        this.key = key;
        this.value = value;
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public String getValueAsString() {
        return value;
    }

    public String getValueOfType(ArgsType expectedType) {
        if (expectedType != this.type) {
            throw new ArgsException("Type not as expected");
        }
        return value;
    }

    public void validate() {
        this.type.validate(this);
    }

    public Optional<?> parse() {
        return type.getMarshaller().parse(this);
    }
}
