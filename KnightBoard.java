public class KnightBoard{
  //@throws IllegalArgumentException when either parameter is <= 0.
  //initialize the board to the correct size and make them all 0's
  public KnightBoard(int startingRows, int startingCols){

  }

  //Blank boards displays 0's as undrscores           1  2 15  6
  //Single spaces between numbers, but                3  4  7 11
  //leading spaces on single digit numbers            8  9 10 12
  //For two digit numbers, put a leading space       13 14  5 16
  public String toString(){

  }

  //Modifies the board by labeling the moves from 1 (at startingRow, startingCol)
  //up to the area of the board in proper knight move steps
  //@throws IllegalStateException when the board contains non-zero values
  //@throws IllegalArgumentException when either parameter is negative or out of bounds
  //@returns true when the board is solvable from the specified starting position
  public boolean solve(int startingRow, int startingCol){

  }

  //@throws IllegalStateException when the board contains non-zero values
  //@throws IllegalArgumentException when either parameter is negative or out of bounds
  //@returns the number of solutions from the starting position specified
  public int countSolutions(int startingRow, int startingCol){

  }

  //Suggestion
  //Level is the number of the knight
  private boolean solveH(int row, int col, int level){

  }
}