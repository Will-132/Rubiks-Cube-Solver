public class CubeInspector
{
    RubiksCube cube;

    public CubeInspector(RubiksCube cube)
    {
        this.cube = cube;
    }

    // Create a way to search for pieces (Edges or corners)
    // First do edges
    static final int[][] EDGES =
    {
        {5,0,1, 0,2,1}, // White-Blue (Bottom-Front)
        {5,1,2, 1,2,1}, // White-Red (Bottom-Right)
        {5,2,1, 2,2,1}, // White-Green (Bottom-Back)
        {5,1,0, 3,2,1}, // White-Orange (Bottom-Left)
        {0,1,2, 1,1,0}, // Blue-Red (Front-Right)
        {2,1,0, 1,1,2}, // Green-Red (Back-Right)
        {2,1,2, 3,1,0}, // Green-Orange (Back-Left)
        {0,1,0, 3,1,2}, // Blue-Orange (Front-Left)
        {4,2,1, 0,0,1}, // Yellow-Blue (Top-Front)
        {4,1,2, 1,0,1}, // Yellow-Red (Top-Right)
        {4,0,1, 2,0,1}, // Yellow-Green (Top-Back)
        {4,1,0, 3,0,1}  // Yellow-Orange (Top-Left)
    };

    // Now do corners
    static final int[][] CORNERS =
    {
        {5,0,2, 0,2,2, 1,2,0}, // White-Blue-Red (Bottom-Front-Right)
        {5,2,2, 2,2,0, 1,2,2}, // White-Green-Red (Bottom-Back-Right)
        {5,2,0, 2,2,2, 3,2,0}, // White-Green-Orange (Bottom-Back-Left)
        {5,0,0, 0,2,0, 3,2,2}, // White-Blue-Orange (Bottom-Front-Left)
        {4,2,2, 0,0,2, 1,0,0}, // Yellow-Blue-Red (Top-Front-Right)
        {4,0,2, 2,0,0, 1,0,2}, // Yellow-Green-Red (Top-Back-Right)
        {4,0,0, 2,0,2, 3,0,0}, // Yellow-Green-Orange (Top-Back-Left)
        {4,2,0, 0,0,0, 3,0,2}  // Yellow-Blue-Orange (Top-Front-Left)
    };

    char sticker(int face, int row, int col)
    {
        switch (face)
        {
            case 0: return cube.frontFace[row][col];
            case 1: return cube.rightFace[row][col];
            case 2: return cube.backFace[row][col];
            case 3: return cube.leftFace[row][col];
            case 4: return cube.topFace[row][col];
            default: return cube.bottomFace[row][col];
        }
    }

    int findPiece(char sticker1, char sticker2)
    {
        for (int i=0; i<EDGES.length; i++)
        {
            //Checks the actual cube and assigns the characters for each piece
            char s1 = (sticker(EDGES[i][0], EDGES[i][1], EDGES[i][2]));
            char s2 = (sticker(EDGES[i][3], EDGES[i][4], EDGES[i][5]));
            //Matches in either orientation
            if ((s1 == sticker1 && s2 == sticker2) || (s1 == sticker2 && s2 == sticker1))
                return i;
        }
        return -1; //Should not happen
    }

    // Overloaded method so all we have to do is change # of arguments
    int findPiece(char sticker1, char sticker2, char sticker3)
    {
        for (int i=0; i<CORNERS.length; i++)
        {
            char s1 = (sticker(CORNERS[i][0], CORNERS[i][1], CORNERS[i][2]));
            char s2 = (sticker(CORNERS[i][3], CORNERS[i][4], CORNERS[i][5]));
            char s3 = (sticker(CORNERS[i][6], CORNERS[i][7], CORNERS[i][8]));
            //Matches in all orientations
            if ((s1 == sticker1 && s2 == sticker2 && s3 == sticker3) ||
                (s1 == sticker1 && s2 == sticker3 && s3 == sticker2) ||
                (s1 == sticker2 && s2 == sticker1 && s3 == sticker3) ||
                (s1 == sticker2 && s2 == sticker3 && s3 == sticker1) ||
                (s1 == sticker3 && s2 == sticker1 && s3 == sticker2) ||
                (s1 == sticker3 && s2 == sticker2 && s3 == sticker1))
                return i;
        }
        return -1;
    }

    // Returns the two colors of the piece in edge slot e
    char[] getEdgeStickers(int e) // Returns two colors for edge slot 'e'
    {
        char[] colors = new char[2];
        colors[0] = sticker(EDGES[e][0], EDGES[e][1], EDGES[e][2]);
        //For top and bottom edges, colors [1] gives you the side facing upward or downward
        colors[1] = sticker(EDGES[e][3], EDGES[e][4], EDGES[e][5]);
        return colors;
    }

    // Exact same thing but now for corners
    char[] getCornerStickers(int c) // Returns the three colors showing in corner slot 'c'
    {
        char[] colors = new char[3];
        colors[0] = sticker(CORNERS[c][0], CORNERS[c][1], CORNERS[c][2]); // the top/bottom-facing sticker
        colors[1] = sticker(CORNERS[c][3], CORNERS[c][4], CORNERS[c][5]); // side sticker 1
        colors[2] = sticker(CORNERS[c][6], CORNERS[c][7], CORNERS[c][8]); // side sticker 2
        return colors;
    }

    void reportEdges() // Prints every edge piece: slot, and each sticker labeled by its face
    {
        String[] slotNames = { "bottom front", "bottom right", "bottom back", "bottom left",
                               "right front", "right back", "left back", "left front",
                               "top front", "top right", "top back", "top left" };
        String[] faceNames = { "front", "right", "back", "left", "top", "bottom" };
        // indexes match the table's face convention: 0 = front 1 = right 2 = back 3 = left 4 = top 5 = bottom
        char[][] pieceList = { {'w','b'}, {'w','r'}, {'w','g'}, {'w','o'},
                               {'b','r'}, {'b','o'}, {'g','r'}, {'g','o'},
                               {'y','b'}, {'y','r'}, {'y','g'}, {'y','o'} };
        for (int p = 0; p < pieceList.length; p++)
        {
            int e = findPiece(pieceList[p][0], pieceList[p][1]);
            char[] st = getEdgeStickers(e);
            System.out.printf("Edge %s-%s %13s %6s = %s %6s = %s%n",
                pieceList[p][0], pieceList[p][1], slotNames[e],
                faceNames[EDGES[e][0]], st[0], faceNames[EDGES[e][3]], st[1]);
        }
    }

    void reportCorners() // Prints where each corner piece is and which way it faces
    {
        String[] slotNames = { "bottom front right", "bottom back right", "bottom back left", "bottom front left",
                               "top front right", "top back right", "top back left", "top front left" };
        char[][] pieceList = { {'w','b','r'}, {'w','b','o'}, {'w','g','r'}, {'w','g','o'},
                               {'y','b','r'}, {'y','b','o'}, {'y','g','r'}, {'y','g','o'} };
        for (int p = 0; p < pieceList.length; p++)
        {
            int c = findPiece(pieceList[p][0], pieceList[p][1], pieceList[p][2]);
            char[] st = getCornerStickers(c);
            System.out.printf("Corner %s-%s-%s %18s top/bottom = %s front/back = %s left/right = %s%n",
                pieceList[p][0], pieceList[p][1], pieceList[p][2], slotNames[c], st[0], st[1], st[2]);
        }
    }

    void reportCubeState() // Combined to show cube state, helpful for visualizing hhe the cube instead of 2d arrays
    {
        System.out.println("\n=== EDGES ===");
        reportEdges();
        System.out.println("\n=== CORNERS ===");
        reportCorners();
    }
}

