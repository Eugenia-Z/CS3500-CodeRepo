import org.junit.Test;


import static org.junit.Assert.*;

public class YesNoQuestionTest extends AllQuestionTest {
  public YesNoQuestionTest() {
    super();
    this.possibleAnswers = new String[]
            {"yes","Yes","YEs","YeS","YES","yEs","yES","yeS","no","No","nO","NO"};
  }

  @Test(expected=IllegalArgumentException.class)
  public void testCreateYesNoQuestionNoText() {
    new YesNoQuestion("");
  }

  @Test(expected=IllegalArgumentException.class)
  public void testCreateYesNoQuestionNoQuestionMark() {
    new YesNoQuestion("Is this fun");
  }


  @Test @Override
  public void testAnswerCorrectly() {

    for (String answer : this.possibleAnswers) {
      Question q = new YesNoQuestion("Is this a trick question?");

      assertFalse(q.hasBeenAnswered());

      q.answer(answer);
      assertEquals(answer.toLowerCase(), q.getEnteredAnswer());
      assertTrue(q.hasBeenAnswered());
    }
  }

  @Test
  public void testAnswerInCorrectly() {
    String []answers = {"yess",""};
    for (String answer:answers) {
      Question q = new YesNoQuestion("Is this a trick question?");
      assertFalse(q.hasBeenAnswered());

      try {
        q.answer(answer);
        fail("Invalid answer");
      }
      catch (IllegalArgumentException e) {
        assertFalse(q.hasBeenAnswered());
      }
    }
  }


}