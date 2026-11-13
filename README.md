# Rubiks-Cube-Solver
A Rubik's Cube simulator and solver written in Java. The cube is modeled with 2D character
arrays and move logic for all 18 standard moves (U, F2, L', etc.), validated through
inverse-scramble testing. A piece-finding system locates any edge or corner piece on the
cube and reports its orientation, driving a beginner-method solver that returns the full
move-by-move solution.

## Features

- **Complete cube model** — six 3x3 face arrays with correct turn mechanics for all six
  faces, including direction handling for faces viewed from their own side (U, D, F, B,
  R, L and all variants)
- **Random scrambles** — generates valid 20-move scrambles with no consecutive same-face
  moves, following WCA-style scramble conventions
- **Piece-finding engine** — coordinate tables for all 12 edges and 8 corners, with lookup
  methods that report where any piece is and which way its stickers face
- **Beginner-method solver** — solves in stages (white cross → first layer → F2L → OLL →
  PLL), with each stage verified by its own milestone check before proceeding
- **Solution reporting** — move history with total solution length, the full move sequence, and
  the time it either took the user or computer to solve the cube
- **Interactive console interface** — scramble manually or randomly, solve the cube yourself
  with move-by-move input, or have the program solve it
- **Piece report tool** — a human-readable census of where every edge and corner is and how
  each sticker faces, useful for debugging and for learning how the solver "sees" the cube

## How It Works

The program separates the cube engine, piece inspection, solving algorithms, and user
interface across four classes. All cube state lives in the `RubiksCube` class: the face
arrays, the turn methods, and a silent `apply()` method that executes any move sequence —
the single engine used by user input, scrambles, and the solver alike. `CubeInspector` reads
that state to locate individual pieces, while `CubeSolver` uses those lookups to drive the
solve.

The solver works by *case enumeration*: for each piece in each stage, it detects the
piece's position and orientation, then applies a fixed, tested insertion sequence for
that case. Combined methods such as `solveWhiteCross()`, `solveOLL()`, `solveF2L()`, and 
`solvePLL()` work to make sure the steps progress in chronological order.

## Project Structure

| File | Purpose |
|---|---|
| `RubiksCube.java` | Cube engine: 2D face state, 18 turn mechanics, scrambling, move validation, and move history |
| `CubeInspector.java` | Piece-finding engine: edge/corner coordinate tables, piece lookup methods, and state reporting |
| `CubeSolver.java` | Beginner-method solver: cross, first layer, F2L, 2-step OLL, PLL, and AUF algorithms |
| `Main.java` | Console interface: program entry point, interactive user solve mode, and timed computer solve mode |
| `README.md` | This file |

## Getting Started

Compile and run:

```bash
cd ~/Desktop/Rubiks-Cube-Solver
git pull
javac src/*.java
java -cp src Main
  
```


## Future Work


- **Machine learning–assisted solving** — ML could reduce both solve time and move count at
  two scales: *global* (a learned model mapping cube states directly to solutions, as recent
  research has done with near-optimal results) and *local* (discovering shorter algorithms
  for specific recurring cases than the hand-picked beginner sequences used here). Currently,
  the program solves the Rubik's Cube in just over 100 moves. However, utilizing the powers of
  machine learning, this number could most likely shrink all the way down to below 20 moves on
  average.
 - **Limitation**: Despite what was aforementioned, human solvers cannot realistically match
  ML-optimized solutions, so an ML-generated solution may be fast for a computer but
  impractical as a teaching tool for how people actually learn to solve — a real trade-off for
  a program whose stages mirror the human beginner method.
- **True 3D rendering** — a 3D view with rotating faces would better communicate piece
  movement than the current face-by-face display.
- **CFOP upgrade** — replace the beginner stages with F2L pairing and fuller OLL/PLL case
  tables to reduce average solution length.
- **Benchmarking** — run many random scrambles and report average solution length and
  solve time.
- **State input with validation** — accept a raw sticker-grid state; requires legality
  checks (color counts, piece existence, permutation parity), a nontrivial subproblem.
- **Undo/redo** — the move history already supports inverse application.

