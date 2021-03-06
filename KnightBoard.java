public class KnightBoard{

  private int[][] board; //The board returned
  private int rlength; //Row length
  private int clength; //Column length
  private int[] moves = new int[]{1,2,1,-2,-1,2,-1,-2,2,1,2,-1,-2,1,-2,-1};

  //@throws IllegalArgumentException when either parameter is <= 0.
  //initialize the board to the correct size and make them all 0's
  public KnightBoard(int startingRows, int startingCols){
    //If either starting rows or starting cols is less than or equal to zero,
    //throw an illegal argument exception
    if (startingRows <= 0 || startingCols <= 0){
      throw new IllegalArgumentException("StartingRows and StartingCols should both be positive numbers");
    }
    //Initialize the board to the correct sizes and store the sizes for future use
    board = new int[startingRows][startingCols];
    rlength = startingRows;
    clength = startingCols;
    //Fill the empty board in with 0's
    for (int row = 0; row < startingRows; row ++){
      for (int column = 0; column < startingCols; column ++){
        board[row][column] = 0;
      }
    }
  }


  //Blank boards displays 0's as undrscores           1  2 15  6
  //Single spaces between numbers, but                3  4  7 11
  //leading spaces on single digit numbers            8  9 10 12
  //For two digit numbers, put a leading space       13 14  5 16
  public String toString(){
    String ans = "";
    //If the board is blank, return a string with _'s in place of the 0's
    if (isBlank()){
      for (int row = 0; row < rlength; row ++){
        for (int column = 0; column < clength; column ++){
          ans += "_ ";
        }
        ans += "\n";
      }
    }
    //If not, run through the array list and return the numbers at each box
    else{
      for (int row = 0; row < rlength; row ++){
        for (int column = 0; column < clength; column ++){
          if (board[row][column] < 10){
            ans += " " + board[row][column] + " ";
          }
          else{
            ans += board[row][column] + " ";
          }
        }
        ans += "\n";
      }
    }
    return ans;
  }

  private boolean isBlank(){
    //Loop through the board to check if it is empty
    for (int row = 0; row < rlength; row ++){
      for (int column = 0; column < clength; column ++){
        if (board[row][column] != 0){
          return false;
        }
      }
    }
    return true;
  }

  //Modifies the board by labeling the moves from 1 (at startingRow, startingCol)
  //up to the area of the board in proper knight move steps
  //@throws IllegalStateException when the board contains non-zero values
  //@throws IllegalArgumentException when either parameter is negative or out of bounds
  //@returns true when the board is solvable from the specified starting position
  public boolean solve(int startingRow, int startingCol){
    //If the board isn't blank, throw an illegal state exception
    if (!isBlank()){
      throw new IllegalStateException("The board must be empty to solve");
    }
    //If the starting row and starting column is out of bound, throw an illegal
    //argument exception
    if (startingRow < 0 || startingCol < 0 || startingRow >= rlength || startingCol >= clength){
      throw new IllegalArgumentException("StartingRow or StartingCol is out of bounds");
    }
    return solveH(startingRow, startingCol, 1);
  }

  //@throws IllegalStateException when the board contains non-zero values
  //@throws IllegalArgumentException when either parameter is negative or out of bounds
  //@returns the number of solutions from the starting position specified
  public int countSolutions(int startingRow, int startingCol){
    //If the board isn't blank, throw an illegal state exception
    if (!isBlank()){
      throw new IllegalStateException("The board must be empty to solve");
    }
    //If the starting row and starting column is out of bound, throw an illegal
    //argument exception
    if (startingRow < 0 || startingCol < 0 || startingRow >= rlength || startingCol >= clength){
      throw new IllegalArgumentException("StartingRow or StartingCol is out of bounds");
    }
    return countH(startingRow, startingCol, 1);
  }

  public int countH(int row, int col, int level){
    int ans = 0;
    //If it is possible to add a knight at this posiion....
    if (add(row, col, level)) {
      //If the level is equal to the number of squares on the board, remove the
      //added knight and return 1 (base case)
      if (level == (rlength * clength)){
        remove(row, col);
        return 1;
      }
      else {
        //Loop through all the possible moves (16 is the total possible amount
        //of moves, counting both column and row, it was just easier to type out than
        //moves.size() :P), add all the possible moves to the ans
        for (int i = 0; i < 16; i = i + 2) {
          ans += countH(row + moves[i], col + moves[i + 1], level + 1);
        }
      }
      //Remove the added knight
      remove(row, col);
    }
    return ans;
  }
  //Suggestion
  //Level is the number of the knight
  private boolean solveH(int row, int col, int level){
    //If the level is greater the the number of possible squares in the board,
    //then there is a solution
    if (level > (rlength * clength)){
      return true;
    }
    //If it is possible to add a knight at the spot...
    if (add(row,col,level)){
      //Loop through all the possible moves (16 is the total possible amount
      //of moves, counting both column and row, it was just easier to type out than
      //moves.size() :P), and if one of them runs all the way to the end, return true
      for (int i = 0; i < 16; i = i + 2){
        if (solveH(row + moves[i], col + moves[i + 1],level + 1)){
          return true;
        }
      }
      //If not, remove the knight from that spot and return false
      remove(row, col);
    }
    return false;
  }

  public boolean add(int row, int col, int level){
    //If the row and column is within the board...
    if (row >= 0 && row < rlength && col >= 0 && col < clength){
      //Check if its a valid space to place a knight and if so,
      //change the value at the spot to the current level
      if (board[row][col] == 0){
        board[row][col] = level;
        return true;
      }
      else{
        return false;
      }
    }
    return false;
  }

  public boolean remove(int row, int col){
    //If the row and column is within the board...
    if (row >= 0 && row < rlength && col >= 0 && col < clength){
      //Check if there is a knight at the spot, and if so, replace
      //the value at the spot with a 0, removing the knight
      if (board[row][col] !=0){
        board[row][col] = 0;
        return true;
      }
      else{
        return false;
      }
    }
    return false;
  }
}
