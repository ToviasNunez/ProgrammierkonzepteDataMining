package uebung1
import scala.io.StdIn.readLine
@main def hello()=
    
    println("Hier startet das erste Scala 3 Programm!")
    println("Please enter your name:")
    val name= readLine()
    println(s"Hello ${name}")
    val x= new Rational(2,5)
    val y= new Rational(3,4)
    val z= x.max(y)
    if z==y then println("Richtiges Ergebnis")
    	else println("Falsches Ergebnis")
   
    // wirft Assertion Failed Exception
    assert(z==y)
