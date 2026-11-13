public class CubeSolver
{
    RubiksCube cube;
    CubeInspector inspector;

    public CubeSolver(RubiksCube cube, CubeInspector inspector)
    {
        this.cube = cube;
        this.inspector = inspector;
    }

    //Now onto implementing algorithms to solve the cube
    void solveWhiteBlueEdge()
    {
        int e = inspector.findPiece('w', 'b'); // Finds piece
        char[] p = inspector.getEdgeStickers(e); // Gives sticker colors of the edge
        if (e == 0 && p[0] == 'w')
            return;
        else if (e == 0 && p[0] == 'b')
            cube.apply("F L D");
        else if (e == 1 && p[0] == 'w')
            cube.apply("D");
        else if (e == 1 && p[0] == 'b')
            cube.apply("R F");
        else if (e == 2 && p[0] == 'w')
            cube.apply("D2");
        else if (e == 2 && p[0] == 'b')
            cube.apply("B R D'");
        else if (e == 3 && p[0] == 'w')
            cube.apply("D'");
        else if (e == 3 && p[0] == 'b')
            cube.apply("L' F'");
        else if (e == 4 && p[0] == 'w')
            cube.apply("R' D'");
        else if (e == 4 && p[0] == 'b')
            cube.apply("F");
        else if (e == 5 && p[0] == 'w')
            cube.apply("R D'");
        else if (e == 5 && p[0] == 'b')
            cube.apply("R2 F");
        else if (e == 6 && p[0] == 'w')
            cube.apply("L' D");
        else if (e == 6 && p[0] == 'b')
            cube.apply("L2 F'");
        else if (e == 7 && p[0] == 'w')
            cube.apply("L D");
        else if (e == 7 && p[0] == 'b')
            cube.apply("F'");
        else if (e == 8 && p[0] == 'w')
            cube.apply("F2");
        else if (e == 8 && p[0] == 'b')
            cube.apply("U' R' F");
        else if (e == 9 && p[0] == 'w')
            cube.apply("R2 D'");
        else if (e == 9 && p[0] == 'b')
            cube.apply("R' F");
        else if (e == 10 && p[0] == 'w')
            cube.apply("U2 F2");
        else if (e == 10 && p[0] == 'b')
            cube.apply("U R' F");
        else if (e == 11 && p[0] == 'w')
            cube.apply("L2 D");
        else if (e == 11 && p[0] == 'b')
            cube.apply("L F'");
        else
            return;
    }

    void solveWhiteRedEdge()
    {
        int e = inspector.findPiece('w', 'r');
        char[] p = inspector.getEdgeStickers(e);
        // I don't have if e=0 since that is already solved when I run the previous method: solveWhiteBlueEdge()
        if (e == 1 && p[0] == 'w')
            return;
        else if (e == 1 && p[0] == 'r')
            cube.apply("R D' F D");
        else if (e == 2 && p[0] == 'w')
            cube.apply("B2 U R2");
        else if (e == 2 && p[0] == 'r')
            cube.apply("B R");
        else if (e == 3 && p[0] == 'w')
            cube.apply("L2 U2 R2");
        else if (e == 3 && p[0] == 'r')
            cube.apply("L' D' F' D");
        else if (e == 4 && p[0] == 'w')
            cube.apply("R'");
        else if (e == 4 && p[0] == 'r')
            cube.apply("D' F D");
        else if (e == 5 && p[0] == 'w')
            cube.apply("R");
        else if (e == 5 && p[0] == 'r')
            cube.apply("D B' D'");
        else if (e == 6 && p[0] == 'w')
            cube.apply("B2 R");
        else if (e == 6 && p[0] == 'r')
            cube.apply("D B D'");
        else if (e == 7 && p[0] == 'w')
            cube.apply("F2 R' F2");
        else if (e == 7 && p[0] == 'r')
            cube.apply("D' F' D");
        else if (e == 8 && p[0] == 'w')
            cube.apply("U' R2");
        else if (e == 8 && p[0] == 'r')
            cube.apply("F R' F'");
        else if (e == 9 && p[0] == 'w')
            cube.apply("R2");
        else if (e == 9 && p[0] == 'r')
            cube.apply("U F R' F'");
        else if (e == 10 && p[0] == 'w')
            cube.apply("U R2");
        else if (e == 10 && p[0] == 'r')
            cube.apply("B' R B");
        else if (e == 11 && p[0] == 'w')
            cube.apply("U2 R2");
        else if (e == 11 && p[0] == 'r')
            cube.apply("U' F R' F'");
        else
            return;
    }

    void solveWhiteGreenEdge()
    {
        int e = inspector.findPiece('w', 'g');
        char[] p = inspector.getEdgeStickers(e);
        if (e == 2 && p[0] == 'w') // Can get rid of e=0 and e=1 as they can be deduced to not contain the edge
            return;
        else if (e == 2 && p[0] == 'g')
            cube.apply("B2 U R B' R'");
        else if (e == 3 && p[0] == 'w')
            cube.apply("L2 U B2");
        else if (e == 3 && p[0] == 'g')
            cube.apply("L B");
        else if (e == 4 && p[0] == 'w')
            cube.apply("D' R' D");
        else if (e == 4 && p[0] == 'g')
            cube.apply("R2 B' R2");
        else if (e == 5 && p[0] == 'w')
            cube.apply("D' R D");
        else if (e == 5 && p[0] == 'g')
            cube.apply("B'");
        else if (e == 6 && p[0] == 'w')
            cube.apply("D L' D'");
        else if (e == 6 && p[0] == 'g')
            cube.apply("B");
        else if (e == 7 && p[0] == 'w')
            cube.apply("D L D'");
        else if (e == 7 && p[0] == 'g')
            cube.apply("L2 B");
        else if (e == 8 && p[0] == 'w')
            cube.apply("U2 B2");
        else if (e == 8 && p[0] == 'g')
            cube.apply("U' R B' R'");
        else if (e == 9 && p[0] == 'w')
            cube.apply("U' B2");
        else if (e == 9 && p[0] == 'g')
            cube.apply("R B' R'");
        else if (e == 10 && p[0] == 'w')
            cube.apply("B2");
        else if (e == 10 && p[0] == 'g')
            cube.apply("U R B' R'");
        else if (e == 11 && p[0] == 'w')
            cube.apply("U B2");
        else if (e == 11 && p[0] == 'g')
            cube.apply("L' B");
        else
            return;
    }

    void solveWhiteOrangeEdge() // Same steps as previous, last cross edge piece
    {
        int e = inspector.findPiece('w', 'o');
        char[] p = inspector.getEdgeStickers(e);
        if (e == 3 && p[0] == 'w')
            return;
        else if (e == 3 && p[0] == 'o')
            cube.apply("L D' B D");
        else if (e == 4 && p[0] == 'w')
            cube.apply("D2 R' D2");
        else if (e == 4 && p[0] == 'o')
            cube.apply("D F D'");
        else if (e == 5 && p[0] == 'w')
            cube.apply("D2 R D2");
        else if (e == 5 && p[0] == 'o')
            cube.apply("D' B' D");
        else if (e == 6 && p[0] == 'w')
            cube.apply("L'");
        else if (e == 6 && p[0] == 'o')
            cube.apply("D' B D");
        else if (e == 7 && p[0] == 'w')
            cube.apply("L");
        else if (e == 7 && p[0] == 'o')
            cube.apply("D F' D'");
        else if (e == 8 && p[0] == 'w')
            cube.apply("U L2");
        else if (e == 8 && p[0] == 'o')
            cube.apply("F' L F");
        else if (e == 9 && p[0] == 'w')
            cube.apply("U2 L2");
        else if (e == 9 && p[0] == 'o')
            cube.apply("U F' L F");
        else if (e == 10 && p[0] == 'w')
            cube.apply("U' L2");
        else if (e == 10 && p[0] == 'o')
            cube.apply("B L' B'");
        else if (e == 11 && p[0] == 'w')
            cube.apply("L2");
        else if (e == 11 && p[0] == 'o')
            cube.apply("U' F' L F");
        else
            return;
    }

    public void solveWhiteCross() // Combine previous stuff
    {
        solveWhiteBlueEdge();
        solveWhiteRedEdge();
        solveWhiteGreenEdge();
        solveWhiteOrangeEdge();
    }

    void solveWhiteBlueRedCorner()
    {
        int e = inspector.findPiece('w', 'b', 'r'); // Is 3 now since we are dealing with corners
        char[] p = inspector.getCornerStickers(e);
        if (e == 0 && p[0] == 'w')
            return;
        else if (e == 0 && p[1] == 'w')
            cube.apply("R U' R' U R U' R'");
        else if (e == 0 && p[2] == 'w') // Accounts for all 3 states the white sticker could be
            cube.apply("R U R' U' R U R'");
        else if (e == 1 && p[0] == 'w')
            cube.apply("R' U' R F' U2 F");
        else if (e == 1 && p[1] == 'w')
            cube.apply("R' U R U R U R'");
        else if (e == 1 && p[2] == 'w')
            cube.apply("R' U' R2 U2 R'");
        else if (e == 2 && p[0] == 'w')
            cube.apply("L R U2 R' L'");
        else if (e == 2 && p[1] == 'w')
            cube.apply("L U' L' R U2 R'");
        else if (e == 2 && p[2] == 'w')
            cube.apply("L U L' F' U F");
        else if (e == 3 && p[0] == 'w')
            cube.apply("F R U' R2 F' R");
        else if (e == 3 && p[1] == 'w')
            cube.apply("L' U L U' R U R'");
        else if (e == 3 && p[2] == 'w')
            cube.apply("L' R U' R' L");
        else if (e == 4 && p[0] == 'w')
            cube.apply("R U' R' F' U2 F");
        else if (e == 4 && p[1] == 'w')
            cube.apply("U R U' R'");
        else if (e == 4 && p[2] == 'w')
            cube.apply("R U R'");
        else if (e == 5 && p[0] == 'w')
            cube.apply("U' R U R' U R U' R'");
        else if (e == 5 && p[1] == 'w')
            cube.apply("U R U R'");
        else if (e == 5 && p[2] == 'w')
            cube.apply("U2 R U' R'");
        else if (e == 6 && p[0] == 'w')
            cube.apply("R U R' U R U' R'");
        else if (e == 6 && p[1] == 'w')
            cube.apply("R U2 R'");
        else if (e == 6 && p[2] == 'w')
            cube.apply("F' U2 F");
        else if (e == 7 && p[0] == 'w')
            cube.apply("R U2 R' U R U' R'");
        else if (e == 7 && p[1] == 'w')
            cube.apply("U' R U R'");
        else if (e == 7 && p[2] == 'w')
            cube.apply("R U' R'");
        else
            return;
    }

    void solveWhiteGreenRedCorner()
    {
        int e = inspector.findPiece('w', 'g', 'r');
        char[] p = inspector.getCornerStickers(e);
        if (e == 1 && p[0] == 'w')
            return;
        else if (e == 1 && p[1] == 'w')
            cube.apply("R' U R U' R' U R");
        else if (e == 1 && p[2] == 'w')
            cube.apply("R' U' R U R' U' R");
        else if (e == 2 && p[0] == 'w')
            cube.apply("L U L' R' U' R");
        else if (e == 2 && p[1] == 'w')
            cube.apply("L U' L' U R' U' R");
        else if (e == 2 && p[2] == 'w')
            cube.apply("L R' U R L'");
        else if (e == 3 && p[0] == 'w')
            cube.apply("L' R' U2 R L");
        else if (e == 3 && p[1] == 'w')
            cube.apply("L' U L R' U2 R");
        else if (e == 3 && p[2] == 'w')
            cube.apply("L' U' L U' R' U' R");
        else if (e == 4 && p[0] == 'w')
            cube.apply("U R' U' R U' R' U R");
        else if (e == 4 && p[1] == 'w')
            cube.apply("U' R' U' R");
        else if (e == 4 && p[2] == 'w')
            cube.apply("U2 R' U R");
        else if (e == 5 && p[0] == 'w')
            cube.apply("R' U2 R U R' U' R");
        else if (e == 5 && p[1] == 'w')
            cube.apply("U' R' U R");
        else if (e == 5 && p[2] == 'w')
            cube.apply("R' U' R");
        else if (e == 6 && p[0] == 'w')
            cube.apply("R' U2 R U' R' U R");
        else if (e == 6 && p[1] == 'w')
            cube.apply("U R' U' R");
        else if (e == 6 && p[2] == 'w')
            cube.apply("R' U R");
        else if (e == 7 && p[0] == 'w')
            cube.apply("R' U' R U' R' U R");
        else if (e == 7 && p[1] == 'w')
            cube.apply("R' U2 R");
        else if (e == 7 && p[2] == 'w')
            cube.apply("U2 R' U' R");
        else
            return;
    }

    void solveWhiteGreenOrangeCorner()
    {
        int e = inspector.findPiece('w', 'g', 'o');
        char[] p = inspector.getCornerStickers(e);
        if (e == 2 && p[0] == 'w')
            return;
        else if (e == 2 && p[1] == 'w')
            cube.apply("L U' L' U L U' L'");
        else if (e == 2 && p[2] == 'w')
            cube.apply("L U L' U' L U L'");
        else if (e == 3 && p[0] == 'w')
            cube.apply("L' U L U2 L U' L'");
        else if (e == 3 && p[1] == 'w')
            cube.apply("L' U L U L U L'");
        else if (e == 3 && p[2] == 'w')
            cube.apply("L' U' L2 U2 L'");
        else if (e == 4 && p[0] == 'w')
            cube.apply("L U L' U L U' L'");
        else if (e == 4 && p[1] == 'w')
            cube.apply("L U2 L'");
        else if (e == 4 && p[2] == 'w')
            cube.apply("U2 L U L'");
        else if (e == 5 && p[0] == 'w')
            cube.apply("L U2 L' U L U' L'");
        else if (e == 5 && p[1] == 'w')
            cube.apply("U' L U L'");
        else if (e == 5 && p[2] == 'w')
            cube.apply("L U' L'");
        else if (e == 6 && p[0] == 'w')
            cube.apply("L U2 L' U' L U L'");
        else if (e == 6 && p[1] == 'w')
            cube.apply("U L U' L'");
        else if (e == 6 && p[2] == 'w')
            cube.apply("L U L'");
        else if (e == 7 && p[0] == 'w')
            cube.apply("L' U L2 U' L'");
        else if (e == 7 && p[1] == 'w')
            cube.apply("U L U L'");
        else if (e == 7 && p[2] == 'w')
            cube.apply("U2 L U' L'");
        else
            return;
    }

    void solveWhiteBlueOrangeCorner()
    {
        int e = inspector.findPiece('w', 'b', 'o');
        char[] p = inspector.getCornerStickers(e);
        if (e == 3 && p[0] == 'w')
            return;
        else if (e == 3 && p[1] == 'w')
            cube.apply("L' U L U' L' U L");
        else if (e == 3 && p[2] == 'w')
            cube.apply("L' U' L U L' U' L");
        else if (e == 4 && p[0] == 'w')
            cube.apply("L' U2 L U' L' U L");
        else if (e == 4 && p[1] == 'w')
            cube.apply("U L' U' L");
        else if (e == 4 && p[2] == 'w')
            cube.apply("L' U L");
        else if (e == 5 && p[0] == 'w')
            cube.apply("L' U' L U' L' U L");
        else if (e == 5 && p[1] == 'w')
            cube.apply("L' U2 L");
        else if (e == 5 && p[2] == 'w')
            cube.apply("F U2 F'");
        else if (e == 6 && p[0] == 'w')
            cube.apply("U L' U' L U' L' U L");
        else if (e == 6 && p[1] == 'w')
            cube.apply("U' L' U' L");
        else if (e == 6 && p[2] == 'w')
            cube.apply("U2 L' U L");
        else if (e == 7 && p[0] == 'w')
            cube.apply("L' U2 L U L' U' L");
        else if (e == 7 && p[1] == 'w')
            cube.apply("U' L' U L");
        else if (e == 7 && p[2] == 'w')
            cube.apply("L' U' L");
        else
            return;
    }

    public void solveFirstLayer()
    // Combines the previous methods for easier accesibility
    // Follows key OOP concept of Abstraction
    {
        solveWhiteBlueRedCorner();
        solveWhiteGreenRedCorner();
        solveWhiteGreenOrangeCorner();
        solveWhiteBlueOrangeCorner();
    }

    void solveBlueRedEdge()
    {
        int e = inspector.findPiece('b', 'r');
        char[] p = inspector.getEdgeStickers(e);
        if (e == 4 && p[0] == 'b')
            return;
        else if (e == 4 && p[0] == 'r')
            cube.apply("F' U F U' R U2 R' U2 R U' R'");
        // These algorithms are longer as they need to account for the whole bottom layer already being solved
        else if (e == 5 && p[0] == 'b')
            cube.apply("R2 U2 R2 U2 R2");
        else if (e == 5 && p[0] == 'r')
            cube.apply("D R' U' R D' F' U2 F");
        else if (e == 6 && p[0] == 'b')
            cube.apply("D2 L U L' D2 U2 R U' R'");
        else if (e == 6 && p[0] == 'r')
            cube.apply("L U L' D2 R U2 R' U F' U' F D2");
        else if (e == 7 && p[0] == 'b')
            cube.apply("F2 U2 F2 U2 F2");
        else if (e == 7 && p[0] == 'r')
            cube.apply("D' L' U' L U' D F' U F");
        else if (e == 8 && p[0] == 'b')
            cube.apply("U2 F' R U R' U' R' F R");
        else if (e == 8 && p[0] == 'r')
            cube.apply("U R U' R' F R' F' R");
        else if (e == 9 && p[0] == 'b')
            cube.apply("U' F' R U R' U' R' F R");
        else if (e == 9 && p[0] == 'r')
            cube.apply("U2 R U' R' F R' F' R");
        else if (e == 10 && p[0] == 'b')
            cube.apply("F' R U R' U' R' F R");
        else if (e == 10 && p[0] == 'r')
            cube.apply("U' R U' R' F R' F' R");
        else if (e == 11 && p[0] == 'b')
            cube.apply("U F' R U R' U' R' F R");
        else if (e == 11 && p[0] == 'r')
            cube.apply("R U' R' F R' F' R");
        else
            return;
    }

    void solveGreenRedEdge()
    // Not only account for bottom layer, but also solved edge pieces
    {
        int e = inspector.findPiece('g', 'r');
        char[] p = inspector.getEdgeStickers(e);
        if (e == 5 && p[0] == 'g')
            return;
        else if (e == 5 && p[0] == 'r')
            cube.apply("B U' B' U R' U2 R U2 R' U R");
        else if (e == 6 && p[0] == 'g')
            cube.apply("B2 U2 B2 U2 B2");
        else if (e == 6 && p[0] == 'r')
            cube.apply("D L U L' D' U B U' B'");
        else if (e == 7 && p[0] == 'g')
            cube.apply("D2 L' U L D2 U2 R' U' R");
        else if (e == 7 && p[0] == 'r')
            cube.apply("L' U' L D2 R' U2 R U' B U B' D2");
        else if (e == 8 && p[0] == 'g')
            cube.apply("U' R' U R U R' U R U' R' U' R");
        else if (e == 8 && p[0] == 'r')
            cube.apply("U R' U R B' R B R'");
        else if (e == 9 && p[0] == 'g')
            cube.apply("R' U R U R' U R U' R' U' R");
        else if (e == 9 && p[0] == 'r')
            cube.apply("U2 R' U R B' R B R'");
        else if (e == 10 && p[0] == 'g')
            cube.apply("U R' U R U R' U R U' R' U' R");
        else if (e == 10 && p[0] == 'r')
            cube.apply("U' R' U R B' R B R'");
        else if (e == 11 && p[0] == 'g')
            cube.apply("R' U R2 U2 R2 U' R2 U' R'");
        else if (e == 11 && p[0] == 'r')
            cube.apply("R' U R B' R B R'");
        else
            return;
    }

    void solveGreenOrangeEdge()
    {
        int e = inspector.findPiece('g', 'o');
        char[] p = inspector.getEdgeStickers(e);
        if (e == 6 && p[0] == 'g')
            return;
        else if (e == 6 && p[0] == 'o')
            cube.apply("B' U B U' L U2 L' U2 L U' L'");
        else if (e == 7 && p[0] == 'g')
            cube.apply("L2 U2 L2 U2 L2");
        else if (e == 7 && p[0] == 'o')
            cube.apply("D L' U' L D' B' U2 B");
        else if (e == 8 && p[0] == 'g')
            cube.apply("B' L U L' U' L' B L");
        else if (e == 8 && p[0] == 'o')
            cube.apply("U' L U' L' B L' B' L");
        else if (e == 9 && p[0] == 'g')
            cube.apply("U B' L U L' U' L' B L");
        else if (e == 9 && p[0] == 'o')
            cube.apply("L U' L' B L' B' L");
        else if (e == 10 && p[0] == 'g')
            cube.apply("U2 B' L U L' U' L' B L");
        else if (e == 10 && p[0] == 'o')
            cube.apply("U L U' L' B L' B' L");
        else if (e == 11 && p[0] == 'g')
            cube.apply("U' B' L U L' U' L' B L");
        else if (e == 11 && p[0] == 'o')
            cube.apply("U2 L U' L' B L' B' L");
        else
            return;
    }

    void solveBlueOrangeEdge()
    // Reused lots of previous aspects for edge slots, especially for when the edge is on the top face
    {
        int e = inspector.findPiece('b', 'o');
        char[] p = inspector.getEdgeStickers(e);
        if (e == 7 && p[0] == 'b')
            return;
        else if (e == 7 && p[0] == 'o')
            cube.apply("F U' F' U L' U2 L U2 L' U L");
        else if (e == 8 && p[0] == 'b')
            cube.apply("U2 F L' U' L U L F' L'");
        else if (e == 8 && p[0] == 'o')
            cube.apply("U' L' U L F' L F L'");
        else if (e == 9 && p[0] == 'b')
            cube.apply("U' F L' U' L U L F' L'");
        else if (e == 9 && p[0] == 'o')
            cube.apply("L' U L F' L F L'");
        else if (e == 10 && p[0] == 'b')
            cube.apply("F L' U' L U L F' L'");
        else if (e == 10 && p[0] == 'o')
            cube.apply("U L' U L F' L F L'");
        else if (e == 11 && p[0] == 'b')
            cube.apply("U F L' U' L U L F' L'");
        else if (e == 11 && p[0] == 'o')
            cube.apply("U2 L' U L F' L F L'");
        else
            return;
    }

    void solveF2LEdges()
    {
        solveBlueRedEdge();
        solveGreenRedEdge();
        solveGreenOrangeEdge();
        solveBlueOrangeEdge();
    }

    void solveF2L() // Combined to follow the beginners method
    {
        solveFirstLayer();
        solveF2LEdges();
    }

    void solveYellowCross() // Part of 2-step OLL in beginners method
    {
        if (cube.topFace[0][1] == 'y' && cube.topFace[1][0] == 'y' && cube.topFace[1][2] == 'y' && cube.topFace[2][1] == 'y')
            return;
        else if (cube.topFace[0][1] == 'y' && cube.topFace[1][0] == 'y' && cube.topFace[1][2] != 'y' && cube.topFace[2][1] != 'y')
            cube.apply("F U R U' R' F'");
        else if (cube.topFace[0][1] == 'y' && cube.topFace[1][0] != 'y' && cube.topFace[1][2] == 'y' && cube.topFace[2][1] != 'y')
            cube.apply("U' F U R U' R' F'");
        else if (cube.topFace[0][1] != 'y' && cube.topFace[1][0] != 'y' && cube.topFace[1][2] == 'y' && cube.topFace[2][1] == 'y')
            cube.apply("U2 F U R U' R' F'");
        else if (cube.topFace[0][1] != 'y' && cube.topFace[1][0] == 'y' && cube.topFace[1][2] != 'y' && cube.topFace[2][1] == 'y')
            cube.apply("U F U R U' R' F'");
        else if (cube.topFace[0][1] == 'y' && cube.topFace[1][0] != 'y' && cube.topFace[1][2] != 'y' && cube.topFace[2][1] == 'y')
            cube.apply("U F R U R' U' F'");
        else if (cube.topFace[0][1] != 'y' && cube.topFace[1][0] == 'y' && cube.topFace[1][2] == 'y' && cube.topFace[2][1] != 'y')
            cube.apply("F R U R' U' F'");
        else if (cube.topFace[0][1] != 'y' && cube.topFace[1][0] != 'y' && cube.topFace[1][2] != 'y' && cube.topFace[2][1] != 'y')
            cube.apply("R U2 R2 F R F' U2 R' F R F'");
        else
            return;
    }

    void solveOLLCorners() // Part of 2-step OLL in beginners method
    {
        // Much easier to program as normal OLL has 57 algorithms (215 possible permutations)
        if (cube.topFace == RubiksCube.SOLVED_CUBE.topFace) // Compares to the solved cube (Easier than sorting through each sticker)
            return;
        else if (cube.frontFace[0][0] == 'y' && cube.frontFace[0][2] == 'y' && cube.backFace[0][0] == 'y' && cube.backFace[0][2] == 'y')
            cube.apply("R U2 R' U' R U R' U' R U' R'");
        else if (cube.leftFace[0][0] == 'y' && cube.leftFace[0][2] == 'y' && cube.rightFace[0][0] == 'y' && cube.rightFace[0][2] == 'y')
            cube.apply("U R U2 R' U' R U R' U' R U' R'");
        else if (cube.topFace[0][0] == 'y' && cube.topFace[0][2] == 'y' && cube.frontFace[0][0] == 'y' && cube.frontFace[0][2] == 'y')
            cube.apply("U2 R2 D' R U2 R' D R U2 R");
        else if (cube.topFace[2][0] == 'y' && cube.topFace[2][2] == 'y' && cube.backFace[0][0] == 'y' && cube.backFace[0][2] == 'y')
            cube.apply("R2 D' R U2 R' D R U2 R");
        else if (cube.topFace[0][0] == 'y' && cube.topFace[2][0] == 'y' && cube.rightFace[0][0] == 'y' && cube.rightFace[0][2] == 'y')
            cube.apply("U' R2 D' R U2 R' D R U2 R");
        else if (cube.topFace[0][2] == 'y' && cube.topFace[2][2] == 'y' && cube.leftFace[0][0] == 'y' && cube.leftFace[0][2] == 'y')
            cube.apply("U R2 D' R U2 R' D R U2 R");
        else if (cube.leftFace[0][0] == 'y' && cube.leftFace[0][2] == 'y' && cube.frontFace[0][2] == 'y' && cube.backFace[0][0] == 'y')
            cube.apply("R U2 R2 U' R2 U' R2 U2 R");
        else if (cube.rightFace[0][0] == 'y' && cube.rightFace[0][2] == 'y' && cube.frontFace[0][0] == 'y' && cube.backFace[0][2] == 'y')
            cube.apply("U2 R U2 R2 U' R2 U' R2 U2 R");
        else if (cube.frontFace[0][0] == 'y' && cube.frontFace[0][2] == 'y' && cube.rightFace[0][2] == 'y' && cube.leftFace[0][0] == 'y')
            cube.apply("U R U2 R2 U' R2 U' R2 U2 R");
        else if (cube.backFace[0][0] == 'y' && cube.backFace[0][2] == 'y' && cube.leftFace[0][2] == 'y' && cube.rightFace[0][0] == 'y')
            cube.apply("U' R U2 R2 U' R2 U' R2 U2 R");
        else if (cube.topFace[0][0] == 'y' && cube.topFace[2][0] == 'y' && cube.frontFace[0][2] == 'y' && cube.backFace[0][0] == 'y')
            cube.apply("U2 L F R' F' L' F R F'");
        else if (cube.topFace[0][2] == 'y' && cube.topFace[2][2] == 'y' && cube.frontFace[0][0] == 'y' && cube.backFace[0][2] == 'y')
            cube.apply("L F R' F' L' F R F'");
        else if (cube.topFace[0][0] == 'y' && cube.topFace[0][2] == 'y' && cube.leftFace[0][2] == 'y' && cube.rightFace[0][0] == 'y')
            cube.apply("U L F R' F' L' F R F'");
        else if (cube.topFace[2][0] == 'y' && cube.topFace[2][2] == 'y' && cube.leftFace[0][0] == 'y' && cube.rightFace[0][2] == 'y')
            cube.apply("U' L F R' F' L' F R F'");
        else if (cube.topFace[0][2] == 'y' && cube.topFace[2][0] == 'y' && cube.backFace[0][2] == 'y' && cube.rightFace[0][0] == 'y')
            cube.apply("R' U2 R' D' R U2 R' D R2");
        else if (cube.topFace[0][0] == 'y' && cube.topFace[2][2] == 'y' && cube.frontFace[0][0] == 'y' && cube.rightFace[0][2] == 'y')
            cube.apply("U' R' U2 R' D' R U2 R' D R2");
        else if (cube.topFace[0][2] == 'y' && cube.topFace[2][0] == 'y' && cube.leftFace[0][0] == 'y' && cube.frontFace[0][2] == 'y')
            cube.apply("U2 R' U2 R' D' R U2 R' D R2");
        else if (cube.topFace[0][0] == 'y' && cube.topFace[2][2] == 'y' && cube.backFace[0][0] == 'y' && cube.leftFace[0][2] == 'y')
            cube.apply("U R' U2 R' D' R U2 R' D R2");
        else if (cube.topFace[2][0] == 'y' && cube.frontFace[0][2] == 'y' && cube.rightFace[0][2] == 'y' && cube.backFace[0][2] == 'y')
            cube.apply("R U R' U R U2 R'");
        else if (cube.topFace[0][0] == 'y' && cube.leftFace[0][2] == 'y' && cube.frontFace[0][2] == 'y' && cube.rightFace[0][2] == 'y')
            cube.apply("U' R U R' U R U2 R'");
        else if (cube.topFace[0][2] == 'y' && cube.backFace[0][2] == 'y' && cube.leftFace[0][2] == 'y' && cube.frontFace[0][2] == 'y')
            cube.apply("U2 R U R' U R U2 R'");
        else if (cube.topFace[2][2] == 'y' && cube.rightFace[0][2] == 'y' && cube.backFace[0][2] == 'y' && cube.leftFace[0][2] == 'y')
            cube.apply("U R U R' U R U2 R'");
        else if (cube.topFace[0][2] == 'y' && cube.leftFace[0][0] == 'y' && cube.frontFace[0][0] == 'y' && cube.rightFace[0][0] == 'y')
            cube.apply("R U2 R' U' R U' R'");
        else if (cube.topFace[2][2] == 'y' && cube.backFace[0][0] == 'y' && cube.leftFace[0][0] == 'y' && cube.frontFace[0][0] == 'y')
            cube.apply("U' R U2 R' U' R U' R'");
        else if (cube.topFace[2][0] == 'y' && cube.rightFace[0][0] == 'y' && cube.backFace[0][0] == 'y' && cube.leftFace[0][0] == 'y')
            cube.apply("U2 R U2 R' U' R U' R'");
        else if (cube.topFace[0][0] == 'y' && cube.frontFace[0][0] == 'y' && cube.rightFace[0][0] == 'y' && cube.backFace[0][0] == 'y')
            cube.apply("U R U2 R' U' R U' R'");
        else
            return; // Should not occur
    }

    void solveOLL()
    {
        solveYellowCross();
        solveOLLCorners();
    }

    void solvePLLCorners()
    {
        if (cube.frontFace[0][0] == cube.frontFace[0][2] && cube.backFace[0][0] == cube.backFace[0][2])
            return;
        else if (cube.frontFace[0][0] == cube.backFace[0][2] && cube.frontFace[0][2] == cube.backFace[0][0])
            cube.apply("F R U' R' U' R U R' F' R U R' U' R' F R F'"); // Y Perm
        else if (cube.leftFace[0][0] == cube.leftFace[0][2] && cube.frontFace[0][2] == cube.backFace[0][0])
            cube.apply("R U R' U' R' F R2 U' R' U' R U R' F'"); // T Perm
        else if (cube.backFace[0][0] == cube.backFace[0][2] && cube.leftFace[0][2] == cube.rightFace[0][0])
            cube.apply("U' R U R' U' R' F R2 U' R' U' R U R' F'");
        else if (cube.rightFace[0][0] == cube.rightFace[0][2] && cube.frontFace[0][0] == cube.backFace[0][2])
            cube.apply("U2 R U R' U' R' F R2 U' R' U' R U R' F'");
        else if (cube.frontFace[0][0] == cube.frontFace[0][2] && cube.leftFace[0][0] == cube.rightFace[0][2])
            cube.apply("U R U R' U' R' F R2 U' R' U' R U R' F'");
        else
            return;
    }

    void solvePLLEdges()
    //Slightly more difficult as I could not use 'M' moves which are most common for these PLL states
    {
        if (cube.frontFace[0][1] == cube.frontFace[0][0] && cube.backFace[0][1] == cube.backFace[0][0])
            return;
        // Accounts for all the values, might be overkill but it was to fix a previous error
        else if (cube.frontFace[0][1] == cube.backFace[0][0] && cube.backFace[0][1] == cube.frontFace[0][0] &&
                 cube.leftFace[0][1] == cube.rightFace[0][0] && cube.rightFace[0][1] == cube.leftFace[0][0])
            cube.apply("R2 U2 R U2 R2 U2 R2 U2 R U2 R2");
        else if (cube.frontFace[0][1] == cube.rightFace[0][0] && cube.backFace[0][1] == cube.backFace[0][0])
            cube.apply("R U' R U R U R U' R' U' R2");
        else if (cube.backFace[0][1] == cube.leftFace[0][0] && cube.rightFace[0][1] == cube.rightFace[0][0])
            cube.apply("U' R U' R U R U R U' R' U' R2");
        else if (cube.frontFace[0][1] == cube.frontFace[0][0] && cube.leftFace[0][1] == cube.rightFace[0][0])
            cube.apply("U2 R U' R U R U R U' R' U' R2");
        else if (cube.frontFace[0][1] == cube.rightFace[0][0] && cube.rightFace[0][1] == cube.backFace[0][0])
            cube.apply("U R U' R U R U R U' R' U' R2");
        else if (cube.frontFace[0][1] == cube.leftFace[0][0] && cube.backFace[0][1] == cube.backFace[0][0])
            cube.apply("R2 U R U R' U' R' U' R' U R'");
        else if (cube.frontFace[0][1] == cube.leftFace[0][0] && cube.leftFace[0][1] == cube.backFace[0][0])
            cube.apply("U' R2 U R U R' U' R' U' R' U R'");
        else if (cube.frontFace[0][1] == cube.frontFace[0][0] && cube.rightFace[0][1] == cube.leftFace[0][0])
            cube.apply("U2 R2 U R U R' U' R' U' R' U R'");
        else if (cube.backFace[0][1] == cube.rightFace[0][0] && cube.leftFace[0][1] == cube.leftFace[0][0])
            cube.apply("U R2 U R U R' U' R' U' R' U R'");
        else if (cube.frontFace[0][1] == cube.leftFace[0][0] && cube.leftFace[0][1] == cube.frontFace[0][0])
            cube.apply("U R' U' R U' R U R U' R' U R U R2 U' R'");
        else if (cube.frontFace[0][1] == cube.rightFace[0][0] && cube.rightFace[0][1] == cube.frontFace[0][0])
            cube.apply("R' U' R U' R U R U' R' U R U R2 U' R'");
        else
            return;
    }

    void solvePLL() // Easier to call
    {
        solvePLLCorners();
        solvePLLEdges();
        solveAUF();
    }

    void solveAUF()
    {
        if (cube.frontFace[0][1] == cube.frontFace[1][1])
            return;
        else
            while (cube.frontFace[0][1] != cube.frontFace[1][1]) // Repeats until condition is true (Shortens code a little bit)
            {
                cube.apply("U");
            }
    }

    void printSolution() // Shows the solution in moves and its length
    {
        System.out.println("Solution length: " + RubiksCube.moveHistory.size());
        System.out.println("Solution: " + String.join(" ", RubiksCube.moveHistory));
    }

    void solve() // Combines all the steps needed to solve the cube
    {
        RubiksCube.moveHistory.clear(); // So the scramble does not affect the solve move history
        solveWhiteCross();
        solveF2L();
        solveOLL();
        solvePLL();
    }
}

