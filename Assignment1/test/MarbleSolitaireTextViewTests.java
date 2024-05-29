import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.*;

public class MarbleSolitaireTextViewTests extends AbstractViewTests {


  @Override
  protected MarbleSolitaireView createView(MarbleSolitaireModelState state) {
    return new MarbleSolitaireTextView(state);
  }

  @Override
  protected MarbleSolitaireView createView(MarbleSolitaireModelState state, Appendable ap) {
    return new MarbleSolitaireTextView(state,ap);
  }

  @Override
  protected MarbleSolitaireModel createModel() {
    return new EnglishSolitaireModel();
  }


  @Test
  public void testViewForEmptyEnglishModelDefault() {
    String expected =
                    "    O O O" + System.lineSeparator() +
                    "    O O O" + System.lineSeparator() +
                    "O O O O O O O" + System.lineSeparator() +
                    "O O O _ O O O" + System.lineSeparator() +
                    "O O O O O O O" + System.lineSeparator() +
                    "    O O O" + System.lineSeparator() +
                    "    O O O";

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    String actual = view.toString();
    assertEquals("Board not as expected when game begins and empty slot at "
                    + "its default position"
            , expected, actual);
    assertEquals("Board does not have 32 marbles when game begins"
            , 32, model.getScore());
  }
}