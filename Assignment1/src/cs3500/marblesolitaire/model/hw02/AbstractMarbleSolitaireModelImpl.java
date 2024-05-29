package cs3500.marblesolitaire.model.hw02;

import java.util.Scanner;

import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

public abstract class AbstractMarbleSolitaireModelImpl implements
        MarbleSolitaireModel {
  protected SlotState[][] board;
  protected int score;
  protected int boardDimension;

  public AbstractMarbleSolitaireModelImpl(int armThickness,int emptyRow, int
          emptyCol) throws
          IllegalArgumentException {
    initializeBoard(armThickness,emptyRow, emptyCol);
  }

  @Override
  public int getBoardSize() {
    return this.boardDimension;
  }

  @Override
  public SlotState getSlotAt(int row,int col) throws IllegalArgumentException {
    if ((row<0) || (row>=boardDimension) || (col<0) || (col>=boardDimension)) {
      throw new IllegalArgumentException("Row or column are beyond board size");
    }
    return this.board[row][col];
  }

  /**
   * Move a peg from a given position to a given position. A move is valid only
   * if all the following conditions are satisfied: <ol> <li>The from position
   * is a valid position on the board. </li> <li>The two position is a valid
   * position on the board. </li> <li>The from and two positions are either in
   * the same column or the same row.</li> <li>The from and two positions are
   * exactly two places apart.</li> <li>The from position has a peg.</li>
   * <li>The middle position (between from and to) has a peg.</li> <li>The to
   * position is empty.</li> </ol>
   *
   * @param fromRow the row number of the position to be moved from (starts at
   *                0)
   * @param fromCol the column number of the position to be moved from (starts
   *                at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at
   *                0)
   */

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    Position to, from;
    from = new Position(fromRow, fromCol);
    to = new Position(toRow, toCol);

    checkValidity(from, to);

    makeMove(from, to);
  }


  @Override
  public boolean isGameOver() {
    int []incr = getValidMoveIncrements();
    //check if each occupied peg can move
    for (int i = 0; i < boardDimension; i++) {
      for (int j = 0; j < boardDimension; j++) {
        if (board[i][j] == SlotState.Marble) {
          Position from = new Position(i, j);
          for (int k = 0; k < incr.length; k += 2) {
            Position to = new Position(i + incr[k], j + incr[k + 1]);
            if ((isValid(from)) && (isValid(to)) && (canMove(from, to) ==
                                                     0)) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  @Override
  public int getScore() {
    return score;
  }

  private void makeMove(Position from, Position to) {
    Position mid = new Position((from.row + to.row) / 2, (from.col + to.col) / 2);

    board[to.row][to.col] = SlotState.Marble;
    board[mid.row][mid.col] = SlotState.Empty;
    board[from.row][from.col] = SlotState.Empty;
    score--;

  }


  private void checkValidity(Position from, Position to) throws
          IllegalArgumentException {
    if (!isValid(from)) {
      throw new IllegalArgumentException("From position is not on board");
    }

    if (!isValid(to)) {
      throw new IllegalArgumentException("To position is not on board");
    }

    int code = canMove(from, to);
    processValidityErrorCode(code);

  }

  protected void processValidityErrorCode(int code) {
    switch (code) {
      case 1:
        throw new IllegalArgumentException("From position does not have a peg");
      case 2:
        throw new IllegalArgumentException("To position is not empty");
      case 3:
        throw new IllegalArgumentException("Middle position does not "
                                           + "have a peg");
    }
  }


  protected int canMove(Position from, Position to) {
    Position mid = new Position((from.row + to.row) / 2, (from.col + to.col) / 2);

    if (board[from.row][from.col] != SlotState.Marble) {
      return 1;
    }

    if (board[to.row][to.col] != SlotState.Empty) {
      return 2;
    }

    if (board[mid.row][mid.col] != SlotState.Marble) {
      return 3;
    }
    return 0; //OK
  }

  protected boolean isValid(Position p) {
    return !((p.row < 0) || (p.row >=boardDimension)
             || (p.col < 0) || (p.col >=boardDimension));
  }


  /**
   * Produce and return the set of valid row and column increments from the
   * current position to/from a marble can move from/to a specific position
   * @return
   */
  protected abstract int[] getValidMoveIncrements();



  protected abstract void initializeBoard(int boardDimension,int emptyRow, int
          emptyCol) throws
          IllegalArgumentException;


  protected static void playGame(MarbleSolitaireModel model) {
    Scanner sc = new Scanner(System.in);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    while (!model.isGameOver()) {
      System.out.println(view.toString());
      System.out.println("Score: " + model.getScore());

      System.out.print("Enter position to move from (row,col): ");
      int fromRow = sc.nextInt();
      int fromCol = sc.nextInt();
      System.out.print("Enter position to move to (row,col): ");
      int toRow = sc.nextInt();
      int toCol = sc.nextInt();

      try {
        model.move(fromRow, fromCol, toRow, toCol);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid move: play again");
      }
    }

    System.out.println("Game over!");
    System.out.println(view.toString());
    System.out.println("Score: " + model.getScore());
  }
}
