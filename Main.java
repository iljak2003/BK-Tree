import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        MyBKTree tree = new MyBKTree();

        boolean running = true;

        System.out.println("====================================");
        System.out.println("        BK-Tree Console App         ");
        System.out.println("====================================");

        while (running) {
            printMenu();

            System.out.print("Choose option: ");
            String choice = console.nextLine().trim();

            switch (choice) {
                case "1":
                    addOneWord(console, tree);
                    break;
                case "2":
                    loadWordsFromFile(console, tree);
                    break;
                case "3":
                    searchSimilarWords(console, tree);
                    break;
                case "4":
                    running = false;
                    System.out.println("Program finished.");
                    break;
                default:
                    System.out.println("Unknown command. Try again.");
            }

            System.out.println();
        }

        console.close();
    }

    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1 - Add one word");
        System.out.println("2 - Load words from file");
        System.out.println("3 - Find similar words");
        System.out.println("4 - Exit");
    }

    private static void addOneWord(Scanner console, MyBKTree tree) {
        System.out.print("Enter word: ");
        String word = console.nextLine().trim();

        if (word.isEmpty()) {
            System.out.println("Empty word was not added.");
            return;
        }

        tree.add(word);
        System.out.println("Word \"" + word + "\" added to tree.");
    }

    private static void loadWordsFromFile(Scanner console, MyBKTree tree) {
        System.out.print("Enter file path: ");
        String path = console.nextLine().trim();

        File file = new File(path);

        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }

        int count = 0;

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                String word = fileScanner.next().trim();

                if (!word.isEmpty()) {
                    tree.add(word);
                    count++;
                }
            }

            System.out.println("Loaded words from file: " + count);

        } catch (FileNotFoundException e) {
            System.out.println("File read error: " + e.getMessage());
        }
    }

    private static void searchSimilarWords(Scanner console, MyBKTree tree) {
        System.out.print("Enter word to search: ");
        String word = console.nextLine().trim();

        if (word.isEmpty()) {
            System.out.println("Cannot search for empty word.");
            return;
        }

        System.out.print("Enter tolerance: ");
        String toleranceInput = console.nextLine().trim();

        int tolerance;

        try {
            tolerance = Integer.parseInt(toleranceInput);
        } catch (NumberFormatException e) {
            System.out.println("Tolerance must be an integer.");
            return;
        }

        List<String> result = tree.getSimilarWords(word, tolerance);

        System.out.println("------------------------------------");
        System.out.println("Search results for: " + word);
        System.out.println("Tolerance: " + tolerance);
        System.out.println("------------------------------------");

        if (result.isEmpty()) {
            System.out.println("No similar words found.");
        } else {
            for (String similarWord : result) {
                System.out.println(similarWord);
            }
        }
    }
}