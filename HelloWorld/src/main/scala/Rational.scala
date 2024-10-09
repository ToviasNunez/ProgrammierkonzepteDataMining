import scala.annotation.tailrec
import scala.annotation.targetName

class Rational(numerator:Int , denominator: Int) {
     require( denominator != 0, "Denominator can not be zero")
     // Simplify the rational number by dividing by the the GCD of the numerator and dominator
     private val g = gcd(numerator.abs , denominator.abs)
     private val numer: Int = numerator/g
     private val denom: Int = denominator/g

     // auxiliary constructor: for creating a relation number with only a numerator (denominator = 1)
     def this(denom:Int) = this(denom,1)
     // computer the greatest common divisor (GCD)
     @tailrec
     private def gcd(a: Int, b: Int): Int = if( b==0) a else gcd(b,a%b)

     //prove the validness of a rational number
     // (A rational number is valid if the denominator is not zero, already handled in require)

     // Transform the rational number to a real number (double)
     def toDouble: Double = numer.toDouble / denom.toDouble

     // Add two rational numbers

      def +(that: Rational): Rational = new Rational(
       numer * that.denom + that.numer * denom,
       denom * that.denom
     )

     // Negation ofa rational number
     def unary_- : Rational = new Rational(-numer,denom)

     // subtract two Rational numbers
     def -(that:Rational): Rational = this + -that

     // Trim (simplify) the rational number (already simplified in the constructor using GCD)
     def trim: Rational = new Rational(numer, denom)

  //Infix-representation (the string representation of the rational number)
  override def toString: String = s"$numer/$denom"

  // Compare two rational number : less than
  def <(that: Rational): Boolean = this.numer * that.denom < that.numer * this.denom

  // find the maximum f two rational numbers
  def max(that: Rational): Rational = if(this < that) that else this
}

