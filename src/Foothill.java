// Lab #9
// Jordan Cheng

import java.util.Scanner;

// client
public class Foothill
{
   // define constants
   private static Player DEFAULT_PLAYER;
   private static final int NUM_PLAYERS = 2;
   private static final int BOARD_LENGTH = 3;
   private static final int BOARD_WIDTH = 3;
   private static final int BOARD_MIN = 0;
   private static final boolean KEEP_LOOPING = true;
   private static final String HORIZONTAL_LINE = "--------------";
   private static final String VERTICAL_DIVIDER = " | ";
   
   public static void main(String[] args) {
      
      // create board and player objects
      Player[][] board = new Player[BOARD_LENGTH][BOARD_WIDTH];
      DEFAULT_PLAYER = new Player(' ');
      Player[] player = new Player[NUM_PLAYERS];
      
      for (int b = 0; b < NUM_PLAYERS; b++) {
         player[b%2] = new Player(askUserForSymbol());
      }
      
      boolean winCondition = false;
      boolean drawCondition = false;
      
      // 5 games are played
      for (int count = 1; count <= 5; count++) {
         System.out.println("Game " + count + " starting:");
         resetBoard(board, DEFAULT_PLAYER);
         displayBoard(board);
         for (int i = 0; i < BOARD_LENGTH * BOARD_WIDTH; i++) {
            makeAMove(board, player[i%2]);
            displayBoard(board);
            winCondition = isWon(board, player[i%2]);
            if (winCondition) {
               System.out.println("Player " + player[i%2].getSymbol() + 
                     " is the winner.");
               player[i%2].addWin();
               break;
            }
            drawCondition = isDraw(board);
            if (drawCondition) {
               System.out.println("The game is a draw.");
               break;
            }
         }
      }
      for (int a = 0; a < NUM_PLAYERS; a++) {
         System.out.println("Player " + player[a%2].getSymbol() + " won " + 
               player[a%2].getWins() + " times.");
      }
   }
   
   // obtain player character
   public static char askUserForSymbol() {
      Scanner getSymbol = new Scanner(System.in);
      String userCharacter;
      char symbol = ' ';
      System.out.println("What is the symbol for a player?");
      while (KEEP_LOOPING) {
         userCharacter = getSymbol.nextLine();
         symbol = userCharacter.charAt(0);
         if (symbol >= 'A' && symbol <= 'Z') {
            break;
         } else {
            System.out.println("Sorry that is no good, choose a capital letter."
                  + "  Try again:");
         }
      }
      return symbol;
   }
   
   // reset the board
   public static void resetBoard(Player[][] board, Player defaultPlayer)
   {
      for (int i = 0; i < BOARD_LENGTH; i++) {
         for (int j = 0; j < BOARD_WIDTH; j++) {
            board[i][j] = defaultPlayer;
         }
      }
   }
   
   // prompt players for move
   public static void makeAMove(Player[][] board, Player player)
   {
      Scanner makeAMove = new Scanner(System.in);
      int userRow, userColumn;
      while (KEEP_LOOPING) {
         do {
            System.out.println("Enter a row (0, 1, or 2) for " +
         player.getSymbol());
            userRow = makeAMove.nextInt();
         } while (userRow >= BOARD_LENGTH || userRow < BOARD_MIN);
         
         do {
            System.out.println("Enter a column (0, 1, or 2) for " + 
         player.getSymbol());
            userColumn = makeAMove.nextInt();
         } while (userColumn >= BOARD_WIDTH || userColumn < BOARD_MIN);
            
         if (board[userRow][userColumn] == DEFAULT_PLAYER) {
            board[userRow][userColumn] = player;
            break;
         } else {
            System.out.println("That space is not available, try again.");
         }
      }
      
   }
   
   // display the current status of the board
   public static void displayBoard(Player[][] board) {
      for (int i = 0; i < BOARD_LENGTH; i++) {
         System.out.println(HORIZONTAL_LINE);
         for (int j = 0; j < BOARD_WIDTH; j++) {
            System.out.print(VERTICAL_DIVIDER + board[i][j].getSymbol());
         }
         System.out.println(VERTICAL_DIVIDER);
      }
      System.out.println(HORIZONTAL_LINE);

   }
   
   // check if somebody won
   public static boolean isWon(Player[][] board, Player player) {
      boolean won = false;
      if (board[0][0] == player && board [1][1] == player && board [2][2] ==
            player) {
         return true;
      } else if (board[0][2] == player && board [1][1] == player && board [2][0]
            == player) {
         return true;
      }
      for (int i = 0; i < BOARD_LENGTH; i++) {
         if (board[i][0] == player && board[i][1] == player && board[i][2] ==
               player) {
            won = true;
            break;
         } else if (board[0][i] == player && board[1][i] == player &&
               board[2][i] == player) {
            won = true;
            break;
         } 
      }
      return won;
   }
   
   // check if the game is a draw
   public static boolean isDraw(Player[][] board)
   {
      for (int i = 0; i < BOARD_LENGTH; i++) {
         for (int j = 0; j < BOARD_WIDTH; j++) {
            if (board[i][j] == DEFAULT_PLAYER) {
               return false;
            }
         }
      }
      return true;
   }
}

// Player class
class Player
{
   public static final char DEFAULT_SYMBOL = ' ';
   public static final int DEFAULT_WINS = 0;
   
   private char symbol;
   private int wins;
   public static final int MAX_WINS = 5;
   
   //constructors
   
   public Player() {
      symbol = DEFAULT_SYMBOL;
      wins = DEFAULT_WINS;
   }
   
   public Player(char symbol) {
      setSymbol(symbol);
      wins = DEFAULT_WINS;
   }
   
   //accessors and mutators
   
   public char getSymbol() {
      return symbol;
   }
   public boolean setSymbol(char symbol) {
      if ((symbol >= 'A' && symbol <= 'Z') || symbol == ' ') {
         this.symbol = symbol;
         return true;
      } else {
         return false;
      }
   }
   
   public int getWins() {
      return wins;
   }
   
   public boolean addWin() {
      if (wins == MAX_WINS) {
         return false;
      } else {
         wins++;
         return true;
      }
   }
   
   public void resetWins() {
      wins = 0;
   }
}

/* -------------------------------- run -------------------------------------

What is the symbol for a player?
5
Sorry that is no good, choose a capital letter.  Try again:
asdf
Sorry that is no good, choose a capital letter.  Try again:
X
What is the symbol for a player?
O
Game 1 starting:
--------------
 |   |   |   | 
--------------
 |   |   |   | 
--------------
 |   |   |   | 
--------------
Enter a row (0, 1, or 2) for X
1
Enter a column (0, 1, or 2) for X
1
--------------
 |   |   |   | 
--------------
 |   | X |   | 
--------------
 |   |   |   | 
--------------
Enter a row (0, 1, or 2) for O
1
Enter a column (0, 1, or 2) for O
1
That space is not available, try again.
Enter a row (0, 1, or 2) for O
5
Enter a row (0, 1, or 2) for O
1
Enter a column (0, 1, or 2) for O
0
--------------
 |   |   |   | 
--------------
 | O | X |   | 
--------------
 |   |   |   | 
--------------
Enter a row (0, 1, or 2) for X
2
Enter a column (0, 1, or 2) for X
0
--------------
 |   |   |   | 
--------------
 | O | X |   | 
--------------
 | X |   |   | 
--------------
Enter a row (0, 1, or 2) for O
2
Enter a column (0, 1, or 2) for O
2
--------------
 |   |   |   | 
--------------
 | O | X |   | 
--------------
 | X |   | O | 
--------------
Enter a row (0, 1, or 2) for X
0
Enter a column (0, 1, or 2) for X
2
--------------
 |   |   | X | 
--------------
 | O | X |   | 
--------------
 | X |   | O | 
--------------
Player X is the winner.
Game 2 starting:
--------------
 |   |   |   | 
--------------
 |   |   |   | 
--------------
 |   |   |   | 
--------------
Enter a row (0, 1, or 2) for X
2
Enter a column (0, 1, or 2) for X
0
--------------
 |   |   |   | 
--------------
 |   |   |   | 
--------------
 | X |   |   | 
--------------
Enter a row (0, 1, or 2) for O
1
Enter a column (0, 1, or 2) for O
1
--------------
 |   |   |   | 
--------------
 |   | O |   | 
--------------
 | X |   |   | 
--------------
Enter a row (0, 1, or 2) for X
0
Enter a column (0, 1, or 2) for X
2
--------------
 |   |   | X | 
--------------
 |   | O |   | 
--------------
 | X |   |   | 
--------------
Enter a row (0, 1, or 2) for O
0
Enter a column (0, 1, or 2) for O
0
--------------
 | O |   | X | 
--------------
 |   | O |   | 
--------------
 | X |   |   | 
--------------
Enter a row (0, 1, or 2) for X
2
Enter a column (0, 1, or 2) for X
2
--------------
 | O |   | X | 
--------------
 |   | O |   | 
--------------
 | X |   | X | 
--------------
Enter a row (0, 1, or 2) for O
1
Enter a column (0, 1, or 2) for O
0
--------------
 | O |   | X | 
--------------
 | O | O |   | 
--------------
 | X |   | X | 
--------------
Enter a row (0, 1, or 2) for X
2
Enter a column (0, 1, or 2) for X
1
--------------
 | O |   | X | 
--------------
 | O | O |   | 
--------------
 | X | X | X | 
--------------
Player X is the winner.
Game 3 starting:
--------------
 |   |   |   | 
--------------
 |   |   |   | 
--------------
 |   |   |   | 
--------------
Enter a row (0, 1, or 2) for X
1
Enter a column (0, 1, or 2) for X
1
--------------
 |   |   |   | 
--------------
 |   | X |   | 
--------------
 |   |   |   | 
--------------
Enter a row (0, 1, or 2) for O
2
Enter a column (0, 1, or 2) for O
2
--------------
 |   |   |   | 
--------------
 |   | X |   | 
--------------
 |   |   | O | 
--------------
Enter a row (0, 1, or 2) for X
2
Enter a column (0, 1, or 2) for X
1
--------------
 |   |   |   | 
--------------
 |   | X |   | 
--------------
 |   | X | O | 
--------------
Enter a row (0, 1, or 2) for O
0
Enter a column (0, 1, or 2) for O
1
--------------
 |   | O |   | 
--------------
 |   | X |   | 
--------------
 |   | X | O | 
--------------
Enter a row (0, 1, or 2) for X
0
Enter a column (0, 1, or 2) for X
2
--------------
 |   | O | X | 
--------------
 |   | X |   | 
--------------
 |   | X | O | 
--------------
Enter a row (0, 1, or 2) for O
2
Enter a column (0, 1, or 2) for O
0
--------------
 |   | O | X | 
--------------
 |   | X |   | 
--------------
 | O | X | O | 
--------------
Enter a row (0, 1, or 2) for X
1
Enter a column (0, 1, or 2) for X
0
--------------
 |   | O | X | 
--------------
 | X | X |   | 
--------------
 | O | X | O | 
--------------
Enter a row (0, 1, or 2) for O
1
Enter a column (0, 1, or 2) for O
2
--------------
 |   | O | X | 
--------------
 | X | X | O | 
--------------
 | O | X | O | 
--------------
Enter a row (0, 1, or 2) for X
0
Enter a column (0, 1, or 2) for X
0
--------------
 | X | O | X | 
--------------
 | X | X | O | 
--------------
 | O | X | O | 
--------------
The game is a draw.
Game 4 starting:
--------------
 |   |   |   | 
--------------
 |   |   |   | 
--------------
 |   |   |   | 
--------------
Enter a row (0, 1, or 2) for X
1
Enter a column (0, 1, or 2) for X
2
--------------
 |   |   |   | 
--------------
 |   |   | X | 
--------------
 |   |   |   | 
--------------
Enter a row (0, 1, or 2) for O
1
Enter a column (0, 1, or 2) for O
1
--------------
 |   |   |   | 
--------------
 |   | O | X | 
--------------
 |   |   |   | 
--------------
Enter a row (0, 1, or 2) for X
0
2Enter a column (0, 1, or 2) for X

--------------
 |   |   | X | 
--------------
 |   | O | X | 
--------------
 |   |   |   | 
--------------
Enter a row (0, 1, or 2) for O
2
Enter a column (0, 1, or 2) for O
2
--------------
 |   |   | X | 
--------------
 |   | O | X | 
--------------
 |   |   | O | 
--------------
Enter a row (0, 1, or 2) for X
0
Enter a column (0, 1, or 2) for X
0
--------------
 | X |   | X | 
--------------
 |   | O | X | 
--------------
 |   |   | O | 
--------------
Enter a row (0, 1, or 2) for O
2
Enter a column (0, 1, or 2) for O
1
--------------
 | X |   | X | 
--------------
 |   | O | X | 
--------------
 |   | O | O | 
--------------
Enter a row (0, 1, or 2) for X
0
Enter a column (0, 1, or 2) for X
1
--------------
 | X | X | X | 
--------------
 |   | O | X | 
--------------
 |   | O | O | 
--------------
Player X is the winner.
Game 5 starting:
--------------
 |   |   |   | 
--------------
 |   |   |   | 
--------------
 |   |   |   | 
--------------
Enter a row (0, 1, or 2) for X
0
Enter a column (0, 1, or 2) for X
2
--------------
 |   |   | X | 
--------------
 |   |   |   | 
--------------
 |   |   |   | 
--------------
Enter a row (0, 1, or 2) for O
1
Enter a column (0, 1, or 2) for O
1
--------------
 |   |   | X | 
--------------
 |   | O |   | 
--------------
 |   |   |   | 
--------------
Enter a row (0, 1, or 2) for X
0
Enter a column (0, 1, or 2) for X
2
That space is not available, try again.
Enter a row (0, 1, or 2) for X
2
Enter a column (0, 1, or 2) for X
0
--------------
 |   |   | X | 
--------------
 |   | O |   | 
--------------
 | X |   |   | 
--------------
Enter a row (0, 1, or 2) for O
2
Enter a column (0, 1, or 2) for O
1
--------------
 |   |   | X | 
--------------
 |   | O |   | 
--------------
 | X | O |   | 
--------------
Enter a row (0, 1, or 2) for X
0
Enter a column (0, 1, or 2) for X
0
--------------
 | X |   | X | 
--------------
 |   | O |   | 
--------------
 | X | O |   | 
--------------
Enter a row (0, 1, or 2) for O
0
Enter a column (0, 1, or 2) for O
1
--------------
 | X | O | X | 
--------------
 |   | O |   | 
--------------
 | X | O |   | 
--------------
Player O is the winner.
Player X won 3 times.
Player O won 1 times.

--------------------------------------------------------------------------- */

