package cs3500.marblesolitaire.Impl;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

public class MarbleSolitaireTextView implements MarbleSolitaireView {
  private MarbleSolitaireModelState model;

  public MarbleSolitaireTextView(MarbleSolitaireModelState model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.model = model;
  }

  @Override
  public String toString() {
    Integer boardSize = model.getBoardSize();
    StringBuilder sb = new StringBuilder();

    for (int row = 0; row < boardSize; ++row) {
      Boolean curRowCovered = false;
      for (int col = 0; col < boardSize; ++col) {
        if (model.getSlotAt(row,col) == SlotState.Marble) {
          curRowCovered = true;
          sb.append("O ");
        } else if (model.getSlotAt(row,col) == SlotState.Empty) {
          sb.append("_ ");
        } else {
          if (curRowCovered) break;
          sb.append("  ");
        }
      }
      sb.deleteCharAt(sb.length() - 1); // Remove the trailing space
      sb.append("\n");
    }
    sb.deleteCharAt(sb.length() - 1); // Remove the trailing newline

    return sb.toString();
  }
}
