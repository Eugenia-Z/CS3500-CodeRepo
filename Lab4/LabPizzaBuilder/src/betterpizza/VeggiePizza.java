package betterpizza;


import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents a vegetarian pizza.
 */
public class VeggiePizza extends AlaCartePizza {

  private VeggiePizza(VeggiePizza.VeggiePizzaBuilder builder) {
    super(builder.size, builder.crust, builder.toppings);
  }

  public static class VeggiePizzaBuilder extends PizzaBuilder<VeggiePizzaBuilder> {
    @Override
    public VeggiePizza.VeggiePizzaBuilder returnBuilder() {
      return this;
    }

    public VeggiePizzaBuilder() {
      super();
      this.addTopping(ToppingName.Cheese, ToppingPortion.Full);
      this.addTopping(ToppingName.Sauce, ToppingPortion.Full);
      this.addTopping(ToppingName.BlackOlive, ToppingPortion.Full);
      this.addTopping(ToppingName.GreenPepper, ToppingPortion.Full);
      this.addTopping(ToppingName.Onion, ToppingPortion.Full);
      this.addTopping(ToppingName.Jalapeno, ToppingPortion.Full);
      this.addTopping(ToppingName.Tomato, ToppingPortion.Full);
    }

    public ObservablePizza build() {
      return new VeggiePizza(this);
    }

    public VeggiePizza.VeggiePizzaBuilder noCheese() {
      toppings.remove(ToppingName.Cheese);
      return this;
    }

    public VeggiePizza.VeggiePizzaBuilder noSauce() {
      toppings.remove(ToppingName.Sauce);
      return this;
    }

    public VeggiePizza.VeggiePizzaBuilder noBlackOlives() {
      toppings.remove(ToppingName.BlackOlive);
      return this;
    }

    public VeggiePizza.VeggiePizzaBuilder noGreenPepper() {
      toppings.remove(ToppingName.GreenPepper);
      return this;
    }

    public VeggiePizza.VeggiePizzaBuilder noOnion() {
      toppings.remove(ToppingName.Onion);
      return this;
    }

    public VeggiePizza.VeggiePizzaBuilder noJalapeno() {
      toppings.remove(ToppingName.Jalapeno);
      return this;
    }

    public VeggiePizza.VeggiePizzaBuilder noTomato() {
      toppings.remove(ToppingName.Tomato);
      return this;
    }
  }
}
