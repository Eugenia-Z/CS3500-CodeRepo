import org.junit.Before;
import org.junit.Test;

import betterpizza.AlaCartePizza;
import betterpizza.CheesePizza;
import betterpizza.ObservablePizza;
import betterpizza.VeggiePizza;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

import static org.junit.Assert.*;

public class BetterPizzaTest {

  ObservablePizza alacarte, cheese, halfCheese, veggie, veggieNoJalapenosNoTomatoesWhiteSauce;


  @Before
  public void setup() {
    alacarte = new AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Medium)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce, ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper, ToppingPortion.Full)
            .addTopping(ToppingName.Onion, ToppingPortion.Full)
            .addTopping(ToppingName.Jalapeno, ToppingPortion.LeftHalf)
            .build();

    cheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .build();

    halfCheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .leftHalfCheese()
            .build();

    veggieNoJalapenosNoTomatoesWhiteSauce = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .noJalapeno()
            .noTomato()
            .noSauce()
            .addTopping(ToppingName.Alfredo, ToppingPortion.Full)
            .build();

    veggie = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .build();
  }

  @Test
  public void testCost() {
    assertEquals(8.25, alacarte.cost(), 0.01);
    assertEquals(9, cheese.cost(), 0.01);
    assertEquals(8.5, halfCheese.cost(), 0.01);
    assertEquals(11.5, veggie.cost(), 0.01);
    assertEquals(10.75, veggieNoJalapenosNoTomatoesWhiteSauce.cost(), 0.01);
  }

  @Test(expected = IllegalStateException.class)
  public void testNoCrust() {
    new AlaCartePizza.AlaCartePizzaBuilder()
            .size(Size.Medium)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce, ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper, ToppingPortion.Full)
            .addTopping(ToppingName.Onion, ToppingPortion.Full)
            .addTopping(ToppingName.Jalapeno, ToppingPortion.LeftHalf)
            .build();
  }

  @Test(expected = IllegalStateException.class)
  public void testNoSize() {
    new AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Thin)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce, ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper, ToppingPortion.Full)
            .addTopping(ToppingName.Onion, ToppingPortion.Full)
            .addTopping(ToppingName.Jalapeno, ToppingPortion.LeftHalf)
            .build();
  }


}