import org.junit.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class MultipleChoiceQuestionTest extends AllQuestionTest {

    public MultipleChoiceQuestionTest() {
        super();
        this.possibleAnswers = new String[]
                {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateMultipleChoiceQuestionNoText() {
        new MultipleChoiceQuestion("");
    }


    @Test @Override
    public void testAnswerCorrectly() {
        for (String answer : this.possibleAnswers) {
            Question multipleChoice = new MultipleChoiceQuestion("Is this a trick question?");
                assertFalse(multipleChoice.hasBeenAnswered());

                multipleChoice.answer(answer);
                assertEquals(answer.toLowerCase(), multipleChoice.getEnteredAnswer());
                assertTrue(multipleChoice.hasBeenAnswered());
        }
    }

    @Test @Override
    public void testAnswerInCorrectly() {
        String[] incorrectOptions = {"0", "", "five"};
        for (String answer : incorrectOptions) {
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
