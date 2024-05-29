package cs3500.marblesolitaire;

import cs3500.marblesolitaire.Impl.EnglishSolitaireModel;
import cs3500.marblesolitaire.Impl.MarbleSolitaireTextView;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import javax.sound.midi.Soundbank;

public class test {

  public static void main(String[] args) {
    MarbleSolitaireModelState m = new EnglishSolitaireModel(3,0,2);
    MarbleSolitaireView view = new MarbleSolitaireTextView(m);
    System.out.println(view.toString());
    System.out.println(((EnglishSolitaireModel) m).isGameOver());
  }


}
