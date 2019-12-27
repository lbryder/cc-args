package args.parser;

import args.Args;
import com.google.common.collect.ImmutableMap;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;

class ArgsParserTest {

    @Test
    void parseInt() {
        ArgsParser parser = ArgsParser.fromMap(ImmutableMap.of("x", ArgsType.Integer));
        Args args = parser.parse("-x", "120");

        Integer actual = args.getInt("x").orElse(0);
        Assertions.assertEquals(120, actual);
    }

    @Test
    void parse_negative_Int() {
        ArgsParser parser = ArgsParser.fromMap(ImmutableMap.of("x", ArgsType.Integer));
        Args args = parser.parse("-x", "\\-120");

        Integer actual = args.getInt("x").orElse(0);
        Assertions.assertEquals(-120, actual);
    }

    @Test
    void parse_bad_Int() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            ArgsParser parser = ArgsParser.fromMap(ImmutableMap.of("x", ArgsType.Integer));
            Args args = parser.parse("-x", "abc");
       });
    }

    @Test
    void noArgumentForKey() {
        ArgsParser parser = ArgsParser.fromMap(ImmutableMap.of("x", ArgsType.Bool));
        Args args = parser.parse("-x");

        Collection<String> keys = args.keys();
        assertThat(keys, Matchers.empty());
    }

    @Test
    void stringArray() {
        String[] expected = {"a", "hej", "1234"};
        ArgsParser parser = ArgsParser.fromMap(ImmutableMap.of("x", ArgsType.StringArray));
        Args args = parser.parse("-x", "a,hej,1234");
        String[] actual = args.getArray("x").get();

        assertThat(actual.length, Matchers.equalTo(expected.length));
        assertThat(actual, Matchers.arrayContaining(expected));
    }

    @Test
    void severalArguments() {
        ArgsParser parser = ArgsParser.fromMap(ImmutableMap.of(
                "d", ArgsType.Double,
                "int", ArgsType.Integer,
                "bo", ArgsType.Bool
        ));
        Args args = parser.parse("-d", "12.34", "-int", "123", "-bo", "true");

        Double actualDouble = args.getDouble("d").get();
        Integer actualInt = args.getInt("int").orElse(null);
        Boolean actualBool = args.getBoolean("bo").orElse(null);

        assertThat(actualDouble, Matchers.closeTo(12.34d, 0.001));
        Assertions.assertEquals(123, actualInt);
        Assertions.assertEquals(true, actualBool);
    }

    @Test
    void severalArguments_with_noise() {
        ArgsParser parser = ArgsParser.fromMap(ImmutableMap.of(
                "d", ArgsType.Double,
                "int", ArgsType.Integer,
                "bo", ArgsType.Bool
        ));
        Args args = parser.parse("noise,0", "-d", "12.34", "-int", "123", "noise_1", "-bo", "true", "noise-2");

        Double actualDouble = args.getDouble("d").get();
        Integer actualInt = args.getInt("int").orElse(null);
        Boolean actualBool = args.getBoolean("bo").orElse(null);

        assertThat(actualDouble, Matchers.closeTo(12.34d, 0.001));
        Assertions.assertEquals(123, actualInt);
        Assertions.assertEquals(true, actualBool);
    }
}