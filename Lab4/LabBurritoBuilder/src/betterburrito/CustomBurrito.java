package betterburrito;

import java.util.Map;

import burrito.PortionSize;
import burrito.Protein;
import burrito.Size;
import burrito.Topping;

/**
 * This class represents a custom burrito that can have an arbitrary number
 * of proteins and toppings, both of arbitrary portion sizes.
 */
public class CustomBurrito implements ObservableBurrito {
  protected final Size size;
  protected final Map<Protein, PortionSize> proteins;
  protected final Map<Topping, PortionSize> toppings;

  /**
   * Create a custom burrito of the specified size.
   *
   * @param size the size of this burrito
   */
  protected CustomBurrito(
          Size size, Map<Protein, PortionSize> proteins, Map<Topping, PortionSize> toppings) {
    if (size == null) {
      throw new IllegalArgumentException("No size specified");
    }
    if (proteins == null) {
      throw new IllegalArgumentException("No proteins specified");
    }
    if (toppings == null) {
      throw new IllegalArgumentException("No toppings specified");
    }
    this.size = size;
    this.proteins = proteins;
    this.toppings = toppings;
  }

  protected CustomBurrito(CustomBurritoBuilder builder) {
    this(builder.size, builder.proteins, builder.toppings);
  }

  @Override
  public PortionSize hasTopping(Topping name) {
    return this.toppings.getOrDefault(name, null);
  }

  @Override
  public PortionSize hasProtein(Protein name) {
    return this.proteins.getOrDefault(name, null);
  }

  @Override
  public double cost() {
    double cost = 0.0;
    for (Map.Entry<Protein, PortionSize> item : this.proteins.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultipler();
    }

    for (Map.Entry<Topping, PortionSize> item : this.toppings.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultipler();
    }
    return cost + this.size.getBaseCost();
  }

  public static class CustomBurritoBuilder extends BurritoBuilder<CustomBurritoBuilder> {

    @Override
    public CustomBurritoBuilder returnBuilder() {
      return this;
    }


    public CustomBurritoBuilder size(Size s) {
      this.size = s;
      return returnBuilder();
    }


    public ObservableBurrito build() throws IllegalStateException {
      try {
        return new CustomBurrito(this);
      } catch (IllegalArgumentException e) {
        throw new IllegalStateException("Burrito could not be created: " + e.getMessage());
      }
    }
  }


}
