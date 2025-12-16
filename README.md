# aoc-2025
Advent of Code 2025

##  State Machine `IdRangeReader`
```mermaid
stateDiagram-v2
 [*] --> firstId: digit
 firstId --> firstId: digit
 firstId --> secondId: hyphen
 secondId --> secondId: digit
 secondId --> [*]: digit
```
