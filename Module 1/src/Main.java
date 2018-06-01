import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final int letterStrHeight = 5;

    private static final String A =
            "    A    \n" +
            "   A A   \n" +
            "  AAAAA  \n" +
            " A     A \n" +
            "A       A";

    private static final String E =
            "EEEEE\n" +
            "E    \n" +
            "EEEEE\n" +
            "E    \n" +
            "EEEEE";

    private static final String G =
            "GGGG  \n" +
            "G     \n" +
            "G GGGG\n" +
            "G    G\n" +
            "GGGGGG";

    private static final String I =
            "IIIII\n" +
            "  I  \n" +
            "  I  \n" +
            "  I  \n" +
            "IIIII";

    private static final String J =
            "   J\n" +
            "   J\n" +
            "   J\n" +
            "J  J\n" +
            " JJ ";

    private static final String L =
            "L     \n" +
            "L     \n" +
            "L     \n" +
            "L     \n" +
            "LLLLLL";

    private static final String M =
            "MM    MM\n" +
            "M  M M M\n" +
            "M   M  M\n" +
            "M      M\n" +
            "M      M";

    private static final String N =
            "N    N\n" +
            "NN   N\n" +
            "N N  N\n" +
            "N  N N\n" +
            "N   NN";

    private static final String S =
            "SSSSS\n" +
            "S    \n" +
            " SS  \n" +
            "    S\n" +
            "SSSS ";

    private static final String T =
            "TTTTTT\n" +
            "  T   \n" +
            "  T   \n" +
            "  T   \n" +
            "  T   ";

    private static final String U =
            "U     U\n" +
            "U     U\n" +
            "U     U\n" +
            " U   U \n" +
            "  UUU  ";

    private static final String V =
            "V       V\n" +
            " V     V \n" +
            "  V   V  \n" +
            "   V V   \n" +
            "    V    ";

    private static final String Y =
            "Y   Y\n" +
            " Y Y \n" +
            "  Y  \n" +
            "  Y  \n" +
            "  Y  ";

    private static Map<Character, String> asciiArt;

    public static void main(String[] args) {
	    //MY JAVA USING INTELLIJ
        loadAsciiArt();
        final String myStr = "my java\nusing\nintellij";
        Arrays.stream(myStr.split("\n")).forEach(line -> {
            printStringByLine(line);
            System.out.print('\n');
        });
    }

    private static void loadAsciiArt() {
        asciiArt = new HashMap<>(13);
        asciiArt.put('a', A);
        asciiArt.put('e', E);
        asciiArt.put('g', G);
        asciiArt.put('i', I);
        asciiArt.put('j', J);
        asciiArt.put('l', L);
        asciiArt.put('m', M);
        asciiArt.put('n', N);
        asciiArt.put('s', S);
        asciiArt.put('t', T);
        asciiArt.put('u', U);
        asciiArt.put('v', V);
        asciiArt.put('y', Y);
    }

    private static void printStringByLine(String str) {
        for(int i=0; i<letterStrHeight; i++) {
            for(char c : str.toCharArray()) {
                if(asciiArt.containsKey(c)) {
                    System.out.print(asciiArt.get(c).split("\n")[i] + "  ");
                }
                else if(c == ' ') {
                    System.out.print(" ");
                }
                else {
                    throw new IllegalArgumentException("Input string contains character not drawn");
                }
            }
            System.out.print("\n");
        }
    }
}
