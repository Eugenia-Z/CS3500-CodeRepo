package cs3500.marblesolitaire.Impl;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import java.util.ArrayList;
import java.util.List;

public class EnglishSolitaireModel implements MarbleSolitaireModel {
  private Integer armThickness;
  private List<List<SlotState>> board;
  private Integer score = 0;

  /**
   * Constructor
   */
  public EnglishSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * Constructor
   *
   * @param sRow Integer, row value of the empty slot
   * @param sCol Integer, column value of the empty slot
   */
  public EnglishSolitaireModel(Integer sRow, Integer sCol) {
    this(3, sRow, sCol);
  }

  /**
   * Constructor
   *
   * @param armThickness Integer, the number of marbles in the top row
   */
  public EnglishSolitaireModel(Integer armThickness) {
    this(armThickness, armThickness + armThickness / 2 - 1, armThickness + armThickness / 2 - 1);
  }


  /**
   * 1 represents that there is a marble on the slot. 0 represents the slot is empty.
   * Integer.MAX_VALUE represents inaccessible slots.
   *
   * @param armThickness Integer, the number of marbles in the top row
   * @param sRow         Integer, row value of the empty slot
   * @param sCol         Integer, column value of the empty slot
   */
  public EnglishSolitaireModel(Integer armThickness, Integer sRow, Integer sCol) {
    if (armThickness % 2 != 1 || armThickness < 0) {
      throw new IllegalArgumentException("Arm thickness must be a positive odd number.");
    }
    if (!isValidPosition(armThickness, sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
    this.armThickness = armThickness;
    Integer length = 3 * armThickness - 2;
    this.board = new ArrayList<>();
    for (int i = 0; i < length; ++i) {
      List<SlotState> row = new ArrayList<>();
      for (int j = 0; j < length; ++j) {
        row.add(onFourCorners(armThickness, i, j) ? SlotState.Invalid : SlotState.Marble);
      }
      this.board.add(row);
    }
    this.board.get(sRow).set(sCol, SlotState.Empty);
  }

  /**
   * check if the empty slot is valid
   *
   * @param armThickness Integer, the number of marbles in the top row
   * @param sRow         Integer, row value of the empty slot
   * @param sCol         Integer, column value of the empty slot
   * @return Boolean
   */
  private Boolean isValidPosition(Integer armThickness, Integer sRow, Integer sCol) {
    Integer length = 3 * armThickness - 2;
    return (sRow >= 0 && sRow < length && sCol >= 0 && sCol < length) && !onFourCorners(
            armThickness, sRow, sCol);
  }

  /**
   * check if the empty slot is on the four corners of the board
   *
   * @param armThickness Integer, the number of marbles in the top row
   * @param sRow         Integer, row value of the empty slot
   * @param sCol         Integer, column value of the empty slot
   * @return Boolean
   */
  private Boolean onFourCorners(Integer armThickness, Integer sRow, Integer sCol) {
    return sRow < armThickness - 1 && sCol < armThickness - 1
            || sRow < armThickness - 1 && sCol > 2 * armThickness - 2 ||
            sRow > 2 * armThickness - 2 && sCol < armThickness - 1
            || sRow > 2 * armThickness - 2 && sCol > 2 * armThickness - 2;
  }


  /**
   * move marbles after checking if the move is valid
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    //check if from and to positions are valid
    if (!isValidPosition(this.getArmThickness(), fromRow, fromCol) || !isValidPosition(this.getArmThickness(), toRow, toCol)) {
      throw new IllegalArgumentException("Invalid move: positions are out of bounds.");
    }

    //there is a marble at the specified "from" position.
    if (board.get(fromRow).get(fromCol) != SlotState.Marble) {
      throw new IllegalArgumentException("Invalid move: no marble at the 'from' position.");
    }

    //the "to" position is empty.
    if (board.get(toRow).get(toCol) != SlotState.Empty) {
      throw new IllegalArgumentException("Invalid move: 'to' position is not empty.");
    }

    //the "to" and "from" positions are exactly two positions away (horizontally or vertically).
    Integer rowDiff = Math.abs(fromRow - toRow);
    Integer colDiff = Math.abs(fromCol - toCol);
    if (!((rowDiff == 2 && colDiff == 0) || (rowDiff == 0 && colDiff == 2))) {
      throw new IllegalArgumentException("Invalid move: positions are not two positions away (horizontally or vertically).");
    }

    //there is a marble in the slot between the ''to" and ''from" positions.
    Integer middleRow = (fromRow + toRow) / 2;
    Integer middleCol = (fromCol + toCol) / 2;
    if (board.get(middleRow).get(middleCol) != SlotState.Marble) {
      throw new IllegalArgumentException("Invalid move: no marble in the slot between 'from' and 'to' positions.");
    }

    //set the board after moving
    board.get(fromRow).set(fromCol, SlotState.Empty);
    board.get(toRow).set(toCol, SlotState.Marble);
    board.get(middleRow).set(middleCol, SlotState.Empty);
    score += 1;
  }

  /**
   * check if there are any valid moves can be made
   *
   * @return
   */
  @Override
  public boolean isGameOver() {
    int[][] directions = {
            {-2, 0},  // Up
            {2, 0},   // Down
            {0, -2},  // Left
            {0, 2}    // Right
    };
    for (int row = 0; row < this.getBoardSize(); ++row) {
      for (int col = 0; col < this.getBoardSize(); ++col) {
        if (getSlotAt(row, col) == SlotState.Marble) {
          for (int[] direction : directions) {
            Integer newRow = row + direction[0];
            Integer newCol = col + direction[1];
            Integer middleRow = (row + newRow) / 2;
            Integer middleCol = (col + newCol) / 2;
            if (isValidPosition(this.getArmThickness(), newRow, newCol) &&
                    isValidPosition(this.getArmThickness(), middleRow, middleCol) &&
                    getSlotAt(newRow, newCol) == SlotState.Empty &&
                    getSlotAt(middleRow, middleCol) == SlotState.Marble) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  /**
   * get board size
   *
   * @return int
   */
  @Override
  public int getBoardSize() {
    return 3 * this.getArmThickness() - 2;
  }

  /**
   * return the state of the given slot
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return Enum, SlotState
   * @throws IllegalArgumentException
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (onFourCorners(this.getArmThickness(), row, col) || isValidPosition(this.getArmThickness(), row, col)) {
      return this.board.get(row).get(col);
    } else {
      throw new IllegalArgumentException("positions are out of bounds.");
    }
  }

  @Override
  public int getScore() {
    return score;
  }

  public Integer getArmThickness() {
    return armThickness;
  }

  public List<List<SlotState>> getBoard() {
    return board;
  }

  @Override
  public String toString() {
    return "EnglishSolitaireModel{" +
            "armThickness=" + armThickness +
            ", board=" + board +
            '}';
  }
}
