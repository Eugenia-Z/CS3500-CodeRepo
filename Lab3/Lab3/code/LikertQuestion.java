
//this class represents a 5-scale likert question
//the scales are strongly agree, agree, neither agree nor disagree, disagree and strongly disagree
public class LikertQuestion implements Question {
  public String questionText;
  String enteredAnswer;

  //a valid question must have text
  public LikertQuestion(String text) throws IllegalArgumentException {
    if ((text.length()==0)) {
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
    return "Likert";
  }

  //a valid answer must be one of the 5 options, in a case-insensitive manner
  @Override
  public void answer(String enteredAnswer) {
    String [] options = {"strongly agree","agree","neither agree nor disagree","disagree","strongly disagree"};

    for (String option:options) {
      if (enteredAnswer.toLowerCase().equals(option)) {
        this.enteredAnswer = enteredAnswer.toLowerCase();
        return;
      }
    }

    throw new IllegalArgumentException("Invalid answer");
  }

  @Override
  public boolean hasBeenAnswered() {
    String [] options = {"strongly agree","agree","neither agree nor disagree","disagree","strongly disagree"};

    for (String option:options) {
      if (enteredAnswer.toLowerCase().equals(option)) {
        return true;
      }
    }
    return false;
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
