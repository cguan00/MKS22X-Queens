public class QueenBoard{
  private int[][]board;

  public QueenBoard(int size){
    board = new int[size][size];
    for(int row = 0; row < board.length; row++){
      for(int col = 0; col < board[row].length; col++){
        board[row][col] = 0;//fill the board up with 0's because it's empty
      }
    }
    // board[0][0] = -1;//-1 represents the Queen. place Queen in upper left hand box
    addQueen(0,0);
  }

  private boolean addQueen(int r, int c){
    board[r][c] = -1;//place the Queen down. Queen represented by -1

    //check boxed horizontally to the right
    for(int col = c; col < board[r].length; col++){
      board[r][col] += 1;
    }

    for(int row1 = r; row1 < board.length; row1++){
      for(int col1 = c; col1 < board[row1].length; col1++){
        board[row1][col1] += 1;
      }
    }
    return true;
  }


  private boolean removeQueen(int r, int c){
    board[r][c] = 0;
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
        if(board[row][col] == 0){
          output += "_";
        }
        if(board[row][col] == -1){
          output += "Q";
        }
        if(board[row][col] > 0){
          output += board[row][col];
        }
        output += " ";
      }
      output += "\n";
    }
    return output;
  }



  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;

  *        true when the board is solveable, and leaves the board in a solved state

  *@throws IllegalStateException when the board starts with any non-zero value

  */
  public boolean solve(){
    return true;
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    return 0;
  }



}
