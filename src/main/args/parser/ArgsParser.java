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
        HashMap<String, TypedArgument> arguments = parseToArgumentMap(args);
        validateArguments(arguments);
        return new Args(arguments);
    }

    private HashMap<String, TypedArgument> parseToArgumentMap(String[] args) {
        HashMap<String, TypedArgument> arguments = new HashMap<>();
        int index = 0;
        while (index+1 < args.length) {
            String keyWithPrefix = args[index];
            String argument = args[index+1];
            if (shouldAddToArguments(keyWithPrefix, argument)) {
                TypedArgument typedArgument = getTypedArgument(keyWithPrefix, argument);
                arguments.put(typedArgument.getKey(), typedArgument);
            }
            index++; // FIXME: //Could be +2 if we enter the If, as then the next is an argument, and thus not a key.
        }
        return arguments;
    }

    private TypedArgument getTypedArgument(String keyWithPrefix, String argument) {
        String key = getKey(keyWithPrefix);
        ArgsType type = this.mapping.getOrDefault(key, ArgsType.String);
        return TypedArgument.of(key, argument, type);
    }

    private boolean shouldAddToArguments(String keyWithPrefix, String argumentString) {
        return isKey(keyWithPrefix) && isArgument(argumentString);
    }

    private boolean isArgument(String string) {
        return !isKey(string);
    }

    private String getKey(String keyWithPrefix) {
        return keyWithPrefix.substring(1);
    }

    private boolean isKey(String arg) {
        return arg.startsWith("-");
    }

    private void validateArguments(Map<String, TypedArgument> arguments) {
        arguments.values().forEach(TypedArgument::validate);
    }
}
