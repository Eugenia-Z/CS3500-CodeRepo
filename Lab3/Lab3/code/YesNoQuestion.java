
//this class represents a yes/no question
public class YesNoQuestion implements Question {
  String questionText;
  private String enteredAnswer;

  //a question must be non empty and should end with a question mark
  public YesNoQuestion(String text) throws IllegalArgumentException {
    if ((text.length()==0) || (text.charAt(text.length()-1)!='?')) {
      throw new IllegalArgumentException("Invalid question text");
    }
    this.questionText = text;
    enteredAnswer = "";
  }


  @Override
  public String getQuestionText() {
    return questionText;
  }

  @Override
  public String getType() {
    return "YesNo";
  }

  //a valid answer must be a yes or no
  @Override
  public void answer(String enteredAnswer) {
    if ((enteredAnswer.toLowerCase().equals("yes")) || (enteredAnswer.toLowerCase().equals("no"))) {
      this.enteredAnswer = enteredAnswer.toLowerCase();
    }
    else {
      throw new IllegalArgumentException("Invalid answer: " + enteredAnswer);
    }
  }

  @Override
  public boolean hasBeenAnswered() {
    return enteredAnswer.equals("yes") || enteredAnswer.equals("no");
  }

  @Override
  public String getEnteredAnswer() {
    if (!hasBeenAnswered()) {
      throw new IllegalStateException("solution.Question not attempted yet!");
    }
    else {
      return enteredAnswer;
    }
  }
}
