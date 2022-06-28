# StableMatchingProblem
Program Assignment 1 for CSS449 Algorithm Design and Analysis

## Description
People will be adopting pets. Each person will adopt exactly one pet and each pet will be adopted by exactly one person. Both people and pets have preference lists that you will read from a file. Implement the Gale-Shapley algorithm for stable matching with an O(n^2) running time.

## File Format
- Line 1: Number of people/pets (n)
- Lines 2 to n+1: Names of people
- Lines n+2 to 2n+1: Preference lists of people using indices, not names (n preferences per line)
- Lines 2n+2 to 3n+1: Names of pets
- Lines 3n+2 to 4n+1: Preference lists of pets using indices, not name (n preferences per line)

## Note
- The program implements the algorithm to be people-optimal (each person is given their best valid match).
- Written in Java
