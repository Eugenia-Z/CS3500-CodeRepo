
import org.junit.Test;


import static org.junit.Assert.*;

public class LikertQuestionTest extends AllQuestionTest {
  public LikertQuestionTest() {
    super();
    this.possibleAnswers = new String[]
            {"strongly agree",
                    "agree",
                    "neither agree nor disagree",
                    "disagree",
                    "strongly disagree"};
  }

  @Test(expected=IllegalArgumentException.class)
  public void testCreateLikertQuestionNoText() {
    new LikertQuestion("");
  }

  public void testAnswerCorrectly() {
    for (String answer : this.possibleAnswers) {
      Question likertQ = new LikertQuestion("Is this a trick question?");

      assertFalse(likertQ.hasBeenAnswered());

      likertQ.answer(answer);
      assertEquals(answer.toLowerCase(), likertQ.getEnteredAnswer());
      assertTrue(likertQ.hasBeenAnswered());
    }
  }

  @Test
  public void testAnswerInCorrectly() {
    String []answers = {"weakly disagree",""};
    for (String answer : answers) {
      Question q = new LikertQuestion("Is this a trick question?");
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