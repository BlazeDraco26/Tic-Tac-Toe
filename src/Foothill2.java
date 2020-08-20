import java.util.Scanner;

public class Foothill2
{
   private static Player2 DEFAULT_Player2;
   private static final int BOARD_LENGTH = 3;
   private static final int BOARD_WIDTH = 3;
   private static final boolean KEEP_LOOPING = true;
   private static final String HORIZONTAL_LINE = "--------------";
   private static final String VERTICAL_DIVIDER = " | ";
   
   public static void main(String[] args) {
      Player2[][] board = new Player2[BOARD_LENGTH][BOARD_WIDTH];
      DEFAULT_Player2 = new Player2(' ');
      char symbol1 = askUserForSymbol();
      char symbol2 = askUserForSymbol();
      resetBoard(board, DEFAULT_Player2);
      displayBoard(board);
      Player2 Player21 = new Player2(symbol1);
      Player2 Player22 = new Player2(symbol2);
      boolean winCondition = false;
      boolean drawCondition = false;
      for (int i = 0; i <= 5; i++) {
         makeAMove(board, Player21);
         displayBoard(board);
         winCondition = isWon(board, Player21);
         if (winCondition) {
            System.out.println("Player2 " + Player21.getSymbol() + " is the winner.");
            break;
         }
         drawCondition = isDraw(board);
         if (drawCondition) {
            System.out.println("The game is a draw.");
            break;
         }
         makeAMove(board, Player22);
         displayBoard(board);
         winCondition = isWon(board, Player22);
         if (winCondition) {
            System.out.println("Player2 " + Player22.getSymbol() + " is the winner.");
            break;
         }
         drawCondition = isDraw(board);
         if (drawCondition) {
            System.out.println("The game is a draw.");
            break;
         }
      }
   }
   
   public static char askUserForSymbol() {
      Scanner getSymbol = new Scanner(System.in);
      String userCharacter;
      char symbol = ' ';
      System.out.println("What is the symbol for a Player2?");
      while (KEEP_LOOPING) {
         userCharacter = getSymbol.nextLine();
         symbol = userCharacter.charAt(0);
         if (symbol >= 'A' && symbol <= 'Z') {
            break;
         } else {
            System.out.println("Sorry that is no good, choose a capital letter.  Try again:");
         }
      }
      return symbol;
   }
   
   public static void resetBoard(Player2[][] board, Player2 defaultPlayer2)
   {
      for (int i = 0; i < BOARD_LENGTH; i++) {
         for (int j = 0; j < BOARD_WIDTH; j++) {
            board[i][j] = defaultPlayer2;
         }
      }
   }
   public static void makeAMove(Player2[][] board, Player2 Player2)
   {
      Scanner makeAMove = new Scanner(System.in);
      int userRow, userColumn;
      while (KEEP_LOOPING) {
         System.out.println("Enter a row to pick your space.");
         userRow = makeAMove.nextInt();
         System.out.println("Enter a column to pick your space.");
         userColumn = makeAMove.nextInt();
         if (board[userRow][userColumn] == DEFAULT_Player2) {
            board[userRow][userColumn] = Player2;
            break;
         }    
      }
   }
   
   public static void displayBoard(Player2[][] board) {
      for (int i = 0; i < BOARD_LENGTH; i++) {
         System.out.println(HORIZONTAL_LINE);
         for (int j = 0; j < BOARD_WIDTH; j++) {
            System.out.print(VERTICAL_DIVIDER + board[i][j].getSymbol());
         }
         System.out.println(VERTICAL_DIVIDER);
      }
      System.out.println(HORIZONTAL_LINE);
  /*    System.out.println(HORIZONTAL_LINE);
      System.out.println(VERTICAL_DIVIDER + board[0][0].getSymbol() + VERTICAL_DIVIDER + board[]);
      System.out.println(HORIZONTAL_LINE);
      System.out.println();
      System.out.println(HORIZONTAL_LINE);
      System.out.println();
      System.out.println(HORIZONTAL_LINE); */
   }
   
   public static boolean isWon(Player2[][] board, Player2 Player2) {
      boolean won = false;
      if (board[0][0] == Player2 && board [1][1] == Player2 && board [2][2] == Player2) {
         return true;
      } else if (board[0][2] == Player2 && board [1][1] == Player2 && board [2][0] == Player2) {
         return true;
      }
      for (int i = 0; i < BOARD_LENGTH; i++) {
         if (board[i][0] == Player2 && board[i][1] == Player2 && board[i][2] == Player2) {
            won = true;
            break;
         } else if (board[0][i] == Player2 && board[1][i] == Player2 && board[2][i] == Player2) {
            won = true;
            break;
         } 
      }
      return won;
   }
   
   public static boolean isWon2(Player2[][] board, Player2 Player2) {
      boolean won = false;
      if (board[0][0] == Player2 && board [1][1] == Player2 && board [2][2] == Player2) {
         won = true;
      } else if (board[0][2] == Player2 && board [1][1] == Player2 && board [2][0] == Player2) {
         won = true;
      }
      for (int i = 0; !won && i < BOARD_LENGTH; i++) {
         if (board[i][0] == Player2 && board[i][1] == Player2 && board[i][2] == Player2) {
            won = true;
         } else if (board[0][i] == Player2 && board[1][i] == Player2 && board[2][i] == Player2) {
            won = true;
         } 
      }
      return won;
   }
   
   public static boolean isDraw(Player2[][] board)
   {
      for (int i = 0; i < BOARD_LENGTH; i++) {
         for (int j = 0; j < BOARD_WIDTH; j++) {
            if (board[i][j] == DEFAULT_Player2) {
               return false;
            }
         }
      }
      return true;
   }
}

 

/* public static void makeAMove2(Player2[][] board, Player2 Player2)
{
   Scanner makeAMove = new Scanner(System.in);
   int userRow, userColumn;
   boolean isValidMove = false;
   do {
      System.out.println("Enter a row to pick your space.");
      userRow = makeAMove.nextInt();
      System.out.println("Enter a column to pick your space.");
      userColumn = makeAMove.nextInt();
      isValidMove = board[userRow][userColumn] == defaultPlayer2;
   } while (!isValidMove);
   board[userRow][userColumn] = Player2; 
}  */
class Player2
{
   public static final char DEFAULT_SYMBOL = ' ';
   public static final int DEFAULT_WINS = 0;
   
   private char symbol;
   private int wins;
   public static final int MAX_WINS = 5;
   
   //constructors
   
   public Player2() {
      symbol = DEFAULT_SYMBOL;
      wins = DEFAULT_WINS;
   }
   
   public Player2(char symbol) {
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

