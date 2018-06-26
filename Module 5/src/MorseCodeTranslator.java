import java.util.Scanner;

/***
 * This project translates a sentence from either english to morse code
 * or morse code back to english.
 */

public class MorseCodeTranslator
{

    private final static String[] alphabet = new String[]{"0", "1", "2", "3",
            "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    private final static String[] morse = new String[]{"-----", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..",
            "----.", ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....",
            "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-",
            ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String sentence;
        int choice = 0;

        while(!isValidChoice(choice)) {
            System.out.print("Enter 1 for English or 2 for Morse input: ");
            choice = input.nextInt();

            if (isValidChoice(choice)) {
                System.out.println("Please enter your sentence: ");
                input.nextLine();
                while(!(sentence = input.nextLine()).isEmpty()) {
                    String translation = choice == 1 ?
                            englishToMorse(sentence) : morseToEnglish(sentence);
                    System.out.println(translation);
                }
            } else {
                System.out.println("Please enter a valid input");
            }
        }

    }

    private static boolean isValidChoice(int choice)
    {
        return choice == 1 || choice == 2;
    }

    private static String englishToMorse(String english)
    {
        StringBuilder translation = new StringBuilder();
        final int letterOffset = ((int) 'A') - 10;
        String[] words = english.split(" ");
        for(String word : words) {
            String morseStr;
            for (int i = 0; i < word.length(); i++) {
                char letter = word.charAt(i);
                if (Character.isAlphabetic(letter)) {
                    letter = Character.toUpperCase(letter);
                    morseStr = morse[(int) letter - letterOffset];
                    translation.append(morseStr).append(" ");
                } else if (Character.isDigit(letter)) {
                    morseStr = morse[(int) letter];
                    translation.append(morseStr).append(" ");
                }
            }
            translation.append("| ");
        }
        return translation.toString();
    }

    private static String morseToEnglish(String morse)
    {
        StringBuilder translation = new StringBuilder();
        String[] words = morse.split("\\|");
        for(String word : words) {
            String[] letters = word.split(" ");
            for(String morseLetter : letters) {
                int arrayIndex = getMorseIndex(morseLetter);
                if(arrayIndex >= 0) {
                    translation.append(alphabet[arrayIndex]);
                }
            }
            translation.append(" ");
        }

        return translation.toString();
    }

    private static int getMorseIndex(String morseCode)
    {
        for(int i=0; i<morse.length; i++) {
            if(morseCode.equals(morse[i])) {
                return i;
            }
        }
        return -1;
    }
}
