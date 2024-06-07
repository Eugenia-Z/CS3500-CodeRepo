package betterpizza;

import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents an ala carte pizza (i.e. a pizza that can
 * have an arbitrary number of ingredients.
 */
public class AlaCartePizza implements ObservablePizza {
  protected final Crust crust;
  protected final Size size;
  protected final Map<ToppingName, ToppingPortion> toppings;

  /**
   * Create a pizza given its crust type, size and toppings.
   */


  protected AlaCartePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) throws IllegalArgumentException {
    if (crust == null) {
      throw new IllegalArgumentException("No crust specified");
    }
    if (size == null) {
      throw new IllegalArgumentException("No size specified");
    }
    if (toppings == null) {
      throw new IllegalArgumentException("No toppings specified");
    }
    this.crust = crust;
    this.size = size;
    this.toppings = toppings;
  }

  protected AlaCartePizza(AlaCartePizzaBuilder builder) {
    this(builder.size, builder.crust, builder.toppings);
  }

  /*
  public static AlaCartePizzaBuilder getBuilder() {
    return new AlaCartePizzaBuilder();
  }
*/

  @Override
  public ToppingPortion hasTopping(ToppingName name) {
    return this.toppings.getOrDefault(name, null);
  }

  @Override
  public double cost() {
    double cost = 0.0;
    for (Map.Entry<ToppingName, ToppingPortion> item : this.toppings.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultiplier();
    }
    return cost + this.size.getBaseCost();
  }

  public static class AlaCartePizzaBuilder extends PizzaBuilder<AlaCartePizzaBuilder> {
    /*  protected Crust crust;
      protected Size size;
      protected Map<ToppingName,ToppingPortion> toppings;

      protected AlaCartePizzaBuilder() {
        crust = null;
        size = null;
        toppings = new HashMap<ToppingName,ToppingPortion>();
      }
  */
    @Override
    public AlaCartePizzaBuilder returnBuilder() {
      return this;
    }

    public AlaCartePizzaBuilder crust(Crust c) {
      this.crust = c;
      return returnBuilder();
    }

    public AlaCartePizzaBuilder size(Size s) {
      this.size = s;
      return returnBuilder();
    }

    public AlaCartePizzaBuilder addTopping(ToppingName name, ToppingPortion portion) {
      this.toppings.put(name, portion);
      return returnBuilder();
    }

    public AlaCartePizzaBuilder removeTopping(ToppingName name) {
      this.toppings.remove(name);
      return returnBuilder();
    }


    public ObservablePizza build() throws IllegalStateException {
      try {
        return new AlaCartePizza(this);
      } catch (IllegalArgumentException e) {
        throw new IllegalStateException("Pizza could not be created: " + e.getMessage());
      }
    }
  }

}
