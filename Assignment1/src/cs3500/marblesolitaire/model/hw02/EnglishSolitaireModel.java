package cs3500.marblesolitaire.model.hw02;


public class EnglishSolitaireModel extends
        AbstractMarbleSolitaireModelImpl implements
        MarbleSolitaireModel {
  /**
   * Create a model of English Solitaire with a default arm thickness of 3, with the empty
   * position at (3,3)
   */
  public EnglishSolitaireModel() {
    super(3,3, 3);
  }

  /**
   * Create a model of English Solitaire with a default arm thickness of 3, with the empty
   * position as specified
   */
  public EnglishSolitaireModel(int emptyRow, int
          emptyCol) throws
          IllegalArgumentException {
    super(3,emptyRow, emptyCol);
  }

  /**
   * Create a model of English Solitaire with the specified arm thickness, with the empty
   * position in the middle of the board
   */
  public EnglishSolitaireModel(int armThickness) throws
          IllegalArgumentException {
    super(armThickness,(3*armThickness-2)/2,(3*armThickness-2)/2);
  }

  /**
   * Create a model of English Solitaire with the specified arm thickness and empty position
   * @param armThickness the arm thickness
   * @param emptyRow the row of the empty position
   * @param emptyCol the column of the empty position
   * @throws IllegalArgumentException
   */
  public EnglishSolitaireModel(int armThickness, int emptyRow, int
          emptyCol) throws
          IllegalArgumentException {
    super(armThickness,emptyRow, emptyCol);
  }



  protected int[] getValidMoveIncrements() {
    return new int[]{
            0, -2,//same row, from left
            0, 2, //same row, from right
            -2, 0, //same column, from above
            2, 0, //same column, from below
    };
  }

  protected void initializeBoard(int armThickness,int emptyRow, int emptyCol)
          throws
          IllegalArgumentException {
    if (armThickness%2==0) {
      throw new IllegalArgumentException("Board dimension should be an odd "
                                         + "number.");
    }
    this.boardDimension = 3*armThickness-2;

    if (((emptyRow < (armThickness-1)) || (emptyRow >= (2*armThickness-1)))
        && ((emptyCol < (armThickness-1)) || (emptyCol >= (2*armThickness-1)))) {
      throw new IllegalArgumentException("Invalid empty cell position "
                                         + "(" + emptyRow + "," + emptyCol + ")");
    }
    board = new SlotState[boardDimension][boardDimension];
    score = 0;
    for (int i = 0; i < boardDimension; i++) {
      for (int j = 0; j < boardDimension; j++) {
        if (((i < (armThickness-1)) || (i >= (2*armThickness-1)))
            && ((j < (armThickness-1)) || (j >= (2*armThickness-1)))) {
          board[i][j] = SlotState.Invalid;
        } else {
          board[i][j] = SlotState.Marble;
          score++;
        }
      }
    }
    board[emptyRow][emptyCol] = SlotState.Empty;
    score--;
  }

  @Override
  protected void processValidityErrorCode(int code) {
    switch (code) {
      case 4:
        throw new IllegalArgumentException("From and two positions "
                                           + "either do not share a "
                                           + "column or row, or they "
                                           + "are more than 2 positions"
                                           + " apart");
      default:
        super.processValidityErrorCode(code);
    }
  }

  @Override
  protected int canMove(Position from, Position to) {
    Position mid = new Position((from.row + to.row) / 2, (from.col + to.col) / 2);

    if (((from.row != to.row) && (from.col != to.col)) || ((Math.abs(from.row - to
            .row) + Math.abs(from.col - to.col)) != 2)) {
      return 4;
    }
    return super.canMove(from, to);
  }
}
