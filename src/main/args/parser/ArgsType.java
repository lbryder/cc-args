package args.parser;

import args.parser.marshaller.*;

import java.util.Arrays;
import java.util.Objects;

public enum  ArgsType {
    /**
     * True is parsed to true (not case-sensitive). Everything else to false.
     */
    Bool("", new BooleanMarshaller()),
    String("*", new StringMarshaller()),
    /**
     * Negative integers, should be prefixed with "\". E.g. "\-123" is parsed to -123.
     */
    Integer("#", new IntegerMarshaller()),
    /**
     * Doubles, using "." as decimal point.
     */
    Double("##", new DoubleMarshaller()),
    /**
     * Array of strings, separeated by ",". E.g. "ab,c,def" is parsed to ["ab", "c", "def"].
     * Each element is trimmed.
     */
    StringArray("[*]", new StringArrayMarshaller()),
    /**
     * Map with String keys and String values. The map should be given in the format "key1:value1, key2:value2".
     */
    StringMap("&", null), //TODO: Add marshaller.
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

    public ArgsMarshaller<?> getMarshaller() {
        return marshaller;
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
