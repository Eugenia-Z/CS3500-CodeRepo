package cs3500.marblesolitaire.view;

import java.io.IOException;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

public class MarbleSolitaireTextView implements MarbleSolitaireView {
  protected MarbleSolitaireModelState modelState;
  protected Appendable app;

  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState) throws IllegalArgumentException {
    if (modelState == null) {
      throw new IllegalArgumentException("View cannot work with a null model");
    }
    this.modelState = modelState;
    this.app = System.out;
  }

  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState,Appendable ap) throws IllegalArgumentException {
    if (modelState == null) {
      throw new IllegalArgumentException("View cannot work with a null model");
    }
    if (ap == null) {
      throw new IllegalArgumentException("View cannot work with a null appendable");
    }
    this.modelState = modelState;
    this.app = ap;
  }

  public String toString() {
    return this.getGameState();
  }

  protected String getGameState() {
    StringBuilder builder = new StringBuilder();
    boolean start = false;

    for (int i = 0; i < this.modelState.getBoardSize(); i++) {
      //  builder.append("\""); //comment out
      start = false;
      for (int j = 0; j < this.modelState.getBoardSize(); j++) {
        if (this.modelState.getSlotAt(i,j)!= MarbleSolitaireModelState.SlotState.Invalid) {
          start = true;
        }
        else if ((this.modelState.getSlotAt(i,j)==MarbleSolitaireModelState.SlotState.Invalid) && (start)) {
          break;
        }
        if (j > 0) {
          builder.append(" ");
        }
        builder.append(slotStateToString(this.modelState.getSlotAt(i,j)));

      }
      //  builder.append("\\n\""); //comment out
      if (i < this.modelState.getBoardSize()-1) {
        //    builder.append("+"); //comment out
        builder.append(System.lineSeparator());
      }
    }
    return builder.toString();
  }

  protected char slotStateToString(MarbleSolitaireModelState.SlotState value) {
    switch (value) {
      case Empty:
          return '_';
      case Marble:
          return 'O';
      default:
        return ' ';
    }
  }
}
