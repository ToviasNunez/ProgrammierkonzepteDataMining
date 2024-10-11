import org.scalatest.funsuite.AnyFunSuite

class RationalTest extends AnyFunSuite:
     // test the creation of rational number and simplification
     test("Creating and simplification of a rational number"):
       val r = new Rational(4,8).trim
       assert(r.numer ==1)
       assert(r.denom == 2)
     // Test that require condition work for invalid denominator
     test("Denominator can not be  zero"):
       assertThrows[IllegalArgumentException]:
            new Rational(1,0)
     // Test the Addition if two  rational numbers
     test("Addition of two rational numbers"):
       val r1 = new Rational(1,2)
       val r2 = new Rational(1,3)
       val result = r1 + r2
       assert(result.numer == 5)
       assert(result.denom == 6)

     // Test the subtraction of two rational numbers
     test("Subtraction of two rational numbers"):
       val r1 = new Rational(3,4)
       val r2 = new Rational(1,4)
       val result = r1 - r2
       assert(result.numer == 1)
       assert(result.denom == 2)


     // Test negation of a rational number
     test("Negation of rational number"):
       val r = new Rational(1,2)
       val negated = r.unary_-
       assert(negated.numer == -1)
       assert(negated.denom == 2)

     // Test comparison of two rational number (less than)
     test("Comparison of two rational number (less than)"):
       val r1 = new Rational(1,2)
       val r2 = new Rational(2,3)
       assert(r1 < r2)
     // Test finding the max of two rational number
     test("Max of two rational number"):
       val r1 = new Rational(1,2)
       var r2 = new Rational(2,3)
       assert(r1.max(r2) == r2)

     // Test Converting the rational number to a double
     test("Converting a rational to a double number"):
       val r1 = new Rational(1,2)
       val result = r1.toDouble
       assert(result== 0.5)
     // Test the toString method for infix representation
     test("Infix string representation of rational numbers")
     val r = new Rational(3,4)
     assert(r.toString() == "3/4")