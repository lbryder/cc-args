package args.parser;

import args.Args;
import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ArgsParser {
    private final Map<String, ArgsType> mapping;

    private ArgsParser(Map<String, ArgsType> typeMapping) {
        mapping = ImmutableMap.copyOf(typeMapping);
    }

    public static ArgsParser fromMap(Map<String, ArgsType> typeMapping) {
        return new ArgsParser(typeMapping);
    }

    public static ArgsParser fromPattern(String pattern) {
        String[] split = pattern.split(",");
        Map<String, ArgsType> collect = Arrays.stream(split)
                .collect(Collectors.toMap(ArgsParser::getKeyFromPattern, ArgsParser::getTypeFromPattern));
        return new ArgsParser(collect);
    }

    private static ArgsType getTypeFromPattern(String s) {
        return ArgsType.from(s.substring(1));
    }

    private static String getKeyFromPattern(String s) {
        return String.valueOf(s.charAt(0));
    }

    public Args parse(String... args) {
        HashMap<String, MainArg> arguments = new HashMap<>();
        int i = 0;
        while (i < args.length) {
            String arg = args[i];
            boolean isKey = arg.startsWith("-");
            if (isKey) {
                String key = arg.substring(1);
                String value = args[i + 1];
                ArgsType type = this.mapping.get(key);
                arguments.put(key, MainArg.of(value, type));
                i += 2;
            } else {
                i++;
            }
        }
        return new Args(arguments);
    }
}
