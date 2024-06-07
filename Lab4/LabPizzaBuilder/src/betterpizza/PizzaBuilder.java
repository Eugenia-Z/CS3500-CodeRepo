package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

public abstract class PizzaBuilder<T extends PizzaBuilder<T>> {
  protected Crust crust;
  protected Size size;
  protected Map<ToppingName, ToppingPortion> toppings;

  protected PizzaBuilder() {
    crust = null;
    size = null;
    toppings = new HashMap<ToppingName, ToppingPortion>();
  }

  public abstract ObservablePizza build();

  protected abstract T returnBuilder();

  public T crust(Crust c) {
    this.crust = c;
    return returnBuilder();
  }

  public T size(Size s) {
    this.size = s;
    return returnBuilder();
  }

  public T addTopping(ToppingName name, ToppingPortion portion) {
    this.toppings.put(name, portion);
    return returnBuilder();
  }

}
