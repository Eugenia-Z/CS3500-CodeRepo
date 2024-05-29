import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
//import edu.neu.TestWeight;

import static org.junit.Assert.*;

public class EnglishSolitaireModelHiddenTests {

  private MarbleSolitaireModel model;

  protected MarbleSolitaireModel createModel(int boardDimension, int emptyRow,
                                             int emptyCol) {
    return new EnglishSolitaireModel(boardDimension, emptyRow, emptyCol);
  }

  protected MarbleSolitaireModel createModel() {
    return new EnglishSolitaireModel();
  }

  protected MarbleSolitaireModel createModel(int d) {
    return new EnglishSolitaireModel(d);
  }

//  @TestWeight(weight = 1)
  @Test(timeout=5000)
  public void testEmptyModelCustomArmLengthDefaultEmptyCell() {
    for (int d = 2; d < 100; d+=2) {
      try {
        model = createModel(d);
        fail("Should not be able to create a marble Solitaire board with "
             + "arm length " + d + ", but it was created.");
      } catch (IllegalArgumentException e) {

      } catch (Exception e) {
        fail("Expected IllegalArgumentException, but got " + e.getClass());
      }
    }
  }


//  @TestWeight(weight = 1)
  @Test(timeout=5000)
  public void testSingleDownMoveDefaultBoard() {

    model = createModel();
    assertEquals("Before moving, the empty position was not as expected",
            MarbleSolitaireModelState.SlotState.Empty,model.getSlotAt(3,3));
    model.move(1, 3, 3, 3);
    assertEquals("After moving, the position from which marble moved is not empty",
            MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(1,3));
    assertEquals("After moving, the position to which marble moved is not full",
            MarbleSolitaireModelState.SlotState.Marble, model.getSlotAt(3,3));
    assertEquals("After moving, the position over which marble jumped is not empty",
            MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(2,3));

    assertEquals("Did not get a score of 31 when starting from default board "
                 + "and moving a marble from 1,3",
            31, model.getScore());

  }

//  @TestWeight(weight = 1)
  @Test(timeout=5000)
  public void testSingleDownMoveCustomBoard() {
    for (int d=3;d<=9;d+=2) {
      int boardDimension = 3*d-2;
      int numMarbles = boardDimension*boardDimension-4*(d-1)*(d-1)-1;
      for (int i = 0; i < boardDimension; i++) {
        for (int j = 0; j < boardDimension; j++) {
          if (!isValid(i, j, boardDimension)) {
            continue;
          }
          model = createModel(d, i, j);
          int fromRow = i + 2;
          int fromCol = j;
          if (!isValid(fromRow, fromCol, boardDimension)) {
            continue;
          }
          assertEquals("Position from which marble is to be moved does not contain a marble",
                  MarbleSolitaireModelState.SlotState.Marble,model.getSlotAt(fromRow,fromCol));
          model.move(fromRow, fromCol, i, j);
          assertEquals("Position from which marble moved is not empty after the move",
                  MarbleSolitaireModelState.SlotState.Empty,model.getSlotAt(fromRow,fromCol));
          assertEquals("Position over which marble moved is not empty after the move",
                  MarbleSolitaireModelState.SlotState.Empty,model.getSlotAt(i+1,j));
          assertEquals("Position to which marble moved does not have a marble",
                  MarbleSolitaireModelState.SlotState.Marble,model.getSlotAt(i,j));


          assertEquals("Board does not have "+(numMarbles-1)+" pegs after "
                       + "one valid move"
                  , numMarbles-1, model.getScore());
        }
      }
    }
  }


//  @TestWeight(weight = 1.3)
  @Test(timeout=5000)
  public void testMovesNotTwoApartSameRow() {
    MarbleSolitaireModelState.SlotState [][]before = new MarbleSolitaireModelState.SlotState[7][7];
    MarbleSolitaireModelState.SlotState [][]after = new MarbleSolitaireModelState.SlotState[7][7];
    model = createModel();
    for (int i=0;i<7;i++) {
      for (int j=0;j<7;j++) {
        before[i][j] = model.getSlotAt(i,j);
      }
    }
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        if (!isValid(i, j, 7)) {
          continue;
        }
        for (int k = 0; k < 7; k++) {
          if (Math.abs(k - i) == 2) {
            continue;
          }
          int toRow = k;
          int toCol = j;
          if (!isValid(toRow, toCol, 7)) {
            continue;
          }
          try {
            model.move(i, j, toRow, toCol);
            fail("Should not be able to move from (" + i + "," + j + ") to ("
                 + toRow + "," + toCol + ")");
          } catch (IllegalArgumentException e) {

          }
        }
      }
    }
    for (int i=0;i<7;i++) {
      for (int j=0;j<7;j++) {
        after[i][j] = model.getSlotAt(i,j);
      }
    }
    assertArrayEquals("After attempting some invalid moves, board does not remain "
                    + "the same as it was before the moves were attempted"
            , after, before);

    assertEquals("Board does not have 32 marbles when starting from "
                 + "default board and attempting invalid moves"
            , 32, model.getScore());
  }


//  @TestWeight(weight = 1.3)
  @Test(timeout=5000)
  public void testMovesNotSameRowNorColumn() {
    MarbleSolitaireModelState.SlotState [][]before = new MarbleSolitaireModelState.SlotState[7][7];
    MarbleSolitaireModelState.SlotState [][]after = new MarbleSolitaireModelState.SlotState[7][7];
    model = createModel();
    for (int i=0;i<7;i++) {
      for (int j=0;j<7;j++) {
        before[i][j] = model.getSlotAt(i,j);
      }
    }
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        if (!isValid(i, j, 7)) {
          continue;
        }
        for (int k = 0; k < 7; k++) {
          if (k == i) {
            continue;
          }
          for (int l = 0; l < 7; l++) {
            if (l == j) {
              continue;
            }

            int toRow = k;
            int toCol = l;
            if (!isValid(toRow, toCol, 7)) {
              continue;
            }
            try {
              model.move(i, j, toRow, toCol);
              fail("On a board of arm length 3 and default empty cell, "
                   + "should not be able to move "
                   + "from (" + i + "," + j +
                   ") to ("
                   + toRow + "," + toCol + ")");
            } catch (IllegalArgumentException e) {

            }
          }
        }
      }
    }
    for (int i=0;i<7;i++) {
      for (int j=0;j<7;j++) {
        after[i][j] = model.getSlotAt(i,j);
      }
    }
    assertArrayEquals("After attempting some invalid moves, board does not remain "
                    + "the same as it was before the moves were attempted"
            , after, before);
    assertEquals("Board does not have 32 marbles when starting from "
                 + "default board and attempting invalid moves"
            , 32, model.getScore());
  }


//  @TestWeight(weight = 1.3)
  @Test(timeout=5000)
  public void testMoveToOccupiedPosition() {
    for (int d=3;d<=9;d+=2) {
      int boardDimension = 3*d-2;
      for (int i = 0; i < boardDimension; i++) {
        for (int j = 0; j < boardDimension; j++) {
          if (!isValid(i, j, boardDimension)) {
            continue;
          }
          MarbleSolitaireModelState.SlotState[][] before = new MarbleSolitaireModelState.SlotState[7][7];
          MarbleSolitaireModelState.SlotState[][] after = new MarbleSolitaireModelState.SlotState[7][7];
          //create a model with (i,j) as empty cell
          model = createModel(d, i, j);
          for (int k = 0; k < 7; k++) {
            for (int l = 0; l < 7; l++) {
              before[k][l] = model.getSlotAt(k, l);
            }
          }
          //create a model with (i,j) as empty cell
          model = createModel(d, i, j);
          for (int r = 0; r < boardDimension; r++) {
            for (int c = 0; c < boardDimension; c++) {
              if (!isValid(r, c, boardDimension)) {
                continue;
              }
              if ((r == i) && (c == j)) {
                continue;
              }
              //try to move from r,c i,j
              int[] incr = {-1, 0, 0, -1, 0, 1, 1, 0};
              for (int k = 0; k < incr.length; k += 2) {
                int fromRow = r;
                int fromCol = c;
                int toRow = r + incr[k];
                int toCol = c + incr[k + 1];

                if (!isValid(toRow, toCol, boardDimension)) {
                  continue;
                }

                if ((toRow == i) && (toCol == j)) {
                  continue;
                }
                try {
                  model.move(fromRow, fromCol, toRow, toCol);
                  fail("On a board of arm length "+d+" and empty position ("+i+","+j+") "
                       + ", should not be "
                       + "able to move "
                       + "from (" + fromRow + "," +
                       fromCol + " " + "to (" + toRow + "," + "" + toCol + ")");
                } catch (IllegalArgumentException e) {

                } catch (Exception e) {
                  fail("Encountered unexpected exception of type " + e.getClass());
                }
              }

            }
          }
          for (int k = 0; k < 7; k++) {
            for (int l = 0; l < 7; l++) {
              after[k][l] = model.getSlotAt(k, l);
            }
          }
          assertArrayEquals("After attempting some invalid moves, board does not remain "
                          + "the same as it was before the moves were attempted"
                  , after, before);

        }
      }
    }

  }

//  @TestWeight(weight = 2)
  @Test(timeout=5000)
  public void testGameOverDefaultBoardWithInvalidMoves() {
    model = createModel();

    int[] inputs = {
            1, 3, 3, 3,
            2, 5, 2, 3,
            1, 1, 1, 1, //invalid
            0, 4, 2, 4,
            3, 4, 1, 4,
            3, 4, 1, 4, //invalid
            5, 4, 3, 4,
            4, 6, 4, 4,
            4, 3, 4, 5,
            2, 6, 4, 6,
            4, 6, 4, 4,
            2, 2, 2, 4, //invalid
            2, 2, 2, 4,
            2, 0, 2, 2,
            4, 1, 4, 3,
            4, 3, 4, 5,
            4, 5, 2, 5,
            2, 5, 2, 3,
            2, 5, 2, 3, //invalid
            2, 3, 2, 1,
            6, 2, 4, 2,
            3, 2, 5, 2,
            6, 4, 6, 2,
            6, 2, 4, 2,
            4, 0, 2, 0,
            2, 0, 2, 2,
            0, 2, 0, 4,
            0, 4, 2, 4,
            2, 4, 4, 4,
            1, 2, 3, 2,
            3, 2, 5, 2,
            2, 2, 2, 2, //invalid
            5, 2, 5, 4,
            5, 4, 3, 4,
            3, 4, 3, 2,
            3, 4, 3, 2, //invalid
            3, 1, 3, 3};

    int success = 0;
    for (int i = 0; i < inputs.length; i += 4) {
      assertFalse("isGameOver returns true even though game is not over",
              model.isGameOver());
      try {
        model.move(inputs[i], inputs[i + 1],
                inputs[i + 2], inputs[i + 3]);
        success++;
        assertEquals("While playing an entire game, a valid move was not "
                     + "successful", 32 - success, model.getScore());
      } catch (IllegalArgumentException e) {

      }

    }
    assertTrue("isGameOver returns false even though game is over",
            model.isGameOver());


  }


  private boolean isValid(int row, int col, int boardDimensions) {
    int d = (boardDimensions + 2) / 3;
    boolean a = (row >= 0) && (row < boardDimensions) && (col >= 0) && (col < boardDimensions);
    boolean b =
            ((row < (d - 1)) || (row >= (2 * d - 1))) && ((col < (d - 1)) || (col >= (2 * d - 1)));
    return a && !b;
  }

  private boolean isAllowed(int fr, int fc, int tr, int tc) {
    return (((fr == tr) || (fc == tc)) && ((Math.abs(fr - tr) == 2) || (Math.abs(fc - tc) == 2)));
  }

  private String revealActual(String str) {
    String ret = str.replace("\r", "\\r").replace("\n","\\n");
    return ret;
  }


}