import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    // KMP string matching algorithm.
    // Returns true if the pattern occurs inside the text.
    public static boolean kmpSearch(String text, String pattern) {
        if (pattern.length() == 0) {
            return true;
        }

        int[] lps = buildLPS(pattern);

        int i = 0; // position in text
        int j = 0; // position in pattern

        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;

                if (j == pattern.length()) {
                    return true;
                }
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }

        return false;
    }

    // Builds the Longest Proper Prefix which is also Suffix array.
    private static int[] buildLPS(String pattern) {
        int[] lps = new int[pattern.length()];

        int length = 0;
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else if (length > 0) {
                length = lps[length - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }

        return lps;
    }

    // Searches every complete sentence in one text file.
    private static int searchFile(Path file, String keyword) {
        int matches = 0;

        try {
            List<String> lines = Files.readAllLines(file);

            for (String line : lines) {
                String[] sentences = line.split("(?<=[.!?])\\s+");

                for (String sentence : sentences) {
                    String cleanSentence = sentence.trim();

                    if (cleanSentence.isEmpty()) {
                        continue;
                    }

                    String lowerSentence = cleanSentence.toLowerCase();
                    String lowerKeyword = keyword.toLowerCase();

                    if (kmpSearch(lowerSentence, lowerKeyword)) {
                        System.out.println("File: " + file.getFileName());
                        System.out.println(cleanSentence);
                        System.out.println();
                        matches++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read file: " + file.getFileName());
        }

        return matches;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("Keyword cannot be empty.");
            sc.close();
            return;
        }

        Path dataFolder = Paths.get("data");

        if (!Files.exists(dataFolder) || !Files.isDirectory(dataFolder)) {
            System.out.println("The data folder was not found.");
            sc.close();
            return;
        }

        int totalMatches = 0;

        try (Stream<Path> files = Files.list(dataFolder)) {
            List<Path> textFiles = files
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().toLowerCase().endsWith(".txt"))
                    .sorted()
                    .toList();

            for (Path file : textFiles) {
                totalMatches += searchFile(file, keyword);
            }

        } catch (IOException e) {
            System.out.println("Error while reading the data folder.");
        }

        System.out.println("Keyword: " + keyword);
        System.out.println();

        if (totalMatches == 0) {
            System.out.println("No matching sentence found.");
        }

        System.out.println();
        System.out.println("Total matching sentences: " + totalMatches);

        sc.close();
    }
}
