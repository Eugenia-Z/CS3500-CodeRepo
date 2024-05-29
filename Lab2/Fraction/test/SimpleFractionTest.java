import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class SimpleFractionTest {
  @Test
  public void testPositiveValidFraction() {
    Random r = new Random(200);

    for (int i = 0; i < 1000; i++) {
      int n = r.nextInt(20000) + 1;
      int d = r.nextInt(20000) + 1;

      Fraction f = new SimpleFraction(n, d);
      assertEquals("Attempted to create and then print " + n + "/" + d
              + " but did not succeed", "" + n + "/" + d, f.toString());

      f = new SimpleFraction(-n, -d);
      assertEquals("Attempted to create and then print -" + n + "/-" + d
              + " (should not print negative signs) but did not succeed", "" + n
              + "/" + d, f.toString());
    }
  }
}
