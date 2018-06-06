import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    // number of lines per ascii art letter
    private static final int letterStrHeight = 5;

    // each letter as ascii art in a string with 5 lines
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

    // map to load art strings to
    private static Map<Character, String> asciiArt;

    public static void main(String[] args) {
	    //MY JAVA USING INTELLIJ
        // load strings into map
        loadAsciiArt();
        // set string of what to draw on console
        final String myStr = "my java\nusing\nintellij";
        // split string by new lines and draw line by line
        Arrays.stream(myStr.split("\n")).forEach(line -> {
            // function to draw a string from ascii map
            printStringByLine(line);
            // print new line to separate each string
            System.out.print('\n');
        });
    }

    private static void loadAsciiArt() {
        // map each char letter to its corresponding string art
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
        // iterate over ascii art string lines which are all the same height as spec-ed above
        for(int i=0; i<letterStrHeight; i++) {
            // take each char in string and print current line of outer for-loop
            for(char c : str.toCharArray()) {
                // if char is recognized in map grab string, split by line, and output current line
                // plus a space otherwise letters run into each other
                if(asciiArt.containsKey(c)) {
                    System.out.print(asciiArt.get(c).split("\n")[i] + "  ");
                }
                // if a space just output a space like the original sentence
                else if(c == ' ') {
                    System.out.print(" ");
                }
                // if given a string containing a letter not pre-rendered throw an exception
                // assumption is we would not draw a string with a letter that wasn't pre-drawn
                else {
                    throw new IllegalArgumentException("Input string contains character not drawn");
                }
            }
            // print new line before returning to separate lines like in the original sentence
            System.out.print("\n");
        }
    }
}
