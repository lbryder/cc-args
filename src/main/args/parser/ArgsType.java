package args.parser;

import args.parser.marshaller.*;

import java.util.Arrays;
import java.util.Objects;

public enum  ArgsType {
    Bool("", new BooleanMarshaller()),
    String("*", new StringMarshaller()),
    Integer("#", new IntegerMarshaller()),
    Double("##", new DoubleMarshaller()),
    StringArray("[*]", new StringArrayMarshaller()),
    StringMap("&", new BooleanMarshaller()),
    ;

    private final String code;
    private ArgsMarshaller<?> marshaller;

    ArgsType(String s, ArgsMarshaller<?> marshaller) {
        this.code = s;
        this.marshaller = marshaller;
    }

    public java.lang.String getCode() {
        return code;
    }

    public static ArgsType from(String value) {
        Objects.requireNonNull(value);
        return Arrays.stream(ArgsType.values())
                .filter(t -> value.equals(t.getCode()))
                .findFirst()
                .orElseThrow(() -> new ArgsException("Invalid Pattern argument"));
    }

    public void validate(TypedArgument value) {
        this.marshaller.test(value);
    }
}
