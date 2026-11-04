import java.util.*;
import java.util.Scanner;
public class RubiksCube
{

   public static void main(String[] args)
   {
      RubiksCube myCube = new RubiksCube(); 
      
      myCube.combinedOptionsRun();      
   }
   
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
      for (int i=0; i<6; i++)
         for (int j=0; j<3; j++)
            for (int k=0; k<3; k++)
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
      
      {  System.out.print("      "); //To replicate the dimensions and have it centered 
         for (int j=0; j<topFace[i].length; j++)
            System.out.print(topFace[i][j]+ " ");
            System.out.println();
      }
      
      /* I have to go by individual lines to show how
       the cube looks rather than print than the enitre
        3x3 side at once. Do this by combining into for loops
      I can make some assumptions as all sides are same size*/
      
      for (int i=0; i<3; i++)
      
      {   
          for (int j=0; j<leftFace[i].length; j++)
            System.out.print(leftFace[i][j]+ " ");
            
          for (int k=0; k<frontFace[i].length; k++)
            System.out.print(frontFace[i][k]+ " ");
            
          for (int l=0; l<rightFace[i].length; l++)
            System.out.print(rightFace[i][l]+ " ");
            
          for (int m=0; m<backFace[i].length; m++)
            System.out.print(backFace[i][m]+ " "); 
          System.out.println();
      }
      /*Create the bottom part the same way we
       did the top to format the cube */
      for (int i=0; i<3; i++)
      {     System.out.print("      ");  
         for (int j=0; j<bottomFace[i].length; j++)
            System.out.print(bottomFace[i][j]+ " ");
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
      for (int i = 0; i<3;i++)
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
      for (int i = 0; i<3 ; i++)
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
      for (int j = 0 ; j<2 ; j++)
      {
         turnF();
      }
   } 
   
   void turnFPrime()
   {
      for (int j = 0 ; j<3 ; j++)
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
      for (int i = 0; i<3 ; i++)
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
      for (int j = 0; j<2 ; j++)
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
      for (String move : moves)
      {
         move = move.trim().toUpperCase(); //To allow for accidental errors with capitalization
         
         switch (move) // Change the written moves into our methods
         {   
            case "U" : turnU(); moveHistory.add("U"); break;
            case "U2" : turnU2(); moveHistory.add("U2"); break;
            case "U'" : turnUPrime(); moveHistory.add("U'"); break;
            case "D" : turnD(); moveHistory.add("D"); break;
            case "D2" : turnD2(); moveHistory.add("D2"); break;
            case "D'" : turnDPrime(); moveHistory.add("D'"); break;
            case "F" : turnF(); moveHistory.add("F"); break;
            case "F2" : turnF2(); moveHistory.add("F2"); break;
            case "F'" : turnFPrime(); moveHistory.add("F'"); break;
            case "B" : turnB(); moveHistory.add("B"); break;
            case "B2" : turnB2(); moveHistory.add("B2"); break;
            case "B'" : turnBPrime(); moveHistory.add("B'"); break;
            case "L" : turnL(); moveHistory.add("L"); break;
            case "L2" : turnL2(); moveHistory.add("L2"); break;
            case "L'" : turnLPrime(); moveHistory.add("L'"); break;
            case "R" : turnR(); moveHistory.add("R"); break;
            case "R2" : turnR2(); moveHistory.add("R2"); break;
            case "R'" : turnRPrime(); moveHistory.add("R'"); break;
            
            // In case of errors within compilation,
            // give an error rather than let it compile wrong
            default:
               System.out.println("Invalid move : \"" + move + "\"\n"); break;   
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
      for (String move : moves)
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
      for (String m : moves)
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
         System.out.println("Invalid scramble — check your moves and try again.");
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

  
  // Create a way to search for pieces (Edges or corners)
  // First do edges
  static final int[][] EDGES =
   {
      {5,0,1,  0,2,1},   // White-Blue    (Bottom-Front)
      {5,1,2,  1,2,1},   // White-Red     (Bottom-Right)
      {5,2,1,  2,2,1},   // White-Green   (Bottom-Back)
      {5,1,0,  3,2,1},   // White-Orange  (Bottom-Left)
      {0,1,2,  1,1,0},   // Blue-Red      (Front-Right)
      {2,1,0,  1,1,2},   // Green-Red     (Back-Right)
      {2,1,2,  3,1,0},   // Green-Orange  (Back-Left)
      {0,1,0,  3,1,2},   // Blue-Orange   (Front-Left) 
      {4,2,1,  0,0,1},   // Yellow-Blue   (Top-Front)
      {4,1,2,  1,0,1},   // Yellow-Red    (Top-Right)
      {4,0,1,  2,0,1},   // Yellow-Green  (Top-Back)
      {4,1,0,  3,0,1}    // Yellow-Orange (Top-Left)
   }; 

   // Now do corners
   static final int[][] CORNERS =
   {
      {5,0,2,  0,2,2,  1,2,0},   // White-Blue-Red      (Bottom-Front-Right)
      {5,2,2,  2,2,0,  1,2,2},   // White-Green-Red     (Bottom-Back-Right)
      {5,2,0,  2,2,2,  3,2,0},   // White-Green-Orange  (Bottom-Back-Left)
      {5,0,0,  0,2,0,  3,2,2},   // White-Blue-Orange   (Bottom-Front-Left)
      {4,2,2,  0,0,2,  1,0,0},   // Yellow-Blue-Red     (Top-Front-Right)
      {4,0,2,  2,0,0,  1,0,2},   // Yellow-Green-Red    (Top-Back-Right)
      {4,0,0,  2,0,2,  3,0,0},   // Yellow-Green-Orange (Top-Back-Left)
      {4,2,0,  0,0,0,  3,0,2},   // Yellow-Blue-Orange  (Top-Front-Left)
   };   
   
   char sticker(int face, int row, int col)
   {
      switch (face)
      {
         case 0: return frontFace[row][col];
         case 1: return rightFace[row][col];
         case 2: return backFace[row][col];
         case 3: return leftFace[row][col];
         case 4: return topFace[row][col];
         default: return bottomFace[row][col];
      }
   }
   
   int findPiece(char sticker1, char sticker2)
   {
      for (int i = 0; i<EDGES.length; i++)
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
      for (int i = 0; i<CORNERS.length; i++)
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
      //For top and bottom edges, colors[1] gives you the side facing upward or downward 
      colors[1] = sticker(EDGES[e][3], EDGES[e][4], EDGES[e][5]);
      return colors;
   }
   
   // Exact same thing but now for corners
   char[] getCornerStickers(int c) // Returns the three colors showing in corner slot 'c'
   {
      char[] colors = new char[3];
      colors[0] = sticker(CORNERS[c][0], CORNERS[c][1], CORNERS[c][2]);   // the top/bottom-facing sticker
      colors[1] = sticker(CORNERS[c][3], CORNERS[c][4], CORNERS[c][5]);   // side sticker 1
      colors[2] = sticker(CORNERS[c][6], CORNERS[c][7], CORNERS[c][8]);   // side sticker 2
      return colors;
   }
   
   
   void reportEdges() // Prints every edge piece: slot, and each sticker labeled by its face
   {
      String[] slotNames = { "bottom front", "bottom right", "bottom back", "bottom left",
                             "right front",  "right back",   "left back",   "left front",
                             "top front",    "top right",    "top back",     "top left" };
        
   
      String[] faceNames = { "front", "right", "back", "left", "top", "bottom" };
      // indexes match the table's face convention: 0=front 1=right 2=back 3=left 4=top 5=bottom
   
      char[][] pieceList = { {'w','b'}, {'w','r'}, {'w','g'}, {'w','o'},
                             {'b','r'}, {'b','o'}, {'g','r'}, {'g','o'},
                             {'y','b'}, {'y','r'}, {'y','g'}, {'y','o'} };
   
      for (int p = 0; p < pieceList.length; p++)
      {
         int e = findPiece(pieceList[p][0], pieceList[p][1]);
         char[] st = getEdgeStickers(e);
         
                                
         System.out.printf("Edge %s-%s  %13s   %6s = %s %6s = %s%n",
                  pieceList[p][0], pieceList[p][1], slotNames[e],
                  faceNames[EDGES[e][0]], st[0], faceNames[EDGES[e][3]], st[1]);
      }
   }
   
   
   void reportCorners() // Prints where each corner piece is and which way it faces
   {
      String[] slotNames = { "bottom front right", "bottom back right", "bottom back left", "bottom front left",
                             "top front right",     "top back right",     "top back left",     "top front left" };
   
      char[][] pieceList = { {'w','b','r'}, {'w','b','o'}, {'w','g','r'}, {'w','g','o'},
                             {'y','b','r'}, {'y','b','o'}, {'y','g','r'}, {'y','g','o'} };
   
      for (int p = 0; p < pieceList.length; p++)
      {
         int c = findPiece(pieceList[p][0], pieceList[p][1], pieceList[p][2]);
         char[] st = getCornerStickers(c);
      
         System.out.printf("Corner %s-%s-%s  %18s   top/bottom = %s  front/back = %s  left/right = %s%n",
                            pieceList[p][0], pieceList[p][1], pieceList[p][2], slotNames[c], st[0], st[1], st[2]);
      }
   }      
      
      
   void reportCubeState() // Combined to show cube state, helpful for visualizing hhe the cube instead of 2d arrays
   {
      System.out.println("\n===EDGES===");
      reportEdges();
      
      System.out.println("\n=== CORNERS ===");
      reportCorners();
   }
   
   //Now onto implementing algorithms to solve the cube
   void solveWhiteBlueEdge()
   {
      int e = findPiece('w', 'b');       // Finds piece
      char[] p = getEdgeStickers(e);     // Gives sticker colors of the edge
      
      if (e == 0 && p[0] == 'w')
         return;
      else if (e == 0 && p[0] == 'b')
         apply("F L D");
         
      else if (e == 1 && p[0] == 'w')
         apply("D'");
      else if (e == 1 && p[0] == 'b')
         apply("R F");  
         
      else if (e == 2 && p[0] == 'w')
         apply("D2");
      else if (e == 2 && p[0] == 'b')
         apply("B R D'"); 
         
      else if (e == 3 && p[0] == 'w')
         apply("D");
      else if (e == 3 && p[0] == 'b')
         apply("L' F'");
      
      else if (e == 4 && p[0] == 'w')
         apply("R' D'");
      else if (e == 4 && p[0] == 'b')
         apply("F");
         
      else if (e == 5 && p[0] == 'w')
         apply("R D'");
      else if (e == 5 && p[0] == 'b')
         apply("R2 F");
      
      else if (e == 6 && p[0] == 'w')
         apply("L' D");
      else if (e == 6 && p[0] == 'b')
         apply("L2 F'");
      
      else if (e == 7 && p[0] == 'w')
         apply("L D");
      else if (e == 7 && p[0] == 'b')
         apply("F'");
      
       else if (e == 8 && p[0] == 'w')
         apply("F2");
      else if (e == 8 && p[0] == 'b')
         apply("U' R' F");
      
      else if (e == 9 && p[0] == 'w')
         apply("R2 D'");
      else if (e == 9 && p[0] == 'b')
         apply("R' F");
      
      else if (e == 10 && p[0] == 'w')
         apply("U2 F2");
      else if (e == 10 && p[0] == 'b')
         apply("U R' F");
         
      else if (e == 11 && p[0] == 'w')
         apply("L2 D");
      else if (e == 11 && p[0] == 'b')
         apply("L F'");
         
      else
         return;
   }
   
   
   void solveWhiteRedEdge()
   {
      int e = findPiece('w', 'r');       
      char[] p = getEdgeStickers(e);      
      
      // I don't have if e = 0 since that is already solved when I run the previous method: solveWhiteBlueEdge()         
      if (e == 1 && p[0] == 'w') 
         return;
      else if (e == 1 && p[0] == 'r')
         apply("R D' F D");  
         
      else if (e == 2 && p[0] == 'w')
         apply("B2 U R2");
      else if (e == 2 && p[0] == 'r')
         apply("B R"); 
         
      else if (e == 3 && p[0] == 'w')
         apply("L2 U2 R2");
      else if (e == 3 && p[0] == 'r')
         apply("L' D' F' D");
      
      else if (e == 4 && p[0] == 'w')
         apply("R'");
      else if (e == 4 && p[0] == 'r')
         apply("D' F D");
         
      else if (e == 5 && p[0] == 'w')
         apply("R");
      else if (e == 5 && p[0] == 'r')
         apply("D B' D'");
      
      else if (e == 6 && p[0] == 'w')
         apply("B2 R");
      else if (e == 6 && p[0] == 'r')
         apply("D B D'");
      
      else if (e == 7 && p[0] == 'w')
         apply("F2 R' F2");
      else if (e == 7 && p[0] == 'r')
         apply("D' F' D");
      
       else if (e == 8 && p[0] == 'w')
         apply("U' R2");
      else if (e == 8 && p[0] == 'r')
         apply("F R' F'");
      
      else if (e == 9 && p[0] == 'w')
         apply("R2");
      else if (e == 9 && p[0] == 'r')
         apply("U F R' F'");
      
      else if (e == 10 && p[0] == 'w')
         apply("U R2");
      else if (e == 10 && p[0] == 'r')
         apply("B' R B");
         
      else if (e == 11 && p[0] == 'w')
         apply("U2 R2");
      else if (e == 11 && p[0] == 'r')
         apply("U' F R' F'");
         
      else
         return;
   }
   
   
   void solveWhiteGreenEdge()
   {
      int e = findPiece('w', 'g');       
      char[] p = getEdgeStickers(e);           
       
       
      if (e == 2 && p[0] == 'w') // Can get rid of e = 0 and e = 1 as they can be deduced to not contain the edge
         return;
      else if (e == 2 && p[0] == 'g')
         apply("B2 U R B' R'"); 
         
      else if (e == 3 && p[0] == 'w')
         apply("L2 U B2");
      else if (e == 3 && p[0] == 'g')
         apply("L B");
      
      else if (e == 4 && p[0] == 'w')
         apply("D' R' D");
      else if (e == 4 && p[0] == 'g')
         apply("R2 B' R2");
         
      else if (e == 5 && p[0] == 'w')
         apply("D' R D");
      else if (e == 5 && p[0] == 'g')
         apply("B'");
      
      else if (e == 6 && p[0] == 'w')
         apply("D L' D'");
      else if (e == 6 && p[0] == 'g')
         apply("B");
      
      else if (e == 7 && p[0] == 'w')
         apply("D L D'");
      else if (e == 7 && p[0] == 'g')
         apply("L2 B");
      
       else if (e == 8 && p[0] == 'w')
         apply("U2 B2");
      else if (e == 8 && p[0] == 'g')
         apply("U' R B' R'");
      
      else if (e == 9 && p[0] == 'w')
         apply("U' B2");
      else if (e == 9 && p[0] == 'g')
         apply("R B' R'");
      
      else if (e == 10 && p[0] == 'w')
         apply("B2");
      else if (e == 10 && p[0] == 'g')
         apply("U R B' R'");
         
      else if (e == 11 && p[0] == 'w')
         apply("U B2");
      else if (e == 11 && p[0] == 'g')
         apply("L' B");
         
      else
         return;
   }
   
   
   void solveWhiteOrangeEdge() // Same steps as previous, last cross edge piece
   {
      int e = findPiece('w', 'o');       
      char[] p = getEdgeStickers(e);           
       
        
      if (e == 3 && p[0] == 'w')
         return;
      else if (e == 3 && p[0] == 'o')
         apply("L D' B D");
      
      else if (e == 4 && p[0] == 'w')
         apply("D2 R' D2");
      else if (e == 4 && p[0] == 'o')
         apply("D F D'");
         
      else if (e == 5 && p[0] == 'w')
         apply("D2 R D2");
      else if (e == 5 && p[0] == 'o')
         apply("D' B' D");
      
      else if (e == 6 && p[0] == 'w')
         apply("L'");
      else if (e == 6 && p[0] == 'o')
         apply("D' B D");
      
      else if (e == 7 && p[0] == 'w')
         apply("L");
      else if (e == 7 && p[0] == 'o')
         apply("D F' D'");
      
       else if (e == 8 && p[0] == 'w')
         apply("U L2");
      else if (e == 8 && p[0] == 'o')
         apply("F' L F");
      
      else if (e == 9 && p[0] == 'w')
         apply("U2 L2");
      else if (e == 9 && p[0] == 'o')
         apply("U F' L F");
      
      else if (e == 10 && p[0] == 'w')
         apply("U' L2");
      else if (e == 10 && p[0] == 'o')
         apply("B L' B'");
         
      else if (e == 11 && p[0] == 'w')
         apply("L2");
      else if (e == 11 && p[0] == 'o')
         apply("U' F' L F");
         
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
      int e = findPiece('w', 'b', 'r'); // Is 3 now since we are dealing with corners      
      char[] p = getCornerStickers(e);           
       
        
      if (e == 0 && p[0] == 'w')
         return;
      else if (e == 0 && p[1] == 'w')
         apply("R U' R' U R U' R'");
      else if (e == 0 && p[2] == 'w') // Accounts for all 3 states the white sticker could be
         apply("R U R' U' R U R'");
         
      else if (e == 1 && p[0] == 'w')
         apply("R' U' R F' U2 F");
      else if (e == 1 && p[1] == 'w')
         apply("R' U R U R U R'");  
      else if (e == 1 && p[2] == 'w')
         apply("R' U' R2 U2 R'");
         
      else if (e == 2 && p[0] == 'w')
         apply("L R U2  R' L'");
      else if (e == 2 && p[1] == 'w')
         apply("L U' L' R U2 R'"); 
      else if (e == 2 && p[2] == 'w')
         apply("L U L' F' U F");
         
      else if (e == 3 && p[0] == 'w')
         apply("F R U' R2 F' R");
      else if (e == 3 && p[1] == 'w')
         apply("L' U L U' R U R'");
      else if (e == 3 && p[2] == 'w')
         apply("L' R U' R' L");
      
      else if (e == 4 && p[0] == 'w')
         apply("R U' R' F' U2 F");
      else if (e == 4 && p[1] == 'w')
         apply("U R U' R'");
      else if (e == 4 && p[2] == 'w')
         apply("R U R'");
         
      else if (e == 5 && p[0] == 'w')
         apply("U' R U R' U R U' R'");
      else if (e == 5 && p[1] == 'w')
         apply("U R U R'");
      else if (e == 5 && p[2] == 'w')
         apply("U2 R U' R'");
      
      else if (e == 6 && p[0] == 'w')
         apply("R U R' U R U' R'");
      else if (e == 6 && p[1] == 'w')
         apply("R U2 R'");
      else if (e == 6 && p[2] == 'w')
         apply("F' U2 F");
      
      else if (e == 7 && p[0] == 'w')
         apply("R U2 R' U R U' R'");
      else if (e == 7 && p[1] == 'w')
         apply("U' R U R'");
      else if (e == 7 && p[2] == 'w')
         apply("R U' R'");
           
      else
         return;
   }
   
         
   void solveWhiteGreenRedCorner()
   {
      int e = findPiece('w', 'g', 'r');       
      char[] p = getCornerStickers(e);           
       
               
      if (e == 1 && p[0] == 'w')
         return;
      else if (e == 1 && p[1] == 'w')
         apply("R' U R U' R' U R");  
      else if (e == 1 && p[2] == 'w')
         apply("R' U' R U R' U' R");
         
      else if (e == 2 && p[0] == 'w')
         apply("L U L' R' U' R");
      else if (e == 2 && p[1] == 'w')
         apply("L U' L' U R' U' R"); 
      else if (e == 2 && p[2] == 'w')
         apply("L R' U R L'");
         
      else if (e == 3 && p[0] == 'w')
         apply("L' R' U2 R L");
      else if (e == 3 && p[1] == 'w')
         apply("L' U L R' U2 R");
      else if (e == 3 && p[2] == 'w')
         apply("L' U' L U' R' U' R");
      
      else if (e == 4 && p[0] == 'w')
         apply("U R' U' R U' R' U R");
      else if (e == 4 && p[1] == 'w')
         apply("U' R' U' R");
      else if (e == 4 && p[2] == 'w')
         apply("U2 R' U R");
         
      else if (e == 5 && p[0] == 'w')
         apply("R' U2 R U R' U' R");
      else if (e == 5 && p[1] == 'w')
         apply("U' R' U R");
      else if (e == 5 && p[2] == 'w')
         apply("R' U' R");
      
      else if (e == 6 && p[0] == 'w')
         apply("R' U2 R U' R' U R");
      else if (e == 6 && p[1] == 'w')
         apply("U R' U' R");
      else if (e == 6 && p[2] == 'w')
         apply("R' U R");
      
      else if (e == 7 && p[0] == 'w')
         apply("R' U' R U' R' U R");
      else if (e == 7 && p[1] == 'w')
         apply("R' U2 R");
      else if (e == 7 && p[2] == 'w')
         apply("U2 R' U' R");
         
      else
         return;
   }         
         
         
   void solveWhiteGreenOrangeCorner()
   {
      int e = findPiece('w', 'g', 'o');       
      char[] p = getCornerStickers(e);           
       
               
      if (e == 2 && p[0] == 'w')
         return;
      else if (e == 2 && p[1] == 'w')
         apply("L U' L' U L U' L'"); 
      else if (e == 2 && p[2] == 'w')
         apply("L U L' U' L U L'");
         
      else if (e == 3 && p[0] == 'w')
         apply("L' U L U2 L U' L'");
      else if (e == 3 && p[1] == 'w')
         apply("L' U L U L U L'");
      else if (e == 3 && p[2] == 'w')
         apply("L' U' L2 U2 L'");
      
      else if (e == 4 && p[0] == 'w')
         apply("L U L' U L U' L'");
      else if (e == 4 && p[1] == 'w')
         apply("L U2 L'");
      else if (e == 4 && p[2] == 'w')
         apply("U2 L U L'");
         
      else if (e == 5 && p[0] == 'w')
         apply("L U2 L' U L U' L'");
      else if (e == 5 && p[1] == 'w')
         apply("U' L U L'");
      else if (e == 5 && p[2] == 'w')
         apply("L U' L'");
      
      else if (e == 6 && p[0] == 'w')
         apply("L U2 L' U' L U L'");
      else if (e == 6 && p[1] == 'w')
         apply("U L U' L'");
      else if (e == 6 && p[2] == 'w')
         apply("L U L'");
      
      else if (e == 7 && p[0] == 'w')
         apply("L' U L2 U' L'");
      else if (e == 7 && p[1] == 'w')
         apply("U L U L'");
      else if (e == 7 && p[2] == 'w')
         apply("U2 L U' L'");
        
         
      else
         return;
   }         
         
         
   void solveWhiteBlueOrangeCorner()
   {
      int e = findPiece('w', 'b', 'o');       
      char[] p = getCornerStickers(e);           
       
         
      if (e == 3 && p[0] == 'w')
         return;
      else if (e == 3 && p[1] == 'w')
         apply("L' U L U' L' U L");
      else if (e == 3 && p[2] == 'w')
         apply("L' U' L U L' U' L");
      
      else if (e == 4 && p[0] == 'w')
         apply("L' U2 L U' L' U L");
      else if (e == 4 && p[1] == 'w')
         apply("U L' U' L");
      else if (e == 4 && p[2] == 'w')
         apply("L' U L");
         
      else if (e == 5 && p[0] == 'w')
         apply("L' U' L U' L' U L");
      else if (e == 5 && p[1] == 'w')
         apply("L' U2 L");
      else if (e == 5 && p[2] == 'w')
         apply("F U2 F'");
      
      else if (e == 6 && p[0] == 'w')
         apply("U L' U' L U' L' U L");
      else if (e == 6 && p[1] == 'w')
         apply("U' L' U' L");
      else if (e == 6 && p[2] == 'w')
         apply("U2 L' U L");
      
      else if (e == 7 && p[0] == 'w')
         apply("L' U2 L U L' U' L");
      else if (e == 7 && p[1] == 'w')
         apply("U' L' U L");
      else if (e == 7 && p[2] == 'w')
         apply("L' U' L");
        
         
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
      int e = findPiece('b', 'r');       
      char[] p = getEdgeStickers(e);           
       
      if (e == 4 && p[0] == 'b')
         return;
      else if (e == 4 && p[0] == 'r')
         apply("F' U F U' R U2 R' U2 R U' R'"); 
         // These algorithms are longer as they need to account for the whole bottom layer already being solved
         
      else if (e == 5 && p[0] == 'b')
         apply("R2 U2 R2 U2 R2");
      else if (e == 5 && p[0] == 'r')
         apply("D R' U' R D' F' U2 F");
      
      else if (e == 6 && p[0] == 'b')
         apply("D2 L U L' D2 U2 R U' R'");
      else if (e == 6 && p[0] == 'r')
         apply("L U L' D2 R U2 R' U F' U' F D2");
      
      else if (e == 7 && p[0] == 'b')
         apply("F2 U2 F2 U2 F2");
      else if (e == 7 && p[0] == 'r')
         apply("D' L' U' L U' D F' U F");
      
      else if (e == 8 && p[0] == 'b')
         apply("U2 F' R U R' U' R' F R");
      else if (e == 8 && p[0] == 'r')
         apply("U R U' R' F R' F' R");
      
      else if (e == 9 && p[0] == 'b')
         apply("U' F' R U R' U' R' F R");
      else if (e == 9 && p[0] == 'r')
         apply("U2 R U' R' F R' F' R");
      
      else if (e == 10 && p[0] == 'b')
         apply("F' R U R' U' R' F R");
      else if (e == 10 && p[0] == 'r')
         apply("U' R U' R' F R' F' R");
         
      else if (e == 11 && p[0] == 'b')
         apply("U F' R U R' U' R' F R");
      else if (e == 11 && p[0] == 'r')
         apply("R U' R' F R' F' R");
         
      else
         return;
   }    
    
    
   void solveGreenRedEdge()
   // Not only account for bottom layer, but also solved edge pieces
   {
      int e = findPiece('g', 'r');       
      char[] p = getEdgeStickers(e);           
       
        
      if (e == 5 && p[0] == 'g')
         return;
      else if (e == 5 && p[0] == 'r')
         apply("B U' B' U R' U2 R U2 R' U R");
      
      else if (e == 6 && p[0] == 'g')
         apply("B2 U2 B2 U2 B2");
      else if (e == 6 && p[0] == 'r')
         apply("D L U L' D' U B U' B'");
      
      else if (e == 7 && p[0] == 'g')
         apply("D2 L' U L D2 U2 R' U' R");
      else if (e == 7 && p[0] == 'r')
         apply("L' U' L D2 R' U2 R U' B U B' D2"); 
      
      else if (e == 8 && p[0] == 'g')
         apply("U' R' U R U R' U R U' R' U' R");
      else if (e == 8 && p[0] == 'r')
         apply("U R' U R B' R B R'");
      
      else if (e == 9 && p[0] == 'g')
         apply("R' U R U R' U R U' R' U' R");
      else if (e == 9 && p[0] == 'r')
         apply("U2 R' U R B' R B R'");
      
      else if (e == 10 && p[0] == 'g')
         apply("U R' U R U R' U R U' R' U' R");
      else if (e == 10 && p[0] == 'r')
         apply("U' R' U R B' R B R'");
         
      else if (e == 11 && p[0] == 'g')
         apply("R' U R2 U2 R2 U' R2 U' R'");
      else if (e == 11 && p[0] == 'r')
         apply("R' U R B' R B R'");
         
      else
         return;
   }    
 
   
   void solveGreenOrangeEdge()
   {
      int e = findPiece('g', 'o');       
      char[] p = getEdgeStickers(e);           
       
        
      if (e == 6 && p[0] == 'g')
         return;
      else if (e == 6 && p[0] == 'o')
         apply("B' U B U' L U2 L' U2 L U' L'");
      
      else if (e == 7 && p[0] == 'g')
         apply("L2 U2 L2 U2 L2");
      else if (e == 7 && p[0] == 'o')
         apply("D L' U' L D' B' U2 B");
      
      else if (e == 8 && p[0] == 'g')
         apply("B' L U L' U' L' B L");
      else if (e == 8 && p[0] == 'o')
         apply("U' L U' L' B L' B' L");
      
      else if (e == 9 && p[0] == 'g')
         apply("U B' L U L' U' L' B L");
      else if (e == 9 && p[0] == 'o')
         apply("L U' L' B L' B' L");
      
      else if (e == 10 && p[0] == 'g')
         apply("U2 B' L U L' U' L' B L");
      else if (e == 10 && p[0] == 'o')
         apply("U L U' L' B L' B' L");
         
      else if (e == 11 && p[0] == 'g')
         apply("U' B' L U L' U' L' B L");
      else if (e == 11 && p[0] == 'o')
         apply("U2 L U' L' B L' B' L");
         
      else
         return;
   } 
   

   void solveBlueOrangeEdge()
   // Reused lots of previous aspects for edge slots, especially for when the edge is on the top face
   {
      int e = findPiece('b', 'o');       
      char[] p = getEdgeStickers(e);           
       
        
      if (e == 7 && p[0] == 'b')
         return;
      else if (e == 7 && p[0] == 'o')
         apply("F U' F' U L' U2 L U2 L' U L");
      
      else if (e == 8 && p[0] == 'b')
         apply("U2 F L' U' L U L F' L'");
      else if (e == 8 && p[0] == 'o')
         apply("U' L' U L F' L F L'");
      
      else if (e == 9 && p[0] == 'b')
         apply("U' F L' U' L U L F' L'");
      else if (e == 9 && p[0] == 'o')
         apply("L' U L F' L F L'");
      
      else if (e == 10 && p[0] == 'b')
         apply("F L' U' L U L F' L'");
      else if (e == 10 && p[0] == 'o')
         apply("U L' U L F' L F L'");
         
      else if (e == 11 && p[0] == 'b')
         apply("U F L' U' L U L F' L'");
      else if (e == 11 && p[0] == 'o')
         apply("U2 L' U L F' L F L'");
         
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
      if (topFace[0][1] == 'y' && topFace[1][0] == 'y' && topFace[1][2] == 'y' && topFace[2][1] == 'y')
         return;
         
      else if (topFace[0][1] == 'y' && topFace[1][0] == 'y' && topFace[1][2] != 'y' && topFace[2][1] != 'y')
         apply("F U R U' R' F'");
      else if (topFace[0][1] == 'y' && topFace[1][0] != 'y' && topFace[1][2] == 'y' && topFace[2][1] != 'y')
         apply("U' F U R U' R' F'");
      else if (topFace[0][1] != 'y' && topFace[1][0] != 'y' && topFace[1][2] == 'y' && topFace[2][1] == 'y')
         apply("U2 F U R U' R' F'");
      else if (topFace[0][1] != 'y' && topFace[1][0] == 'y' && topFace[1][2] != 'y' && topFace[2][1] == 'y')
         apply("U F U R U' R' F'");
         
      else if (topFace[0][1] == 'y' && topFace[1][0] != 'y' && topFace[1][2] != 'y' && topFace[2][1] == 'y')
         apply("U F R U R' U' F'");
      else if (topFace[0][1] != 'y' && topFace[1][0] == 'y' && topFace[1][2] == 'y' && topFace[2][1] != 'y')
         apply("F R U R' U' F'");
         
      else if (topFace[0][1] != 'y' && topFace[1][0] != 'y' && topFace[1][2] != 'y' && topFace[2][1] != 'y')
         apply("R U2 R2 F R F' U2 R' F R F'");
      else
         return;
   }
   
   
   void solveOLLCorners() // Part of 2-step OLL in beginners method
                          // Much easier to program as normal OLL has 57 algorithms (215 possible permutations)
   {
      if (topFace == SOLVED_CUBE.topFace) // Compares to the solved cube (Easier than sorting through each sticker)
         return;
         
      else if (frontFace[0][0] == 'y' && frontFace[0][2] == 'y' && backFace[0][0] == 'y' && backFace[0][2] == 'y')
         apply("R U2 R' U' R U R' U' R U' R'");
      else if (leftFace[0][0] == 'y' && leftFace[0][2] == 'y' && rightFace[0][0] == 'y' && rightFace[0][2] == 'y')
         apply("U R U2 R' U' R U R' U' R U' R'");
         
         
      else if (topFace[0][0] == 'y' && topFace[0][2] == 'y' && frontFace[0][0] == 'y' && frontFace[0][2] == 'y')
         apply("U2 R2 D' R U2 R' D R U2 R");
      else if (topFace[2][0] == 'y' && topFace[2][2] == 'y' && backFace[0][0] == 'y' && backFace[0][2] == 'y')
         apply("R2 D' R U2 R' D R U2 R");
      else if (topFace[0][0] == 'y' && topFace[2][0] == 'y' && rightFace[0][0] == 'y' && rightFace[0][2] == 'y')
         apply("U' R2 D' R U2 R' D R U2 R");
      else if (topFace[0][2] == 'y' && topFace[2][2] == 'y' && leftFace[0][0] == 'y' && leftFace[0][2] == 'y')
         apply("U R2 D' R U2 R' D R U2 R");
         
      else if (leftFace[0][0] == 'y' && leftFace[0][2] == 'y' && frontFace[0][2] == 'y' && backFace[0][0] == 'y')
         apply("R U2 R2 U' R2 U' R2 U2 R");
      else if (rightFace[0][0] == 'y' && rightFace[0][2] == 'y' && frontFace[0][0] == 'y' && backFace[0][2] == 'y')
         apply("U2 R U2 R2 U' R2 U' R2 U2 R");
      else if (frontFace[0][0] == 'y' && frontFace[0][2] == 'y' && rightFace[0][2] == 'y' && leftFace[0][0] == 'y')
         apply("U R U2 R2 U' R2 U' R2 U2 R");
      else if (backFace[0][0] == 'y' && backFace[0][2] == 'y' && leftFace[0][2] == 'y' && rightFace[0][0] == 'y')
         apply("U' R U2 R2 U' R2 U' R2 U2 R");
         
      else if (topFace[0][0] == 'y' && topFace[2][0] == 'y' && frontFace[0][2] == 'y' && backFace[0][0] == 'y')
         apply("U2 L F R' F' L' F R F'");
      else if (topFace[0][2] == 'y' && topFace[2][2] == 'y' && frontFace[0][0] == 'y' && backFace[0][2] == 'y')
         apply("L F R' F' L' F R F'");
      else if (topFace[0][0] == 'y' && topFace[0][2] == 'y' && leftFace[0][2] == 'y' && rightFace[0][0] == 'y')
         apply("U L F R' F' L' F R F'");
      else if (topFace[2][0] == 'y' && topFace[2][2] == 'y' && leftFace[0][0] == 'y' && rightFace[0][2] == 'y')
         apply("U' L F R' F' L' F R F'");
         
      else if (topFace[0][2] == 'y' && topFace[2][0] == 'y' && backFace[0][2] == 'y' && rightFace[0][0] == 'y')
         apply("R' U2 R' D' R U2 R' D R2");
      else if (topFace[0][0] == 'y' && topFace[2][2] == 'y' && frontFace[0][0] == 'y' && rightFace[0][2] == 'y')
         apply("U' R' U2 R' D' R U2 R' D R2");
      else if (topFace[0][2] == 'y' && topFace[2][0] == 'y' && leftFace[0][0] == 'y' && frontFace[0][2] == 'y')
         apply("U2 R' U2 R' D' R U2 R' D R2");
      else if (topFace[0][0] == 'y' && topFace[2][2] == 'y' && backFace[0][0] == 'y' && leftFace[0][2] == 'y')
         apply("U R' U2 R' D' R U2 R' D R2");
         
      else if (topFace[2][0] == 'y' && frontFace[0][2] == 'y' && rightFace[0][2] == 'y' && backFace[0][2] == 'y')
         apply("R U R' U R U2 R'");
      else if (topFace[0][0] == 'y' && leftFace[0][2] == 'y' && frontFace[0][2] == 'y' && rightFace[0][2] == 'y')
         apply("U' R U R' U R U2 R'");
      else if (topFace[0][2] == 'y' && backFace[0][2] == 'y' && leftFace[0][2] == 'y' && frontFace[0][2] == 'y')
         apply("U2 R U R' U R U2 R'");
      else if (topFace[2][2] == 'y' && rightFace[0][2] == 'y' && backFace[0][2] == 'y' && leftFace[0][2] == 'y')
         apply("U R U R' U R U2 R'");
         
      else if (topFace[0][2] == 'y' && leftFace[0][0] == 'y' && frontFace[0][0] == 'y' && rightFace[0][0] == 'y')
         apply("R U2 R' U' R U' R'");
      else if (topFace[2][2] == 'y' && backFace[0][0] == 'y' && leftFace[0][0] == 'y' && frontFace[0][0] == 'y')
         apply("U' R U2 R' U' R U' R'");
      else if (topFace[2][0] == 'y' && rightFace[0][0] == 'y' && backFace[0][0] == 'y' && leftFace[0][0] == 'y')
         apply("U2 R U2 R' U' R U' R'");
      else if (topFace[0][0] == 'y' && frontFace[0][0] == 'y' && rightFace[0][0] == 'y' && backFace[0][0] == 'y')
         apply("U R U2 R' U' R U' R'");
                   
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
      if (frontFace[0][0] == frontFace[0][2] && backFace[0][0] == backFace[0][2])
         return;
         
      else if (frontFace[0][0] == backFace[0][2] && frontFace[0][2] == backFace[0][0])
         apply("F R U' R' U' R U R' F' R U R' U' R' F R F'"); // Y Perm
         
      else if (leftFace[0][0] == leftFace[0][2] && frontFace[0][2] == backFace[0][0])
         apply("R U R' U' R' F R2 U' R' U' R U R' F'"); // T Perm
      else if (backFace[0][0] == backFace[0][2] && leftFace[0][2] == rightFace[0][0])
         apply("U' R U R' U' R' F R2 U' R' U' R U R' F'");
      else if (rightFace[0][0] == rightFace[0][2] && frontFace[0][0] == backFace[0][2])
         apply("U2 R U R' U' R' F R2 U' R' U' R U R' F'");
      else if (frontFace[0][0] == frontFace[0][2] && leftFace[0][0] == rightFace[0][2])
         apply("U R U R' U' R' F R2 U' R' U' R U R' F'");
      
      else
         return;
   }
   
   
   void solvePLLEdges()
   //Slightly more difficult as I could not use 'M' moves which are most common for these PLL states

   {
      if (frontFace[0][1] == frontFace[0][0] && backFace[0][1] == backFace[0][0])
         return;
       
       // Accounts for all the values, might be overkill but it was to fix a previous error  
      else if (frontFace[0][1] == backFace[0][0] && backFace[0][1] == frontFace[0][0] &&
               leftFace[0][1] == rightFace[0][0] && rightFace[0][1] == leftFace[0][0]) 
         apply("R2 U2 R U2 R2 U2 R2 U2 R U2 R2");
         
      else if (frontFace[0][1] == rightFace[0][0] && backFace[0][1] == backFace[0][0])
         apply("R U' R U R U R U' R' U' R2");
      else if (backFace[0][1] == leftFace[0][0] && rightFace[0][1] == rightFace[0][0])
         apply("U' R U' R U R U R U' R' U' R2");
      else if (frontFace[0][1] == frontFace[0][0] && leftFace[0][1] == rightFace[0][0])
         apply("U2 R U' R U R U R U' R' U' R2");
      else if (frontFace[0][1] == rightFace[0][0] && rightFace[0][1] == backFace[0][0])
         apply("U R U' R U R U R U' R' U' R2");
      
      else if (frontFace[0][1] == leftFace[0][0] && backFace[0][1] == backFace[0][0])
         apply("R2 U R U R' U' R' U' R' U R'");
      else if (frontFace[0][1] == leftFace[0][0] && leftFace[0][1] == backFace[0][0])
         apply("U' R2 U R U R' U' R' U' R' U R'");
      else if (frontFace[0][1] == frontFace[0][0] && rightFace[0][1] == leftFace[0][0])
         apply("U2 R2 U R U R' U' R' U' R' U R'");
      else if (backFace[0][1] == rightFace[0][0] && leftFace[0][1] == leftFace[0][0])
         apply("U R2 U R U R' U' R' U' R' U R'");
         
      else if (frontFace[0][1] == leftFace[0][0] && leftFace[0][1] == frontFace[0][0])
         apply("U R U R' U R' U' R' U R U' R' U' R2 U R");
      else if (frontFace[0][1] == rightFace[0][0] && rightFace[0][1] == frontFace[0][0])
         apply("R U R' U R' U' R' U R U' R' U' R2 U R");
      
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
      if (frontFace[0][1] == frontFace[1][1])
         return;
      else 
      {
         while (frontFace[0][1] != frontFace[1][1]) // Repeats until condition is true (Shortens code a little bit)
            apply("U");
      }
   }  
         
         
   void printSolution() // Shows the solution in moves and its length
   {
      System.out.println("Solution length: " + moveHistory.size());
      System.out.println("Solution: " + String.join(" ", moveHistory));
   }         
         
   void solve() // Combines all the steps needed to solve the cube
   {
     moveHistory.clear(); // So the scramble does not affect the solve move history
     solveWhiteCross();
     solveF2L();
     solveOLL();
     solvePLL();
   }     

   
         
      
   void programSolvesCube() // Computer solves the (already scrambled) cube
   {
      long start = System.nanoTime();
      solve();
      long end = System.nanoTime();
   
      System.out.println("\nEnd Result:");
      printCube();
   
      if (isCubeSolved())
         System.out.printf("Solved in %.6f seconds%n", (end - start) / 1000000000.0);
      else
         System.out.println("Solver failed to reach a solved state.");

      printMoveHistory();
   }
   
   
   void youSolveCube() // User solves interactively until solved or they quit
   {
      long start = System.nanoTime();         // timer starts once, before their first move
      Scanner scanner = new Scanner(System.in);
      String input = "";
   
      while (!isCubeSolved() && !input.equalsIgnoreCase("Exit"))
      {
         System.out.println("\nIf you want a visualization of where the pieces are: Type \"Scan\"");
         System.out.println("Enter your moves (space separated), or type \"Exit\" to stop:");
         input = scanner.nextLine().trim();
   
         if (input.equalsIgnoreCase("Exit"))
            break; 
         else if (input.equalsIgnoreCase("Scan"))
         {
            reportCubeState();
            continue;
         }
   
         apply(input.split(" "));
         System.out.println();
         printCube();
      }
   
      long end = System.nanoTime();
      double seconds = (end - start) / 1000000000.0;
   
      if (isCubeSolved())
      {
         System.out.println("Congrats on solving the cube by yourself!");
         System.out.printf("Solved in %.6f seconds%n", seconds);
      }
      else
         System.out.println("\nSession ended before solving.");
   
      printMoveHistory();
   }
         
   void combinedOptionsRun() // Scramble, then choose: computer solves or you solve
   // Only have to call this get full procedure
   {
      String scramble = scrambleTheCube();
      if (scramble == null)
         return;
      
      else
      {
         Scanner scanner = new Scanner(System.in);
         System.out.println("Do you want the program to solve the cube for you? (Type: \"Yes\" or \"No\")");
         String input = scanner.nextLine().trim();
      
         if (input.equalsIgnoreCase("Yes"))
            programSolvesCube();
         else if (input.equalsIgnoreCase("No"))
            youSolveCube();
         else
            System.out.println("Run ended: Next time, please type either \"Yes\" or \"No\".");
      }  
   }
}
 
