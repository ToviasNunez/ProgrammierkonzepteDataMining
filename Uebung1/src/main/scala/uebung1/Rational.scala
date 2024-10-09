

package uebung1

class Rational (val numerator:Int, val denominator:Int):

  require(denominator != 0,"Denominator muss != 0 sein")
 // Simplify the rational number by dividing by the the GCD of the numerator and dominator
  def g = gcd(numerator.abs , denominator.abs)
  def num:Int = numerator /g
  def denom:Int =  denominator /g
 // auxiliary constructor: for creating a relation number with only a numerator (denominator = 1)
  def this (denominator:Int) = this(denominator,1)
 // computer the greatest common divisor (GCD)
  def gcd(a: Int, b:Int): Int = if(b==0)a else gcd(b,a%b)

 
  //prove the validness of a rational number
  // (A rational number is valid if the denominator is not zero, already handled in require)

     // Transform the rational number to a real number (double)
  def value:Double = (num.toDouble / denom.toDouble)
 // Add two rational numbers
  def +(that:Rational):Rational = new Rational(num * that.denom + that.num * denom , denom*that.denom)
   // Negation ofa rational number
  def unary_- : Rational = new Rational(-num,denom)
   // subtract two Rational numbers
  def -(that:Rational): Rational = this + -that
 // Trim (simplify) the rational number (already simplified in the constructor using GCD)
  def trim: Rational = new Rational(num,denom)
 //Infix-representation (the string representation of the rational number)
   override def toString:String = s"$num/$denom"
 // Compare two rational number : less than
   def <(that:Rational):Boolean = this.num * that.denom < that.num * this.denom
  // find the maximum f two rational numbers
  def  max(x:Rational): Rational =
    if numerator / denominator < x.num/x.denom then this
      else x

end Rational



