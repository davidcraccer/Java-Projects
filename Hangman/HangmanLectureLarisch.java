import java.util.Scanner;

public class HangmanLectureLarisch {

    // Lecture: 05/23

    public static void main(String[] args) {
        runGame();
    }

    private static void runGame() {
        
        final int maxWrongTurns = 6;
        int wrongTurns = 0;
        int foundCharacters = 0;
        String history = "";
        

        String[] hangman = {"  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========\n", 

                            "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========\n",

                            "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========\n",

                            "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========\n", 

                            "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========\n",

                            "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========\n",

                            "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n=========\n"};

        final String randomWord = randomWord();

        char[] word = initialCharArrayOfLength(randomWord.length());
        System.out.println(word);

        do {
            char characterInput = scanCharacter();
            System.out.println("Valid input was: " + characterInput);

            if (charExistsInWord(characterInput, history.toCharArray())) {
                System.out.println(characterInput + " has already been used, please try again");
                System.out.println("\n#############################################");
                continue;
            }

            boolean existsInWord = charExistsInWord(characterInput, randomWord.toCharArray());
            System.out.println(existsInWord ? "exists" : "Does not exist");
            history += characterInput;

            if (existsInWord) {
                for (int i = 0; i < word.length; i++) {
                    if (randomWord.charAt(i) == characterInput) {
                        word[i] = characterInput;
                        foundCharacters++;
                        System.out.print(hangman[wrongTurns]);

                    }
                }
            } else {
                wrongTurns++;
                System.out.print(hangman[wrongTurns]);
            }

            printRoundInfo(word, wrongTurns, maxWrongTurns);
            System.out.println("\n#############################################");

        } while (foundCharacters < randomWord.length() && wrongTurns < maxWrongTurns);
    }

    private static void printRoundInfo(char[] word, int wrongTurns, int maxWrongTurns) {
        System.out.println(word);
        System.out.println("Wrong turns: " + wrongTurns + "/" + maxWrongTurns);
    }

    private static String randomWord() {
        final String[] words = {
                "JAVA",
                "CODING",
                "PROGRAMMING",
                "INFORMATICS",
                "COMPUTER",
                "HTW",
                "BERLIN",
                "TRESKOWALLEE",
        };

        return words[(int) (Math.random() * words.length)];
    }

    private static boolean charExistsInWord(char needle, char[] haystack) {
        for (char c : haystack) {
            if (c == needle) {
                return true;
            }
        }
        return false;
    }

    private static char scanCharacter() {
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            System.out.print("\nYour input: ");
            input = scanner.nextLine();
            if (input.length() != 1) {
                System.out.println("Input must be of length 1, try again.");
                System.out.println("\n#############################################");

            }
        } while (input.length() != 1);

        return input.toUpperCase().charAt(0);
    }

    private static char[] initialCharArrayOfLength(int length) {
        char[] word = new char[length];
        for (int i = 0; i < word.length; i++) {
            word[i] = '_';
        }
        return word;
    }
}