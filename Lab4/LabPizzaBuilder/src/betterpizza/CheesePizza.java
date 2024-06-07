package betterpizza;


import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents a cheese pizza.
 */
public class CheesePizza extends AlaCartePizza {

  private CheesePizza(CheesePizzaBuilder builder) {
    super(builder.size, builder.crust, builder.toppings);
  }

  public static class CheesePizzaBuilder extends PizzaBuilder<CheesePizzaBuilder> {

    @Override
    public CheesePizzaBuilder returnBuilder() {
      return this;
    }

    public CheesePizzaBuilder() {
      super();
      this.addTopping(ToppingName.Cheese, ToppingPortion.Full);
      this.addTopping(ToppingName.Sauce, ToppingPortion.Full);
    }

    public ObservablePizza build() {
      return new CheesePizza(this);
    }

    public CheesePizzaBuilder noCheese() {
      toppings.remove(ToppingName.Cheese);
      return this;
    }

    public CheesePizzaBuilder leftHalfCheese() {
      toppings.put(ToppingName.Cheese, ToppingPortion.LeftHalf);
      return this;
    }

    public CheesePizzaBuilder rightHalfCheese() {
      toppings.put(ToppingName.Cheese, ToppingPortion.RightHalf);
      return this;
    }

    public CheesePizzaBuilder noRedSauce() {
      toppings.remove(ToppingName.Sauce);
      return this;
    }

    public CheesePizzaBuilder leftHalfRedSauce() {
      toppings.put(ToppingName.Sauce, ToppingPortion.LeftHalf);
      return this;
    }

    public CheesePizzaBuilder rightHalfRedSauce() {
      toppings.put(ToppingName.Sauce, ToppingPortion.RightHalf);
      return this;
    }

    public CheesePizzaBuilder whiteSauce() {
      toppings.put(ToppingName.Alfredo, ToppingPortion.Full);
      return this;
    }

    public CheesePizzaBuilder leftHalfWhiteSauce() {
      toppings.put(ToppingName.Alfredo, ToppingPortion.LeftHalf);
      return this;
    }

    public CheesePizzaBuilder rightHalfWhiteSauce() {
      toppings.put(ToppingName.Alfredo, ToppingPortion.RightHalf);
      return this;
    }


  }
}
