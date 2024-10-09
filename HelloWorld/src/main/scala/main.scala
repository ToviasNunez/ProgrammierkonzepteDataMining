@main
def runRational(): Unit = {
  val r1 = new Rational(12,16)
  val r2 = new Rational(2,5)

  println(s"Rational 1: $r1")
  println(s"Rational 2: $r2")

  // Addition rational numbers
  println(s"Addition: ${r1 + r2}")

  // Subtraction rational number
  println(s"Addition: ${r1 - r2}")

  //Negation of the rational numbers
  println(s"Subtraction: r1 ${-r1}" )

  // Transform to real number
  println(s"r1 as double : ${r1.toDouble}")

  // Comparing rational numbers
  println(s"r1 < r2: ${r1 < r2}")

  println(s"Max of r1 and r2: ${r1.max(r2)}")
}