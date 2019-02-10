public class Driver{
  public static void main(String[] args) {
    QueenBoard board = new QueenBoard(4);
    board.addQueen(0,0);
    System.out.println(board);
    board.addQueen(1,0);
    System.out.println(board);
    board.removeQueen(1,0);
    System.out.println(board);
  }
}
