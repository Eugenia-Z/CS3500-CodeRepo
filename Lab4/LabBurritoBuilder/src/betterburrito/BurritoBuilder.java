package betterburrito;

import java.util.HashMap;
import java.util.Map;

import burrito.PortionSize;
import burrito.Protein;
import burrito.Size;
import burrito.Topping;

/**
 * This class represents the builder for a burrito.
 * @param <T>
 */
public abstract class BurritoBuilder<T extends BurritoBuilder<T>> {
  protected Size size;
  protected Map<Protein, PortionSize> proteins;
  protected Map<Topping, PortionSize> toppings;

  /**
   * Create a burrito builder with no proteins or toppings.
   */
  protected BurritoBuilder() {
    size = null;
    proteins = new HashMap<Protein, PortionSize>();
    toppings = new HashMap<Topping, PortionSize>();
  }

  public abstract ObservableBurrito build();

  protected abstract T returnBuilder();

  public T size(Size s) {
    this.size = s;
    return returnBuilder();
  }


  public T addProtein(Protein p, PortionSize size) {
    this.proteins.put(p, size);
    return returnBuilder();
  }


  public T addTopping(Topping t, PortionSize size) {
    this.toppings.put(t, size);
    return this.returnBuilder();
  }


}
