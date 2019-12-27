package args.parser;

import java.util.Arrays;
import java.util.Objects;

public enum  ArgsType {
    Bool(""),
    String("*"),
    Integer("#"),
    Double("##"),
    StringArray("[*]"),
    StringMap("&"),
    ;

    private final String code;

    ArgsType(String s) {
        this.code = s;
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

}
