import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
//import edu.neu.TestWeight;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class EnglishSolitaireModelVisibleTests {

  private MarbleSolitaireModel model;

  protected MarbleSolitaireModel createModel(int emptyRow, int emptyCol) {
    return new EnglishSolitaireModel(emptyRow, emptyCol);
  }

  protected MarbleSolitaireModel createModel(int armLength, int emptyRow,
                                             int emptyCol) {
    return new EnglishSolitaireModel(armLength, emptyRow, emptyCol);
  }

  protected MarbleSolitaireModel createModel() {
    return new EnglishSolitaireModel();
  }
  protected MarbleSolitaireModel createModel(int d) {
    return new EnglishSolitaireModel(d);
  }

//  @TestWeight(weight = 1.5)
  @Test(timeout=5000)
  public void testNewModelCustomEmptyCell() {
    for (int d = 3; d <= 15; d += 2) {
      int boardDimensions = 3 * d - 2;
      int numMarbles = boardDimensions * boardDimensions - 4 * (d - 1) * (d - 1) - 1;
      for (int i = 0; i < boardDimensions; i++) {
        for (int j = 0; j < boardDimensions; j++) {
          if (!isValid(i, j, boardDimensions)) {
            continue;
          }
          try {
            model = createModel(d,i,j);
          } catch (Exception e) {
            fail("Could not create a marble solitaire board of arm "
                    + "thickness " + d + " with empty cell at "
                    + "(" + i + "," + j + ")");
          }
          assertEquals("After creating board, board dimensions are not as expected",
                  boardDimensions,model.getBoardSize());
          for (int k = 0; k < boardDimensions; k++) {
            for (int l = 0; l < boardDimensions; l++) {
              if (!isValid(k, l, boardDimensions)) {
                assertEquals("Given position should be invalid, but is returned as empty or marble"
                        , MarbleSolitaireModelState.SlotState.Invalid,model.getSlotAt(k,l));
              } else if ((k == i) && (l == j)) {
                assertEquals("Given position should be empty, but is returned as invalid or marble"
                        , MarbleSolitaireModelState.SlotState.Empty,model.getSlotAt(k,l));
              } else {
                assertEquals("Given position should have a marble, but is returned as empty or invalid"
                        , MarbleSolitaireModelState.SlotState.Marble,model.getSlotAt(k,l));
              }



            }

          }
          assertEquals("Board does not have " + numMarbles + " marbles when "
                          + "game begins with arm length " + d + " and "
                          + "empty slot at (" + i + "," + j + ")"
                  , numMarbles, model.getScore());
        }
      }
    }
  }

//  @TestWeight(weight = 1.5)
  @Test(timeout=5000)
  public void testNewModelDefaultEmptyCell() {
    for (int d = 3; d <= 15; d += 2) {
      int boardDimensions = 3 * d - 2;
      int numMarbles = boardDimensions * boardDimensions - 4 * (d - 1) * (d - 1) - 1;

          try {
            model = createModel(d);
          } catch (Exception e) {
            fail("Could not create a marble solitaire board of arm "
                    + "thickness " + d + " with empty cell in the middle");
          }
      assertEquals("After creating board, board dimensions are not as expected",
              boardDimensions,model.getBoardSize());
          for (int k = 0; k < boardDimensions; k++) {
            for (int l = 0; l < boardDimensions; l++) {
              if (!isValid(k, l, boardDimensions)) {
                assertEquals("Given position should be invalid, but is returned as empty or marble"
                        , MarbleSolitaireModelState.SlotState.Invalid,model.getSlotAt(k,l));
              } else if ((k == boardDimensions/2) && (l == boardDimensions/2)) {
                assertEquals("Given position should be empty, but is returned as invalid or marble"
                        , MarbleSolitaireModelState.SlotState.Empty,model.getSlotAt(k,l));
              } else {
                assertEquals("Given position should have a marble, but is returned as empty or invalid"
                        , MarbleSolitaireModelState.SlotState.Marble,model.getSlotAt(k,l));
              }



            }

          }
          assertEquals("Board does not have " + numMarbles + " marbles when "
                          + "game begins with arm length " + d + " and "
                          + "empty slot at the center" , numMarbles, model.getScore());
    }
  }




//  @TestWeight(weight = 1.5)
  @Test(timeout=5000)
  public void testViewForEmptyModelCustomEmptyCell() {
    MarbleSolitaireView view;
    for (int d = 3; d <= 15; d += 2) {
      int boardDimensions = 3 * d - 2;
      int numMarbles = boardDimensions * boardDimensions - 4 * (d - 1) * (d - 1) - 1;
      for (int i = 0; i < boardDimensions; i++) {
        for (int j = 0; j < boardDimensions; j++) {
          if (!isValid(i, j, boardDimensions)) {
            continue;
          }
          try {
            model = createModel(d, i, j);
          } catch (Exception e) {
            fail("Could not create a marble solitaire board of arm "
                 + "thickness " + d + " with empty cell at "
                 + "(" + i + "," + j + ")");
          }
          view = new MarbleSolitaireTextView(model);
          StringBuilder builder = new StringBuilder();
          boolean start;
          char peg;
          for (int k = 0; k < boardDimensions; k++) {
            start = false;
            for (int l = 0; l < boardDimensions; l++) {
              if (!isValid(k, l, boardDimensions)) {
                peg = ' ';
              } else if ((k == i) && (l == j)) {
                peg = '_';
              } else {
                peg = 'O';
              }

              if (peg != ' ') {
                start = true;
              } else if ((peg == ' ') && (start)) {
                break;
              }
              if (l > 0) {
                builder.append(" ");
              }
              builder.append(peg);

            }
            if (k < boardDimensions - 1) {
              builder.append(System.lineSeparator());
            }
          }
          assertEquals("Board not as expected when game begins with arm "
                       + "length " + d + " and empty slot"
                       + " at (" + i + "," + j + ")"
                  , builder.toString(), view.toString());
          assertEquals("Board does not have " + numMarbles + " marbles when "
                       + "game begins with arm length " + d + " and "
                       + "empty slot at (" + i + "," + j + ")"
                  , numMarbles, model.getScore());
        }
      }
    }
  }

//  @TestWeight(weight = 1)
  @Test(timeout=5000)
  public void testEmptyModelCustomInvalidEmptyCell() {
    for (int d=3;d<=15;d++) {
      int boardDimension = 3*d-2;
      for (int i = 0; i < boardDimension; i++) {
        for (int j = 0; j < boardDimension; j++) {
          if (!isValid(i, j, boardDimension)) {
            try {
              model = createModel(d, i, j);
              fail("Should not be able to create a marble Solitaire board "
                   + "with arm length "+d
                   + " and empty cell at (" + i + "," + j + "), but it was "
                   + "created.");
            } catch (IllegalArgumentException e) {

            } catch (Exception e) {
              fail("Expected IllegalArgumentException, but got " + e.getClass());
            }
          }
        }
      }
    }
  }

//  @TestWeight(weight = 1)
  @Test(timeout=5000)
  public void testSingleLeftMoveDefaultBoard() {

    model = createModel();
    assertEquals("Before moving, the empty position was not as expected",
            MarbleSolitaireModelState.SlotState.Empty,model.getSlotAt(3,3));
    model.move(3, 5, 3, 3);
    assertEquals("After moving, the position from which marble moved is not empty",
            MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(3,5));
    assertEquals("After moving, the position to which marble moved is not full",
            MarbleSolitaireModelState.SlotState.Marble, model.getSlotAt(3,3));
    assertEquals("After moving, the position over which marble jumped is not empty",
            MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(3,4));

    assertEquals("Did not get a score of 31 when starting from default board "
                 + "and moving a marble from 3,5",
            31, model.getScore());

  }

//  @TestWeight(weight = 1)
  @Test(timeout=5000)
  public void testSingleLeftMoveCustomBoard() {
    for (int d=3;d<=9;d+=2) {
      int boardDimension = 3*d-2;
      int numMarbles = boardDimension*boardDimension-4*(d-1)*(d-1)-1;
      for (int i = 0; i < boardDimension; i++) {
        for (int j = 0; j < boardDimension; j++) {
          if (!isValid(i, j, boardDimension)) {
            continue;
          }
          model = createModel(d, i, j);
          int fromRow = i;
          int fromCol = j + 2;
          if (!isValid(fromRow, fromCol, boardDimension)) {
            continue;
          }
          assertEquals("Position from which marble is to be moved does not contain a marble",
                  MarbleSolitaireModelState.SlotState.Marble,model.getSlotAt(fromRow,fromCol));
          model.move(fromRow, fromCol, i, j);
          assertEquals("Position from which marble moved is not empty after the move",
                  MarbleSolitaireModelState.SlotState.Empty,model.getSlotAt(fromRow,fromCol));
          assertEquals("Position over which marble moved is not empty after the move",
                  MarbleSolitaireModelState.SlotState.Empty,model.getSlotAt(i,j+1));
          assertEquals("Position to which marble moved does not have a marble",
                  MarbleSolitaireModelState.SlotState.Marble,model.getSlotAt(i,j));

          assertEquals("Board does not have "+(numMarbles-1)+" pegs after "
                       + "one valid move"
                  , numMarbles-1, model.getScore());
        }
      }
    }
  }

//  @TestWeight(weight = 1)
  @Test(timeout=5000)
  public void testSingleRightMoveDefaultBoard() {
    String s = System.lineSeparator();

    model = createModel();
    assertEquals("Position from which marble is to be moved does not contain a marble",
            MarbleSolitaireModelState.SlotState.Marble,model.getSlotAt(3,1));
    model.move(3, 1, 3, 3);
    assertEquals("Position from which marble moved is not empty after the move",
            MarbleSolitaireModelState.SlotState.Empty,model.getSlotAt(3,1));
    assertEquals("Position over which marble moved is not empty after the move",
            MarbleSolitaireModelState.SlotState.Empty,model.getSlotAt(3,2));
    assertEquals("Position to which marble moved does not have a marble",
            MarbleSolitaireModelState.SlotState.Marble,model.getSlotAt(3,3));

    assertEquals("Did not get a score of 31 when starting from default board "
                 + "and moving a marble from 3,1",
            31, model.getScore());

  }

//  @TestWeight(weight = 1)
  @Test(timeout=5000)
  public void testSingleRightMoveCustomBoard() {
    for (int d=3;d<=9;d+=2) {
      int boardDimension = 3*d-2;
      int numMarbles = boardDimension*boardDimension-4*(d-1)*(d-1)-1;
      for (int i = 0; i < boardDimension; i++) {
        for (int j = 0; j < boardDimension; j++) {
          if (!isValid(i, j, boardDimension)) {
            continue;
          }
          model = createModel(d, i, j);
          int fromRow = i;
          int fromCol = j - 2;
          if (!isValid(fromRow, fromCol, boardDimension)) {
            continue;
          }
          assertEquals("Position from which marble is to be moved does not contain a marble",
                  MarbleSolitaireModelState.SlotState.Marble,model.getSlotAt(fromRow,fromCol));
          model.move(fromRow, fromCol, i, j);
          assertEquals("Position from which marble moved is not empty after the move",
                  MarbleSolitaireModelState.SlotState.Empty,model.getSlotAt(fromRow,fromCol));
          assertEquals("Position over which marble moved is not empty after the move",
                  MarbleSolitaireModelState.SlotState.Empty,model.getSlotAt(i,j-1));
          assertEquals("Position to which marble moved does not have a marble",
                  MarbleSolitaireModelState.SlotState.Marble,model.getSlotAt(i,j));

          assertEquals("Board does not have "+(numMarbles-1)+" pegs after "
                       + "one valid move"
                  , numMarbles-1, model.getScore());
        }
      }
    }
  }


//  @TestWeight(weight = 1)
  @Test(timeout=5000)
  public void testSingleUpMoveDefaultBoard() {

    model = createModel();
    assertEquals("Position from which marble is to be moved does not contain a marble",
            MarbleSolitaireModelState.SlotState.Marble,model.getSlotAt(5,3));
    model.move(5,3,3,3);
    assertEquals("Position from which marble moved is not empty after the move",
            MarbleSolitaireModelState.SlotState.Empty,model.getSlotAt(5,3));
    assertEquals("Position over which marble moved is not empty after the move",
            MarbleSolitaireModelState.SlotState.Empty,model.getSlotAt(4,3));
    assertEquals("Position to which marble moved does not have a marble",
            MarbleSolitaireModelState.SlotState.Marble,model.getSlotAt(3,3));

    assertEquals("Did not get a score of 31 when starting from default board "
                 + "and moving a marble from 5,3",
            31, model.getScore());

  }

//  @TestWeight(weight = 1)
  @Test(timeout=5000)
  public void testSingleUpMoveCustomBoard() {
    for (int d=3;d<=9;d+=2) {
      int boardDimension = 3*d-2;
      int numMarbles = boardDimension*boardDimension-4*(d-1)*(d-1)-1;
      for (int i = 0; i < boardDimension; i++) {
        for (int j = 0; j < boardDimension; j++) {
          if (!isValid(i, j, boardDimension)) {
            continue;
          }
          model = createModel(d, i, j);
          int fromRow = i - 2;
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
                  MarbleSolitaireModelState.SlotState.Empty,model.getSlotAt(i-1,j));
          assertEquals("Position to which marble moved does not have a marble",
                  MarbleSolitaireModelState.SlotState.Marble,model.getSlotAt(i,j));

          assertEquals("Board does not have "+(numMarbles-1)+" marbles after one valid move"
                  , numMarbles-1, model.getScore());
        }
      }
    }
  }

//  @TestWeight(weight = 1.3)
  @Test(timeout=5000)
  public void testMoveFromInvalidPosition() {
    MarbleSolitaireModelState.SlotState [][]before = new MarbleSolitaireModelState.SlotState[7][7];
    MarbleSolitaireModelState.SlotState [][]after = new MarbleSolitaireModelState.SlotState[7][7];
    model = createModel();
    for (int i=0;i<7;i++) {
      for (int j=0;j<7;j++) {
        before[i][j] = model.getSlotAt(i,j);
      }
    }
    int[] incr = {-2, 0, 0, -2, 0, 2, 2, 0};

    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        if (!isValid(i, j, 7)) {
          for (int k = 0; k < incr.length; k += 2) {
            try {
              model.move(i, j, i + incr[k], j + incr[k + 1]);
              fail("After starting with default board, should not be able to "
                   + "move from (" + i + "," + j + ") "
                   + "to (" + (i + incr[k]) + "," + (j + incr[k + 1]) + ")");
            } catch (IllegalArgumentException e) {
              //pass
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
  public void testMoveToInvalidPosition() {
    MarbleSolitaireModelState.SlotState [][]before = new MarbleSolitaireModelState.SlotState[7][7];
    MarbleSolitaireModelState.SlotState [][]after = new MarbleSolitaireModelState.SlotState[7][7];
    model = createModel();
    for (int i=0;i<7;i++) {
      for (int j=0;j<7;j++) {
        before[i][j] = model.getSlotAt(i,j);
      }
    }
    int[] incr = {-2, 0, 0, -2, 0, 2, 2, 0};

    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        if (!isValid(i, j, 7)) {
          continue;
        }
        for (int k = 0; k < incr.length; k += 2) {
          int toRow = i + incr[k];
          int toCol = j + incr[k + 1];
          if (!isValid(toRow, toCol, 7)) {
            try {
              model.move(i, j, toRow, toCol);
              fail("Should not be able to move from (" + i + "," + j + ") to ("
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
  public void testMovesNotTwoApartSameColumn() {
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
          if (Math.abs(k - j) == 2) {
            continue;
          }
          int toRow = i;
          int toCol = k;
          if (!isValid(toRow, toCol, 7)) {
            continue;
          }
          try {
            model.move(i, j, toRow, toCol);
            fail("On board of arm length 3 and center empty cell, should not "
                 + "be able to move "
                 + "from (" + i + "," + j + ") to ("
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
  public void testMoveFromEmptyPosition() {
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


          //try to move from i,j to somewhere
          int[] incr = {-2, 0, 0, -2, 0, 2, 2, 0};
          for (int k = 0; k < incr.length; k += 2) {
            int toRow = i + incr[k];
            int toCol = j + incr[k + 1];
            if (!isValid(toRow, toCol, boardDimension)) {
              continue;
            }
            try {
              model.move(i, j, toRow, toCol);
              fail("On a board of arm length " + d + " and empty "
                      + "position (" + i + "," + j + ") , should not be able "
                      + "to move from "
                      + "(" + i + "," + j + " "
                      + "to (" + toRow + ","
                      + "" + toCol + ")");
            } catch (IllegalArgumentException e) {

            } catch (Exception e) {
              fail("Encountered unexpected exception of type " + e.getClass());
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

//  @TestWeight(weight = 1.3)
  @Test(timeout=5000)
  public void testMoveAcrossEmptyPosition() {
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
          //try to move across i,j
          int[] incr = {-1, 0, 1, 0, 1, 0, -1, 0, 0, -1, 0, 1, 0, 1, 0, -1};
          for (int k = 0; k < incr.length; k += 4) {
            int fromRow = i + incr[k];
            int fromCol = i + incr[k + 1];
            int toRow = i + incr[k + 2];
            int toCol = j + incr[k + 3];

            if (!isValid(fromRow, fromCol, boardDimension)) {
              continue;
            }
            if (!isValid(toRow, toCol, boardDimension)) {
              continue;
            }
            try {
              model.move(fromRow, fromCol, toRow, toCol);
              fail("On a board of arm length "+d+" and empty "
                   + "position ("+i+""+j+"), should not be able to move from "
                   + "(" + fromRow + "," +
                   fromCol + " " + "to (" + toRow + "," + "" + toCol + ")");
            } catch (IllegalArgumentException e) {

            } catch (Exception e) {
              fail("Encountered unexpected exception of type " + e.getClass());
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
  public void testGameOverDefaultBoard() {
    model = createModel();

    int[] inputs = {
            1, 3, 3, 3,
            2, 5, 2, 3,
            0, 4, 2, 4,
            3, 4, 1, 4,
            5, 4, 3, 4,
            4, 6, 4, 4,
            4, 3, 4, 5,
            2, 6, 4, 6,
            4, 6, 4, 4,
            2, 2, 2, 4,
            2, 0, 2, 2,
            4, 1, 4, 3,
            4, 3, 4, 5,
            4, 5, 2, 5,
            2, 5, 2, 3,
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
            5, 2, 5, 4,
            5, 4, 3, 4,
            3, 4, 3, 2,
            3, 1, 3, 3};

    for (int i = 0; i < inputs.length; i += 4) {
      assertFalse("isGameOver returns true even though game is not over",
              model.isGameOver());
      model.move(inputs[i], inputs[i + 1],
              inputs[i + 2], inputs[i + 3]);
      assertEquals("While playing an entire game, a valid move was not "
                   + "successful", 32 - i / 4 - 1, model.getScore());

    }
    assertTrue("isGameOver returns false even though game is over",
            model.isGameOver());


  }

//  @TestWeight(weight = 1.5)
  @Test(timeout=5000)
  public void testRandomPlay() {


    for (int d = 3; d <= 9; d += 2) {
      int boardDimensions = 3 * d - 2;
      Random r = new Random(d);
      model = createModel(d, boardDimensions / 2, boardDimensions / 2);
      int score = model.getScore();

      List<Integer> marbles = parseBoardState(model, MarbleSolitaireModelState.SlotState.Marble);//parseGameState(boardDimensions, gameState, 'O');
      List<Integer> empties = parseBoardState(model, MarbleSolitaireModelState.SlotState.Empty);//parseGameState(boardDimensions, gameState, '_');


      while (!model.isGameOver()) {
        //get the marbles and the empties

        Collections.shuffle(marbles,r);

        //choose a marble
        int fromRow = -1;
        int fromCol = -1;
        int toRow = -1;
        int toCol = -1;
        int position = 0;
        boolean found = false;
        while (!found && (position < marbles.size())) {

          fromRow = marbles.get(position) / boardDimensions;
          fromCol = marbles.get(position) % boardDimensions;
          int emptyPosition = 0;
          while (!found && emptyPosition < empties.size()) {
            toRow = empties.get(emptyPosition) / boardDimensions;
            toCol = empties.get(emptyPosition) % boardDimensions;
            if ((isValid(fromRow, fromCol, boardDimensions))
                && (isValid(toRow, toCol, boardDimensions))
                && (marbles.contains((Integer) ((toRow + fromRow) / 2 * boardDimensions +
                                                (toCol + fromCol) / 2)))
                && isAllowed(fromRow, fromCol, toRow, toCol)) {
              found = true;
            } else {
              emptyPosition++;
            }
          }
          if (!found) {
            position++;
          }
        }

        assertTrue("No marble can move, but the game is not over",
                found);
        try {
          model.move(fromRow, fromCol, toRow, toCol);
          score -= 1;

          marbles.add((Integer) (toRow * boardDimensions + toCol));
          empties.remove((Integer) (toRow * boardDimensions + toCol));

          marbles.remove((Integer) (fromRow * boardDimensions + fromCol));
          empties.add((Integer) (fromRow * boardDimensions + fromCol));

          marbles.remove((Integer) ((fromRow + toRow) / 2 * boardDimensions + (fromCol + toCol) / 2));
          empties.add((Integer) ((fromRow + toRow) / 2 * boardDimensions + (fromCol + toCol) / 2));


        } catch (IllegalArgumentException e) {

        }
        assertEquals(score, model.getScore());
      }
      //  System.out.println(model.getGameState());
      //  System.out.println("Score: " + model.getScore());
      assertEquals("After playing a board of arm length "+d+" and a center "
                   + "empty cell with random valid moves, score does not "
                   + "tally upon game over",
              score,
              model.getScore());
    }

  }

  private List<Integer> parseBoardState(MarbleSolitaireModelState modelState,MarbleSolitaireModelState.SlotState state) {
    List<Integer> positions = new LinkedList<Integer>();
    for (int i=0;i<modelState.getBoardSize();i++) {
      for (int j=0;j<modelState.getBoardSize();j++) {
        if (modelState.getSlotAt(i,j)==state) {
          positions.add(i*modelState.getBoardSize()+j);
        }
      }

    }
    return positions;
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