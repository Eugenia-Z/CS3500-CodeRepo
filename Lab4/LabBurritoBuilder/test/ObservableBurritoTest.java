import org.junit.Before;
import org.junit.Test;

import betterburrito.CustomBurrito;
import betterburrito.ObservableBurrito;
import betterburrito.VeggieBurrito;
import burrito.PortionSize;
import burrito.Protein;
import burrito.Size;
import burrito.Topping;

import static org.junit.Assert.*;

/**
 * Test class for the better burrito design.
 */
public class ObservableBurritoTest {
  ObservableBurrito customBurrito;
  ObservableBurrito veggieBurritoJumboSize;
  ObservableBurrito veggieBurritoLessCheese;
  ObservableBurrito veggieBurritoNoCheeseWithCornHotSalsa;



  @Before
  public void setup() {
    customBurrito = new CustomBurrito.CustomBurritoBuilder()
            .size(Size.Normal)
            .addProtein(Protein.Tofu, PortionSize.Normal)
            .addTopping(Topping.Cheese, PortionSize.Normal)
            .addTopping(Topping.MediumSalsa,PortionSize.Less)
            .addTopping(Topping.SourCream,PortionSize.Extra)
            .build();

    veggieBurritoJumboSize = new VeggieBurrito.VeggieBurritoBuilder()
            .size(Size.Jumbo)
            .build();

    veggieBurritoLessCheese = new VeggieBurrito.VeggieBurritoBuilder()
            .size(Size.Normal)
            .addTopping(Topping.Cheese,PortionSize.Less)
            .build();

    veggieBurritoNoCheeseWithCornHotSalsa = new VeggieBurrito.VeggieBurritoBuilder()
            .size(Size.Normal)
            .noCheese()
            .addTopping(Topping.Corn,PortionSize.Normal)
            .addTopping(Topping.HotSalsa,PortionSize.Normal)
            .build();
  }

  @Test
  public void testCost() {
    assertEquals(5.9,customBurrito.cost(),0.01);
    assertEquals(7.2,veggieBurritoJumboSize.cost(),0.01);
    assertEquals(8, veggieBurritoNoCheeseWithCornHotSalsa.cost(), 0.01);
  }

  @Test(expected = IllegalStateException.class)
  public void testNoSize() {
    new CustomBurrito.CustomBurritoBuilder()
            .addProtein(Protein.Tofu, PortionSize.Normal)
            .addTopping(Topping.Cheese, PortionSize.Normal)
            .addTopping(Topping.MediumSalsa,PortionSize.Less)
            .addTopping(Topping.SourCream,PortionSize.Extra)
            .build();
  }
}