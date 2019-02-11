public class QueenBoard{
  private int[][]board;

  public QueenBoard(int size){
    board = new int[size][size];//board is represented by 2D array
  }

  private boolean addQueen(int r, int c){
    if(board[r][c] != 0){
      return false;//cannot add a queen to a threatened square or a square that already contains a queen
    }
    board[r][c] = -1;//queen represented by -1
    for (int i = 1; i < board.length - c; i++){//only need one for loop to loop through the possible moves
      board[r][c + i] += 1;//squares to the right are threatened

      if (r - i >= 0){//prevent exception, will not go out of bounds
        board[r - i][c + i] += 1;//squares diagonally to the upper left are threatened, add 1
      }

      if (r + i < board.length){//prevent exception, will not go out of bounds
        board[r + i][c + i] += 1;//squares diagonally to the bottom right are threatened, add 1
      }
    }
    return true;
  }

  private boolean removeQueen(int r, int c){
    if(board[r][c] != -1){
      return false;//cannot remove a queen from a square that does not contain a queen
    }
    board[r][c] = 0;//remove the queen. square is now empty
    for (int i = 1; i < board.length - c; i++){//only need one for loop to loop through the possible moves
      board[r][c + i] -= 1;//squares to the right are no longer threatened by removed queen, subtract 1

      if (r - i >= 0){//prevent exception, will not go out of bounds
        board[r - i][c + i] -= 1;//squares diagonally to the upper left are no longer threatened by removed queen, subtract 1
      }

      if (r + i < board.length){//prevent exception, will not go out of bounds
        board[r + i][c + i] -= 1;//squares diagonally to the bottom right are no longer threatened by removed queen, substract 1
      }
    }
    return true;
  }

  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _

  *_ _ _ Q

  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){
    String output = "";
    for(int row = 0; row < board.length; row++){
      for(int col = 0; col < board[row].length; col++){
        if(board[row][col] == -1){
          output += "Q";
        } else{
          output += "_";
        }
        // output += board[row][col];
        output += " ";
      }
      output += "\n";
    }
    return output;
  }

  public boolean isEmpty(){
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[r].length; c++){
        if (board[r][c] != 0){
          return false;
        }
      }
    }
    return true;
  }

  //fills the board with all 0's
  public void clear(){
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[r].length; c++){
        board[r][c] = 0;
      }
    }
  }

  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;

  *        true when the board is solveable, and leaves the board in a solved state

  *@throws IllegalStateException when the board starts with any non-zero value

  */
  public boolean solve(){
    if(!(isEmpty())){
      throw new IllegalStateException();
    }
    // return solveHelper(0,0);//call helper method
    return solveHelper(0);
  }


  public boolean solveHelper(int col){
    if (col >= board.length){
      return true;//base case. reached end of board
    }
    for(int row = 0; row < board.length; row++){//loop through the rows
      if(addQueen(row, col)){//if queen can be added
        if(solveHelper(col + 1)){
          return true;//try adding to next column
        }
        removeQueen(row, col);//backtracking: if cannot add to next column, remove queen and try again
      }
    }
    // System.out.println(Text.go(1,1));
    // System.out.println(this);Text.wait(50); //adjust this delay
    return false;//board cannot be solved if you looped through every row and it doesn't work
  }


  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    if(!(isEmpty())){
      throw new IllegalStateException();
    }
    int count = countHelper(0);//stores how many solutions there are
    clear();//clear board. board is filled with all 0's
    return count;
  }

  public int countHelper(int col){
    if(col >= board.length){
      return 1;//made it to end of board. one solution
    }
    int count = 0;
    for(int row = 0; row < board.length; row++){//loop through the rows
      if(addQueen(row, col)){//if you added a queen
        count += countHelper(col + 1);//recursive step. moving onto next column
        removeQueen(row, col);//removes queen after recursive call
      }
    }
    return count;//after every position has been tried
  }


}
