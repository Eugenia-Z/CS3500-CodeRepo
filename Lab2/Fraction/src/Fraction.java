public interface Fraction {
  Fraction add(Fraction other);
  Fraction add(int num, int den) throws IllegalArgumentException;
  double getDecimalValue(int roundedPlace);
}
