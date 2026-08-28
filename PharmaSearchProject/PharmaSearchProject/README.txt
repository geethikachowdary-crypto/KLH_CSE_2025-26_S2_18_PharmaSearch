PHARMA SEARCH - JAVA TEXT SEARCH DEMONSTRATION

Folder structure:

PharmaSearchProject/
├── src/
│   └── Main.java
└── data/
    ├── project_overview.txt
    ├── problem_and_solution.txt
    ├── features.txt
    ├── system_working.txt
    ├── technologies.txt
    ├── modules.txt
    └── future_scope.txt

How to run:

1. Open the PharmaSearchProject folder in a Java IDE.
2. Compile and run src/Main.java.
3. Keep the data folder in the project root, at the same level as src.
4. Enter a keyword that occurs in the project information.

Example keywords:
medicine
search
KMP
users
dosage
manufacturer
substitutes
algorithms
pharmaceutical

The search is case-insensitive. The actual keyword matching is performed by the manually implemented KMP algorithm. String.contains(), String.indexOf(), and String.matches() are not used for the actual search.

The program reads every .txt file in the data folder automatically and prints the complete sentence containing the keyword.
