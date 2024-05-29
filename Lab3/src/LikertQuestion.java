
//this class represents a 5-scale likert question
//the scales are strongly agree, agree, neither agree nor disagree, disagree and strongly disagree
public class LikertQuestion extends AQuestion {

  //a valid question must have text
  public LikertQuestion(String text) throws IllegalArgumentException {
    super(text);
    this.options =
            new String[]{"strongly agree", "agree", "neither agree nor disagree", "disagree", "strongly disagree"};
  }


  @Override
  public String getType() {
    return "Likert";
  }

}