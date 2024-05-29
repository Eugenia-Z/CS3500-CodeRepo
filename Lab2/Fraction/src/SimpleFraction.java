public class SimpleFraction implements Fraction {
  private final int numerator, denominator;

  public SimpleFraction(int n, int d) throws IllegalArgumentException {
    if (d == 0) {
      throw new IllegalArgumentException("Cannot create a fraction with 0 as denominator");
    }
    if (((n > 0) && (d < 0)) || ((n < 0) && (d > 0))) {
      throw new IllegalArgumentException("Cannot create a negative fraction");
    }
    this.numerator = Math.abs(n);
    this.denominator = Math.abs(d);
  }

  @Override
  public String toString() {
    return "" + this.numerator + "/" + this.denominator;
  }

  @Override
  public Fraction add(int num, int den) throws IllegalArgumentException {
    if (den == 0) {
      throw new IllegalArgumentException("Cannot add a fraction with 0 as denominator");
    }
    if (((num > 0) && (den < 0)) || ((num < 0) && (den > 0))) {
      throw new IllegalArgumentException("Cannot add a negative fraction");
    }
    return new SimpleFraction(numerator * den + denominator * num, denominator * den);
  }

  @Override
  public Fraction add(Fraction other) {
    return other.add(this.numerator, this.denominator);
  }

  @Override
  public double getDecimalValue(int roundedPlace) {
    double value = (double) this.numerator / this.denominator;
    int fac = (int) Math.pow(10, Math.max(1, roundedPlace));
    return Math.round(value * fac) / (double) fac;
  }
}
