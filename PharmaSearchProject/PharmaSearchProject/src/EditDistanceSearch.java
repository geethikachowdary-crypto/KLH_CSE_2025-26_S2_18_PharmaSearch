import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Comparator;
import java.util.Scanner;

public class EditDistanceSearch {

    // All 16 text files in the project
    static String[] fileNames = {
        "algorithms.txt",
        "dosage_information.txt",
        "drug_interactions.txt",
        "future_scope.txt",
        "manufacturers.txt",
        "medicine_categories.txt",
        "medicine_information.txt",
        "modules.txt",
        "pharmacy_information.txt",
        "prescription_medicines.txt",
        "problem_and_solution.txt",
        "project_overview.txt",
        "side_effects.txt",
        "storage_information.txt",
        "substitutes.txt",
        "technologies.txt"
    };


    // ----------------------------------------------------
    // Find the data folder
    // ----------------------------------------------------

    public static File findDataFile(String fileName) {

        // Possible locations
        String[] possiblePaths = {
            "data" + File.separator + fileName,
            ".." + File.separator + "data" + File.separator + fileName,
            "src" + File.separator + ".." + File.separator
                + "data" + File.separator + fileName
        };

        for (String path : possiblePaths) {

            File file = new File(path);

            if (file.exists() && file.isFile()) {
                return file;
            }
        }

        // Search current folder and parent folders
        File currentFolder =
            new File(".").getAbsoluteFile();

        for (int i = 0; i < 5 && currentFolder != null; i++) {

            File dataFolder =
                new File(currentFolder, "data");

            File file =
                new File(dataFolder, fileName);

            if (file.exists() && file.isFile()) {
                return file;
            }

            currentFolder =
                currentFolder.getParentFile();
        }

        return null;
    }


    // ----------------------------------------------------
    // Store one search result
    // ----------------------------------------------------

    static class SearchResult {

        String word;
        int distance;
        String fileName;

        SearchResult(
            String word,
            int distance,
            String fileName
        ) {

            this.word = word;
            this.distance = distance;
            this.fileName = fileName;
        }
    }


    // ----------------------------------------------------
    // Extract words from a file
    // ----------------------------------------------------

    public static ArrayList<String> extractWords(
        String fileName
    ) {

        ArrayList<String> words =
            new ArrayList<>();

        File file =
            findDataFile(fileName);

        if (file == null) {

            System.out.println(
                "Warning: File not found: " + fileName
            );

            return words;
        }


        try {

            BufferedReader reader =
                new BufferedReader(
                    new FileReader(file)
                );

            String line;


            while ((line = reader.readLine()) != null) {

                /*
                 * Split the line into individual words.
                 * Only alphabetic characters are considered.
                 */
                String[] parts =
                    line.split("[^a-zA-Z]+");


                for (String word : parts) {

                    if (!word.isEmpty()) {

                        words.add(word);
                    }
                }
            }

            reader.close();

        } catch (IOException e) {

            System.out.println(
                "Error reading file: " + file.getPath()
            );
        }

        return words;
    }


    // ----------------------------------------------------
    // Determine similarity threshold
    // ----------------------------------------------------

    public static int getThreshold(
        String searchWord
    ) {

        if (searchWord.length() <= 4) {

            return 1;

        } else if (searchWord.length() <= 7) {

            return 2;

        } else {

            return 3;
        }
    }


    // ----------------------------------------------------
    // MAIN METHOD
    // ----------------------------------------------------

    public static void main(String[] args) {

        Scanner scanner =
            new Scanner(System.in);


        System.out.println();
        System.out.println(
            "=========================================="
        );
        System.out.println(
            "       PHARMA SEARCH - EDIT DISTANCE"
        );
        System.out.println(
            "=========================================="
        );


        System.out.print(
            "Enter search word: "
        );


        String searchWord =
            scanner.nextLine()
                   .trim()
                   .toLowerCase();


        // Check empty input
        if (searchWord.isEmpty()) {

            System.out.println(
                "Search word cannot be empty."
            );

            scanner.close();
            return;
        }


        int threshold =
            getThreshold(searchWord);


        ArrayList<SearchResult> results =
            new ArrayList<>();


        // Prevent duplicate word-file combinations
        HashSet<String> processedWords =
            new HashSet<>();


        // ------------------------------------------------
        // Read all 16 files
        // ------------------------------------------------

        for (String fileName : fileNames) {

            ArrayList<String> words =
                extractWords(fileName);


            for (String originalWord : words) {

                String lowerWord =
                    originalWord.toLowerCase();


                String uniqueKey =
                    lowerWord + "|" + fileName;


                if (processedWords.contains(uniqueKey)) {

                    continue;
                }


                processedWords.add(uniqueKey);


                // Calculate Edit Distance
                int distance =
                    EditDistance.editDistance(
                        searchWord,
                        lowerWord
                    );


                // Add similar words
                if (distance <= threshold) {

                    results.add(
                        new SearchResult(
                            originalWord,
                            distance,
                            fileName
                        )
                    );
                }
            }
        }


        // ------------------------------------------------
        // Sort results
        // ------------------------------------------------

        results.sort(
            Comparator
                .comparingInt(
                    (SearchResult r) -> r.distance
                )
                .thenComparing(
                    r -> r.word.toLowerCase()
                )
                .thenComparing(
                    r -> r.fileName
                )
        );


        // ------------------------------------------------
        // Display results
        // ------------------------------------------------

        System.out.println();


        if (results.isEmpty()) {

            System.out.println(
                "========== NO MATCH FOUND =========="
            );

            System.out.println();

            System.out.println(
                "No sufficiently similar word was found."
            );

            System.out.println(
                "Try another search word."
            );

        } else {

            // Check whether an exact match exists
            boolean exactMatch =
                results.get(0).distance == 0;


            if (exactMatch) {

                System.out.println(
                    "========== EXACT MATCH FOUND =========="
                );

            } else {

                System.out.println(
                    "========== POSSIBLE MATCHES =========="
                );
            }


            // Display maximum 5 results
            int count =
                Math.min(5, results.size());


            for (int i = 0; i < count; i++) {

                SearchResult result =
                    results.get(i);


                System.out.println();

                System.out.println(
                    (i + 1) + ". " + result.word
                );

                System.out.println(
                    "   Edit Distance: "
                    + result.distance
                );

                System.out.println(
                    "   File: data/"
                    + result.fileName
                );
            }
        }


        System.out.println();

        System.out.println(
            "Similarity threshold used: "
            + threshold
        );


        scanner.close();
    }
}