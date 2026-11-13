import java.util.*;
import java.util.Scanner;

public class RubiksCube
{
    static final RubiksCube SOLVED_CUBE = new RubiksCube();
    // Outside a class and static so you can access it easily to compare

    //Make the 2d array to create the side colors of the cube
    char[][] frontFace =
    {
        {'b','b','b'},
        {'b','b','b'},
        {'b','b','b'}
    };
    char[][] rightFace =
    {
        {'r','r','r'},
        {'r','r','r'},
        {'r','r','r'}
    };
    char[][] backFace =
    {
        {'g','g','g'},
        {'g','g','g'},
        {'g','g','g'}
    };
    char[][] leftFace =
    {
        {'o','o','o'},
        {'o','o','o'},
        {'o','o','o'}
    };
    char[][] topFace =
    {
        {'y','y','y'},
        {'y','y','y'},
        {'y','y','y'}
    };
    char[][] bottomFace =
    {
        {'w','w','w'},
        {'w','w','w'},
        {'w','w','w'}
    };

    char[][] copyState(char[][] face) // A set up to copy the state of each side, used in later methods
    {
        char[][] copy = new char[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                copy[i][j] = face[i][j];
        return copy;
    }

    char[][][] saveState() // To update the cube after moves
    {
        char[][][] state = new char[6][][];
        state[0] = copyState(frontFace);
        state[1] = copyState(rightFace);
        state[2] = copyState(backFace);
        state[3] = copyState(leftFace);
        state[4] = copyState(topFace);
        state[5] = copyState(bottomFace);
        return state;
    }

    //Can't use .equals as it is a string class or directly use == as that is based on referenced memory
    boolean checkEquality(RubiksCube other) // Compare to the other since the method implicitly uses the first argument
    {
        char[][][] a = saveState();
        char[][][] b = other.saveState();
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 3; j++)
                for (int k = 0; k < 3; k++)
                    if (a[i][j][k] != b[i][j][k])
                        return false; // To make sure it goes through all the chars and all are not the same
        return true;
    }

    void printCube()
    {
        //Create the outline of the cube
        /*Tried to simply repeat but faced difficulty as
        the println would print a new line each time when
        I want the middle rows to be next to each other to
        better show the demensions of a cube*/
        for (int i=0; i<3; i++)
        {
            System.out.print("      "); //To replicate the dimensions and have it centered
            for (int j=0; j<topFace[i].length; j++)
                System.out.print(topFace[i][j]+" ");
            System.out.println();
        }
        /* I have to go by individual lines to show how
        the cube looks rather than print than the enitre
        3x3 side at once. Do this by combining into for loops
        I can make some assumptions as all sides are same size*/
        for (int i=0; i<3; i++)
        {
            for (int j=0; j<leftFace[i].length; j++)
                System.out.print(leftFace[i][j]+" ");
            for (int k=0; k<frontFace[i].length; k++)
                System.out.print(frontFace[i][k]+ " ");
            for (int l=0; l<rightFace[i].length; l++)
                System.out.print(rightFace[i][l]+" ");
            for (int m=0; m<backFace[i].length; m++)
                System.out.print(backFace[i][m]+" ");
            System.out.println();
        }
        /*Create the bottom part the same way we
        did the top to format the cube */
        for (int i=0; i<3; i++)
        {
            System.out.print("      ");
            for (int j=0; j<bottomFace[i].length; j++)
                System.out.print(bottomFace[i][j]+" ");
            System.out.println();
        }
        System.out.println();
    }

    //Create method for turning each side
    void turnU()
    {
        //Start with the side of the face it involves
        char tempCorner = topFace[0][2];
        char tempEdge = topFace[0][1];
        // Allowed to have the same name as they have different parameters
        //corners
        topFace[0][2] = topFace[0][0];
        topFace[0][0] = topFace[2][0];
        topFace[2][0] = topFace[2][2];
        topFace[2][2] = tempCorner;
        //edges
        topFace[0][1] = topFace[1][0];
        topFace[1][0] = topFace[2][1];
        topFace[2][1] = topFace[1][2];
        topFace[1][2] = tempEdge;

        //the middle color topFace[1][1] stays the same
        //Now do the other affected sides
        /* .clone() is required here: without it we'd share one array object
        between two faces, corrupting the cube when later moves write to it.
        So, it will only be needed for turning the top and bottom rows (U and D) */
        char[] tempFrontRow = frontFace[0].clone();
        frontFace[0] = rightFace[0].clone();
        rightFace[0] = backFace[0].clone();
        backFace[0] = leftFace[0].clone();
        leftFace[0] = tempFrontRow;
    }

    //Now replicate for U2 and U'
    void turnU2()
    {
        for (int i=0;i<2;i++)
        {
            turnU();
        }
    }

    void turnUPrime()
    {
        for (int i=0;i<3;i++)
        {
            turnU();
        }
    }

    void turnD()
    {
        //Can repeat names in temp variables as they are only defined within this method
        char tempCorner = bottomFace[0][2];
        char tempEdge = bottomFace[0][1];
        //corners
        bottomFace[0][2] = bottomFace[0][0];
        bottomFace[0][0] = bottomFace[2][0];
        bottomFace[2][0] = bottomFace[2][2];
        bottomFace[2][2] = tempCorner;
        //edges
        bottomFace[0][1] = bottomFace[1][0];
        bottomFace[1][0] = bottomFace[2][1];
        bottomFace[2][1] = bottomFace[1][2];
        bottomFace[1][2] = tempEdge;

        //Now do the other affected sides
        char[] tempBottomRow = frontFace[2].clone();
        frontFace[2] = leftFace[2].clone();
        leftFace[2] = backFace[2].clone();
        backFace[2] = rightFace[2].clone();
        rightFace[2] = tempBottomRow;
    }

    void turnD2()
    {
        for (int i=0;i<2;i++)
        {
            turnD();
        }
    }

    void turnDPrime()
    {
        for (int i=0;i<3;i++)
        {
            turnD();
        }
    }

    void turnR()
    {
        char tempCorner = rightFace[0][2];
        char tempEdge = rightFace[0][1];
        //corners
        rightFace[0][2] = rightFace[0][0];
        rightFace[0][0] = rightFace[2][0];
        rightFace[2][0] = rightFace[2][2];
        rightFace[2][2] = tempCorner;
        //edges
        rightFace[0][1] = rightFace[1][0];
        rightFace[1][0] = rightFace[2][1];
        rightFace[2][1] = rightFace[1][2];
        rightFace[1][2] = tempEdge;
        //Now do the other affected sides
        // Have to do something different here to get only the right column affected
        for (int i=0; i<3;i++)
        {
            char tempRightColumn = frontFace[i][2];
            frontFace[i][2] = bottomFace[i][2];
            bottomFace[i][2] = backFace[2-i][0]; //To account for the perspective
            backFace[2-i][0] = topFace[i][2];
            topFace[i][2] = tempRightColumn;
        }
    }

    void turnR2()
    {
        for (int j=0;j<2;j++)
        {
            turnR();
        }
    }

    void turnRPrime()
    {
        for (int j = 0;j<3;j++)
        {
            turnR();
        }
    }

    void turnL()
    {
        char tempCorner = leftFace[0][0];
        char tempEdge = leftFace[0][1];
        //corners
        leftFace[0][0] = leftFace[2][0];
        leftFace[2][0] = leftFace[2][2];
        leftFace[2][2] = leftFace[0][2];
        leftFace[0][2] = tempCorner;
        //edges
        leftFace[0][1] = leftFace[1][0];
        leftFace[1][0] = leftFace[2][1];
        leftFace[2][1] = leftFace[1][2];
        leftFace[1][2] = tempEdge;
        //Loop to only get the certain column
        for (int i = 0; i<3;i++)
        {
            char tempLeftColumn = frontFace[i][0];
            frontFace[i][0] = topFace[i][0];
            topFace[i][0] = backFace[2-i][2];
            backFace[2-i][2] = bottomFace[i][0];
            bottomFace[i][0] = tempLeftColumn;
        }
    }

    //Copy that format for L' and L2
    void turnL2()
    {
        for (int j=0;j<2;j++)
        {
            turnL();
        }
    }

    void turnLPrime()
    {
        for (int j=0;j<3;j++)
        {
            turnL();
        }
    }

    void turnF()
    {
        char tempCorner = frontFace[0][2];
        char tempEdge = frontFace[0][1];
        //corners
        frontFace[0][2] = frontFace[0][0];
        frontFace[0][0] = frontFace[2][0];
        frontFace[2][0] = frontFace[2][2];
        frontFace[2][2] = tempCorner;
        //edges
        frontFace[0][1] = frontFace[1][0];
        frontFace[1][0] = frontFace[2][1];
        frontFace[2][1] = frontFace[1][2];
        frontFace[1][2] = tempEdge;
        /*Other affected sides
        Will be complicated due to changes with face
        ex. bottom row on top face but left row on other face*/
        for (int i = 0; i<3; i++)
        {
            char tempFrontColumn = topFace[2][i];
            topFace[2][i] = leftFace[2-i][2];
            leftFace[2-i][2] = bottomFace[0][2-i];
            bottomFace[0][2-i] = rightFace[i][0];
            rightFace[i][0] = tempFrontColumn;
        }
    }

    //Repeat for the rest of F2 and F'
    void turnF2()
    {
        for (int j = 0; j<2; j++)
        {
            turnF();
        }
    }

    void turnFPrime()
    {
        for (int j = 0; j<3 ; j++)
        {
            turnF();
        }
    }

    void turnB()
    {
        char tempCorner = backFace[0][2];
        char tempEdge = backFace[0][1];
        //corners
        backFace[0][2] = backFace[0][0];
        backFace[0][0] = backFace[2][0];
        backFace[2][0] = backFace[2][2];
        backFace[2][2] = tempCorner;
        //edges
        backFace[0][1] = backFace[1][0];
        backFace[1][0] = backFace[2][1];
        backFace[2][1] = backFace[1][2];
        backFace[1][2] = tempEdge;
        //Other affected sides
        for (int i = 0; i<3; i++)
        {
            char tempBackColumn = topFace[0][i];
            topFace[0][i] = rightFace[i][2];
            rightFace[i][2] = bottomFace[2][2-i];
            bottomFace[2][2-i] = leftFace[2-i][0];
            leftFace[2-i][0] = tempBackColumn;
        }
    }

    //Repeat for B2 and B'
    void turnB2()
    {
        for (int j = 0; j<2; j++)
        {
            turnB();
        }
    }

    void turnBPrime()
    {
        for (int j = 0; j<3 ; j++)
        {
            turnB();
        }
    }

    String randomScramble()// Creating the random scramble with the math class
    {
        int x;
        String scramble="";
        char prevFace = ' ';
        String[] moveList = { "U","U'","U2","D","D'","D2",
                              "F","F'","F2","B","B'","B2",
                              "L","L'","L2","R","R'","R2"};
        for (int i=0; i<20; i++)
        {
            x = (int)(Math.random()*18); // To create a truly random scramble
            while (moveList[x].charAt(0) == prevFace) // To follow WCA scrambling standards, no repeat moves
                x = (int)(Math.random()*18);
            scramble += moveList[x] + " ";
            prevFace = moveList[x].charAt(0);
        }
        return scramble;
    }

    //Create a way for the viewer to scramble the cube on their own
    String[] getScramble()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give the scramble you want to make for the Rubiks Cube");
        System.out.println("Separate each move with a space");
        System.out.println("If you want a random scramble, type \"Random\"");
        System.out.println("Ex: \"U' F R2 D\"");
        String input = scanner.nextLine(); //Reads the whole line
        System.out.println();
        if (input.trim().equalsIgnoreCase("Random"))
        {
            String scrambleText = randomScramble();
            System.out.println("Scramble: " + scrambleText);
            return scrambleText.trim().split(" ");
        }
        else
            return input.trim().split(" "); // Seperates the input into individual moves
    }

    static ArrayList<String> moveHistory = new ArrayList<>();

    void apply(String[] moves)
    {
        //Sort through the string
        for (String move: moves)
        {
            move = move.trim().toUpperCase(); //To allow for accidental errors with capitalization
            switch (move) // Change the written moves into our methods
            {
                case "U": turnU(); moveHistory.add("U"); break;
                case "U2": turnU2(); moveHistory.add("U2"); break;
                case "U'": turnUPrime(); moveHistory.add("U'"); break;
                case "D": turnD(); moveHistory.add("D"); break;
                case "D2": turnD2(); moveHistory.add("D2"); break;
                case "D'": turnDPrime(); moveHistory.add("D'"); break;
                case "F": turnF(); moveHistory.add("F"); break;
                case "F2": turnF2(); moveHistory.add("F2"); break;
                case "F'": turnFPrime(); moveHistory.add("F'"); break;
                case "B": turnB(); moveHistory.add("B"); break;
                case "B2": turnB2(); moveHistory.add("B2"); break;
                case "B'": turnBPrime(); moveHistory.add("B'"); break;
                case "L": turnL(); moveHistory.add("L"); break;
                case "L2": turnL2(); moveHistory.add("L2"); break;
                case "L'": turnLPrime(); moveHistory.add("L'"); break;
                case "R": turnR(); moveHistory.add("R"); break;
                case "R2": turnR2(); moveHistory.add("R2"); break;
                case "R'": turnRPrime(); moveHistory.add("R'"); break;
                // In case of errors within compilation,
                // give an error rather than let it compile wrong
                default:
                    System.out.println("Invalid move: \""+move+"\"\n"); break;
            }
        }
    }

    void apply(String moves) // This helps if you dont want to input an array
    {
        apply(moves.split(" "));
    }

    int countValidMoves(String[] moves) // Counts number of moves
    {
        int count = 0;
        for (String move: moves)
            if (!move.isEmpty()) //Accounts for extra spaces
                count++;
        return count;
    }

    void printMoveHistory()
    {
        System.out.println("Total Moves: " + moveHistory.size());
        System.out.println("Move History: " + String.join(" ", moveHistory));
    }

    void reportScramble(String[] moves) // Prints the summary line and the cube
    {
        int moveCount = countValidMoves(moves);
        if (moveCount > 1)
            System.out.println("You made " + moveCount + " moves in your scramble\n");
        else if (moveCount == 1)
            System.out.println("You made " + moveCount + " move in your scramble\n");
        else
            System.out.println("No valid moves were applied.\n");
        System.out.println("Scrambled Cube State: ");
        printCube();
    }

    boolean hasInvalidMoves(String[] moves) // To account for wrong moves so compiling doesn't go any further
    {
        for (String m: moves)
        {
            String move = m.toUpperCase();
            if (!(move.equals("U") || move.equals("U2") || move.equals("U'")
               || move.equals("D") || move.equals("D2") || move.equals("D'")
               || move.equals("F") || move.equals("F2") || move.equals("F'")
               || move.equals("B") || move.equals("B2") || move.equals("B'")
               || move.equals("L") || move.equals("L2") || move.equals("L'")
               || move.equals("R") || move.equals("R2") || move.equals("R'")))
                return true;
        }
        return false;
    }

    String scrambleTheCube() // Combines multiple aspects as I used other parts, more simplicity
    {
        moveHistory.clear();
        String[] moves = getScramble();
        if (hasInvalidMoves(moves))
        {
            System.out.println("Invalid scramble check your moves and try again.");
            return null; // caller must check for this
        }
        apply(moves);
        reportScramble(moves);
        moveHistory.clear();
        return String.join(" ", moves);
    }

    public boolean isCubeSolved()
    {
        return checkEquality(SOLVED_CUBE);
    }
}

