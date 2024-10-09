package uebung1
import scala.io.StdIn.readLine

@main def hello()=
    
    println("Hier startet das erste Scala 3 Programm!")
    println("Please enter your name:")
    val name= readLine()
    println(s"Hello ${name}")
    val r1= new Rational(12,24)
    val r2= new Rational(3,4)
    val r3= r1.max(r2)
    if r3==r2 then println("Richtiges Ergebnis")
    	else println("Falsches Ergebnis")
    // wirft Assertion Failed Exception
    assert(r3==r2)

    println(s"Rational 1: $r1")
    println(s"Rational 2: $r2")

  // Addition rational numbers
    println(s"Addition: ${r1 + r2}")
 
  // Subtraction rational number
    println(s"Subtraction: ${r1 - r2}")

  //Negation of the rational numbers
    println(s"Negative: r1 ${-r1}" )

  // Transform to real number
    println(s"r1 as double : ${r1.value}")

  // Comparing rational numbers
    println(s"r1 < r2: ${r1 < r2}")

    println(s"Max of r1 and r2: ${r1.max(r2)}")
