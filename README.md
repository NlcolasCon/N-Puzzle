# N-Puzzle Game

An interactive Java implementation of the classic **N-Puzzle** problem.  
The project allows users to play the sliding puzzle interactively or let the computer attempt automatic solutions with randomized moves and statistical evaluation.

![N-Puzzle Logo](assets/N_Puzzle.png)

---

## Project Explanation
The N-Puzzle is a sliding puzzle consisting of an `N x N` grid filled with numbered tiles and a single empty space.  
The objective is to rearrange the tiles into the correct order by sliding them into the empty space.

This implementation supports:
- Interactive mode, where the user controls the moves (`l`, `r`, `u`, `d`, `e`).
- Automatic mode, where the program plays multiple randomized games and calculates success statistics.
- Configurable puzzle size (`N`) and display mode (`text-based` or graphics-ready with **StdDraw**).

The project demonstrates:
- **2D arrays and matrix manipulation**  
- **Input validation and error handling**  
- **Procedural decomposition (Library + Client)**  
- **Randomized shuffling and difficulty levels**  
- **Interactive menu-driven design**  

---

## Repository Structure
N-Puzzle/
┣ src/ # Java source code
┃ ┣ Client.java
┃ ┣ Library.java
┃ ┗ Options.java
┣ assets/ # Images/media
┃ ┗ N_Puzzle.png
┣ .gitignore # Ignore IDE/build artifacts
┣ LICENSE # MIT open-source license
┣ README.md # Project documentation


---

## Features
- Menu-driven interface:
  - **Interactive play** – user solves the puzzle manually.  
  - **Automatic play** – program runs simulations and computes success rates.  
  - **Exit option** – clean termination.  
- Configurable difficulty (`k` random shuffles).  
- Support for text-based display of the puzzle.  
- Extendable to graphical display with **StdDraw**.  
- Statistics collection in automatic mode:
  - Success percentage.  
  - Average moves in successful games.  

---

## Technologies
- Language: Java
- Paradigm: Procedural + modular decomposition
- Tools: Any Java-compatible IDE (Eclipse, IntelliJ, VS Code)

---

## Author
- Developed by Nicolas Constantinou
- 2025

---

## Usage

### Compile/Run:
```bash
javac src/*.java -d bin
java -cp bin Client 3 1

