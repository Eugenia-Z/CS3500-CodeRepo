public class MultipleChoiceQuestion extends AQuestion {

    MultipleChoiceQuestion(String text) throws IllegalArgumentException {
        super(text);
        this.options = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    }
    /**
     * Get a string that represents the type of this question.
     * The actual string returned depends on the implementation.
     *
     * @return the type of this question, as a string
     */
    @Override
    public String getType() {
        return "Multiple Choice Question";
    }

}
