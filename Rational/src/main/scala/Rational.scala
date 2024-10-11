import scala.annotation.{tailrec, targetName}

class Rational(n:Int, d:Int): // class for the representation of rational numbers
  // creation of rational number by specifying the nominator and denominator

  // Prove the validness of a rational number
  require( d != 0, "Denominator can not be zero")

  // Simplification of Rational

  
  val numer:Int = n
  val denom:Int = d

  // GCD nethide
  @tailrec
  private def gcd(a:Int, b:Int):Int = if(b==0) a else gcd(b,a%b)

  // Transform it to a real number
  def toDouble: Double = numer.toDouble /denom.toDouble
  // Adding rational numbers
  @targetName("+")
  def +(that: Rational): Rational = new Rational(numer*that.denom + that.numer*denom , denom * that.denom)

  // Negation rational numbers

  def unary_- : Rational = new Rational(-numer,denom)

  // Subtraction rational number
  @targetName("-")
  def -(that:Rational):Rational = (this + -that).trim

   // Trim (simplify) the rational number (already simplified in the constructor using GCD)
   def trim: Rational = {
    val g = gcd(numer.abs,denom.abs)
    new Rational(numer/g, denom/g)}

  // Infix-representation
  override def toString(): String = s"$numer/$denom"

  // Comparing rational numbers (small than , max )
  def < (that:Rational) : Boolean = this.numer * that.denom < that.numer * this.denom

  def max (that:Rational) : Rational = if (this<that) that else this


end Rational

