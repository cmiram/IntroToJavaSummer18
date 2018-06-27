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

        // loop until user enters a valid input choice
        while(!isValidChoice(choice))
        {
            System.out.print("Enter 1 for English or 2 for Morse input: ");
            choice = input.nextInt();

            if (isValidChoice(choice))
            {
                System.out.println("Please enter your sentence: ");
                input.nextLine();
                while(!(sentence = input.nextLine()).isEmpty())
                {
                    String translation = choice == 1 ?
                            englishToMorse(sentence) : morseToEnglish(sentence);
                    System.out.println(translation);
                }
            } else
                {
                System.out.println("Please enter a valid input");
            }
        }

    }

    /***
     * Determines if input was a valid choice i.e 1->English 2->Morse
     *
     * @param choice value of user input
     *
     * @return boolean if valid or not
     */
    private static boolean isValidChoice(int choice)
    {
        return choice == 1 || choice == 2;
    }

    /***
     * Translates ascii letters to morse code seperated by spaces
     * and words seperated by the pipe symbol -> |
     *
     * @param english input sentence from the user
     *
     * @return String of equivalent input in morse code
     */
    private static String englishToMorse(String english)
    {
        StringBuilder translation = new StringBuilder();
        // ascii code of capital letters starts at 65
        // offset by 10 because numbers come first in alphabet
        // defined above
        final int letterOffset = ((int) 'A') - 10;

        // split sentence by space delimeter
        String[] words = english.split(" ");

        // loop over each word and translate each letter
        for(String word : words) {
            String morseStr;
            // loop over individual letters
            for (int i = 0; i < word.length(); i++)
            {
                char letter = word.charAt(i);
                // if its a letter grab the ascii code and subtract the offset
                if (Character.isAlphabetic(letter))
                {
                    letter = Character.toUpperCase(letter);
                    morseStr = morse[(int) letter - letterOffset];
                    translation.append(morseStr).append(" ");
                }
                // if its a number just use its value as array index
                else if (Character.isDigit(letter))
                {
                    morseStr = morse[(int) letter];
                    translation.append(morseStr).append(" ");
                }
            }
            // separate each word with a pipe symbol
            translation.append("| ");
        }
        return translation.toString();
    }

    /***
     * Translates a morse string with words separated by pipes into
     * english letters.
     *
     * @param morse String with letters separated by spaces and words
     *              separated by pipe
     *
     * @return String of equivalent english letters and numbers
     */
    private static String morseToEnglish(String morse)
    {
        StringBuilder translation = new StringBuilder();

        // split string by words separated by pipes
        String[] words = morse.split("\\|");
        for(String word : words)
        {
            // split each word into individual letters delimited by a space
            String[] letters = word.split(" ");
            for(String morseLetter : letters)
            {
                // use function to return index of equivalent morse code
                int arrayIndex = getMorseIndex(morseLetter);
                // if -1 it didn't recognize code so don't index on alphabet
                if(arrayIndex >= 0)
                {
                    translation.append(alphabet[arrayIndex]);
                }
            }
            // separate each word with a space
            translation.append(" ");
        }

        return translation.toString();
    }

    /***
     * Iterates over morse array and returns index of matching string or
     * -1 if it is not found
     *
     * @param morseCode String of morse code
     *
     * @return int of index in alphabet of equivalent letter or -1 if not found
     */
    private static int getMorseIndex(String morseCode)
    {
        for(int i=0; i<morse.length; i++)
        {
            if(morseCode.equals(morse[i]))
            {
                return i;
            }
        }
        return -1;
    }
}
