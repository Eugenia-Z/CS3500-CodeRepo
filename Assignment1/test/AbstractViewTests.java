import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public abstract class AbstractViewTests {
  protected abstract MarbleSolitaireView createView(MarbleSolitaireModelState state);
  protected abstract MarbleSolitaireView createView(MarbleSolitaireModelState state,Appendable ap);
  protected abstract MarbleSolitaireModel createModel();

  @Test(expected=IllegalArgumentException.class)
  public void testNullModelStateDefaultAppendable() {
    createView(null);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testNullModelStateMeaningfulAppendable() {
    createView(null,new StringBuilder());
  }

  @Test(expected=IllegalArgumentException.class)
  public void testNullAppendable() {
    createView(createModel(),null);
  }


}
