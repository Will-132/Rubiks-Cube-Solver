import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Main program = new Main();
        program.combinedOptionsRun();
    }

    void programSolvesCube(RubiksCube cube, CubeSolver solver) // Computer solves the (already scrambled) cube
    {
        long start = System.nanoTime();
        solver.solve();
        long end = System.nanoTime();
        System.out.println("\nEnd Result:");
        cube.printCube();
        if (cube.isCubeSolved())
            System.out.printf("Solved in %.6f seconds%n", (end - start) / 1000000000.0);
        else
            System.out.println("Solver failed to reach a solved state.");
        cube.printMoveHistory();
    }

    void youSolveCube(RubiksCube cube, CubeInspector inspector) // User solves interactively until solved or they quit
    {
        long start = System.nanoTime(); // timer starts once, before their first move
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!cube.isCubeSolved() && !input.equalsIgnoreCase("Exit"))
        {
            System.out.println("\nIf you want a visualization of where the pieces are: Type \"Scan\"");
            System.out.println("Enter your moves (space separated), or type \"Exit\" to stop:");
            input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Exit"))
                break;
            else if (input.equalsIgnoreCase("Scan"))
            {
                inspector.reportCubeState();
                continue;
            }
            cube.apply(input.split(" "));
            System.out.println();
            cube.printCube();
        }
        long end = System.nanoTime();
        double seconds = (end - start) / 1000000000.0;
        if (cube.isCubeSolved())
        {
            System.out.println("Congrats on solving the cube by yourself!");
            System.out.printf("Solved in %.6f seconds%n", seconds);
        }
        else
            System.out.println("\nSession ended before solving.");
        cube.printMoveHistory();
    }

    void combinedOptionsRun() // Scramble, then choose: computer solves or you solve
    // Only have to call this get full procedure
    {
        RubiksCube myCube = new RubiksCube();
        CubeInspector inspector = new CubeInspector(myCube);
        CubeSolver solver = new CubeSolver(myCube, inspector);

        String scramble = myCube.scrambleTheCube();
        if (scramble == null)
            return;
        else
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want the program to solve the cube for you? (Type: \"Yes\" or \"No\")");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Yes"))
                programSolvesCube(myCube, solver);
            else if (input.equalsIgnoreCase("No"))
                youSolveCube(myCube, inspector);
            else
                System.out.println("Run ended: Next time, please type either \"Yes\" or \"No\".");
        }
    }
}

