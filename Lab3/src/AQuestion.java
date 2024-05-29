public abstract class AQuestion implements Question {
    protected String questionText;

    protected String enteredAnswer;

    protected String[] options;

    public AQuestion(String text) throws IllegalArgumentException {
        if ((text.length() == 0)) {
            throw new IllegalArgumentException("Invalid question text");
        }
        this.questionText = text;
        this.enteredAnswer = "";
        this.options = new String[0];
    }

    /**
     * Get the text of this question
     *
     * @return the text of this question as a string
     */
    public String getQuestionText() {
        return this.questionText;
    }

    /**
     * Get a string that represents the type of this question.
     * The actual string returned depends on the implementation.
     *
     * @return the type of this question, as a string
     */
    public abstract String getType();

    /**
     * Enter an answer to this question. Specific implementations may enforce constraints
     * on what a valid answer can be.
     *
     * @param enteredAnswer the answer entered for this question by a student
     */
    public void answer(String enteredAnswer) throws IllegalArgumentException {
        try {
            int enteredAnswerToNum = Integer.parseInt(enteredAnswer);
            if (enteredAnswerToNum < 1) {
                throw new IllegalArgumentException("Invalid answer");
            }
            for (String option : this.options) {
                int optionToInt = Integer.parseInt(option);
                if (enteredAnswerToNum == optionToInt) {
                    this.enteredAnswer = enteredAnswer.toLowerCase();
                    return;
                }
            }

            throw new IllegalArgumentException("Invalid answer");
        } catch (NumberFormatException e) {

            for (String option : this.options) {
                if (enteredAnswer.toLowerCase().equals(option)) {
                    this.enteredAnswer = enteredAnswer.toLowerCase();
                    return;
                }
            }

            throw new IllegalArgumentException("Invalid answer");
        }
    }

    /**
     * Returns whether this question has been answered by the student.
     *
     * @return true if the question has been answered, false otherwise
     */
    public boolean hasBeenAnswered() {
        for (String option : this.options) {
            if (this.enteredAnswer.toLowerCase().equals(option)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the answer entered by the student, if it has been entered.
     *
     * @return the answer entered by the student
     * @throws IllegalStateException if the question has not been answered yet
     */
    public String getEnteredAnswer() throws IllegalStateException {
        if (!this.hasBeenAnswered()) {
            throw new IllegalStateException("Question not attempted yet!");
        } else {
            return this.enteredAnswer;
        }
    }
}
