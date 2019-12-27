import args.Args;
import args.parser.ArgsException;
import args.parser.ArgsParser;
import args.parser.ArgsType;
import com.google.common.collect.ImmutableMap;

public class Main {
    private static ArgsParser parser = ArgsParser.fromPattern("l,p#,d*");
    private static ArgsParser parser_map = ArgsParser.fromMap(ImmutableMap.of(
            "l", ArgsType.Bool,
            "p",ArgsType.Integer,
            "d", ArgsType.String,
            "kk", ArgsType.Double
    ));


    public static void main(String[] args) {
        try {
            Args arg = parser.parse(args);
            Args argMap = parser_map.parse(args);

            boolean logging = arg.getBoolean("l")
                    .orElse(false);
            int port = arg.getInt("p")
                    .orElse(-1);
            String directory = argMap.getString("d")
                    .orElse("N/A");
            double age = argMap.getDouble("kk")
                    .orElse(0.1d);
            executeApplication(logging, port, directory);
        } catch (ArgsException e) {
            System.out.printf("Argument error: %s\n", e.getMessage());
        }
    }

    private static void executeApplication(boolean logging, int port, String directory) {
        System.out.printf("logging is %s, port:%d, directory:%s\n", logging, port, directory);
    }
}


//Schema:
//        - char    - Boolean arg.
//        - char*   - String arg.
//        - char#   - Integer arg.
//        - char##  - double arg.
//        - char[*] - one element of a string array.
//
//        Example schema: (f,s*,n#,a##,p[*])
//        Coresponding command line: "-f -s Bob -n 1 -a 3.2 -p e1 -p e2 -p e3
