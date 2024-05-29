import org.junit.*;

import java.util.Random;

import static org.junit.Assert.*;

public abstract class AllQuestionTest {
    protected String longRandom;

    protected String[] possibleAnswers;

    AllQuestionTest() {
        this.longRandom = "aosdifjaso oifhas;ldihv;al skdfha;osidghv;osiadhvbasdjkhvn";
        this.possibleAnswers = new String[0];
    }

    @Test
    public void testCreateValidQuestions() {
        Random r = new Random(200);
        for (int i = 0; i < 1000; i++) {
            int start = r.nextInt(longRandom.length() - 1);
            int end = start + r.nextInt(longRandom.length() - start - 1) + 1;
            String questionText = longRandom.substring(start, end);

            Question yesOrNoQuestion = new YesNoQuestion(questionText + "?");
            Question likeEndQuestion = new LikertQuestion(questionText + "?");
            Question multipleChoice = new MultipleChoiceQuestion(questionText + "?");

            assertEquals(questionText + "?", yesOrNoQuestion.getQuestionText());
            assertEquals("YesNo", yesOrNoQuestion.getType());

            assertEquals(questionText + "?", likeEndQuestion.getQuestionText());
            assertEquals("Likert", likeEndQuestion.getType());

            assertEquals(questionText + "?", multipleChoice.getQuestionText());
            assertEquals("Multiple Choice Question", multipleChoice.getType());
        }
    }

    @Test
    public abstract void testAnswerCorrectly();
    @Test
    public abstract void testAnswerInCorrectly();


}
