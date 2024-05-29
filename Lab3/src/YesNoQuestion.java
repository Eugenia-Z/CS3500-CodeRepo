
//this class represents a yes/no question
public class YesNoQuestion extends AQuestion {

  //a question must be non empty and should end with a question mark
  public YesNoQuestion(String text) throws IllegalArgumentException {
    super(text);
    if (text.charAt(text.length()-1)!='?') {
      throw new IllegalArgumentException("Invalid question text");
    }
    this.options = new String[]{"yes", "no"};
  }

  @Override
  public String getType() {
    return "YesNo";
  }

}
