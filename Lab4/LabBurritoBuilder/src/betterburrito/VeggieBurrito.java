package betterburrito;

import burrito.PortionSize;
import burrito.Protein;
import burrito.Size;
import burrito.Topping;

/**
 * This class represents a veggie burrito. A veggie burrito has black beans,
 * medium salsa, cheese, lettuce, and guacamole, all in the regular portions.
 */
public class VeggieBurrito extends CustomBurrito {
  private VeggieBurrito(VeggieBurritoBuilder builder) {
    super(builder.size, builder.proteins, builder.toppings);
  }

  public static class VeggieBurritoBuilder extends BurritoBuilder<VeggieBurritoBuilder> {

    public VeggieBurritoBuilder() {
      super();
      this.addProtein(Protein.BlackBeans, PortionSize.Normal);
      this.addTopping(Topping.MediumSalsa, PortionSize.Normal);
      this.addTopping(Topping.Cheese, PortionSize.Normal);
      this.addTopping(Topping.Lettuce, PortionSize.Normal);
      this.addTopping(Topping.Guacamole, PortionSize.Normal);
    }

    @Override
    public ObservableBurrito build() {
      return new VeggieBurrito(this);
    }

    @Override
    protected VeggieBurritoBuilder returnBuilder() {
      return this;
    }

    public VeggieBurritoBuilder noCheese() {
      toppings.remove(Topping.Cheese);
      return this;
    }

    public VeggieBurritoBuilder noBlackBeans() {
      proteins.remove(Protein.BlackBeans);
      return this;
    }

    public VeggieBurritoBuilder noMediumSalsa() {
      toppings.remove(Topping.MediumSalsa);
      return this;
    }

    public VeggieBurritoBuilder noLettuce() {
      toppings.remove(Topping.Lettuce);
      return this;
    }

    public VeggieBurritoBuilder noGuacamole() {
      toppings.remove(Topping.Guacamole);
      return this;
    }
  }
}
