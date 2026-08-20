# Pharma Search

**Course:** Data Structures and Algorithms – 3  
**Team:** 9  
**Supervisor:** Dr. S. Vinay Kumar, Associate Professor, Department of Computer Science and Engineering  
**Current Phase:** Pattern/String Matching Implementation – Review 2

---

## Team Details

| S. No. | Student Name | Roll Number |
|-------:|--------------|-------------|
| 1 | M. Geethika Chowdary | 2520030056 |
| 2 | K. Poojitha Sai | 2520030103 |
| 3 | R. Alekhya | 2520030209 |

---

## Abstract

The **Pharma Search** system is a Data Structures and Algorithms based project designed to provide efficient search and retrieval of information from a structured pharmaceutical drug corpus.

The system processes a collection of medicine and pharmaceutical records and applies advanced string-matching algorithms to support efficient pattern and keyword searching across information such as medicine names, uses, symptoms, ingredients, categories, manufacturers, and other relevant attributes.

For pattern matching, the system implements the **Knuth-Morris-Pratt (KMP)** and **Rabin-Karp** algorithms. KMP uses the Longest Proper Prefix which is also Suffix (LPS) array to avoid unnecessary comparisons, while Rabin-Karp uses a rolling hash to identify candidate matches efficiently.

The project demonstrates the practical application of advanced string-matching algorithms in a real-world pharmaceutical information search scenario.

---

## Objectives

1. Implement efficient pattern and string-matching algorithms.
2. Search pharmaceutical information stored in the project's drug corpus.
3. Compare KMP and Rabin-Karp for the same search queries.
4. Verify consistency of matching results produced by different algorithms.
5. Measure and compare algorithm execution performance.
6. Provide efficient searching of medicines and pharmaceutical information.
7. Demonstrate practical applications of Data Structures and Algorithms in a pharmaceutical search scenario.

---

## Algorithms and Data Structures

The project includes:

- Knuth-Morris-Pratt (KMP) pattern matching
- Rabin-Karp pattern matching
- Pattern/String Matching
- Rolling Hash
- Pharmaceutical record corpus loading
- Search result matching
- Algorithm performance comparison

Future phases may incorporate additional DSA-3 concepts based on the project requirements and course progression.

---

## Current Phase Status

### Review 2 – Pattern/String Matching

**Status:** In Progress

The current implementation focuses on:

- KMP string matching
- Rabin-Karp string matching
- Search over the pharmaceutical drug corpus
- Comparison of KMP and Rabin-Karp
- Matching-result consistency verification
- Execution-time benchmarking
- Operation-count comparison

Both algorithms will be integrated into the Pharma Search engine.

---

## Project Flow

```text
Pharmaceutical Drug Corpus
          |
          v
     Corpus Loader
          |
          v
   Pharmaceutical Records
          |
          v
       User Query
          |
          +-------------------+
          |                   |
          v                   v
         KMP             Rabin-Karp
          |                   |
          +---------+---------+
                    |
                    v
       Matching Pharmaceutical Records
                    |
                    v
          Algorithm Comparison
                    |
                    v
          Performance Analysis
