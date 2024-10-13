/**
 * this is my main code
 */

import java.time.Year
import scala.io.StdIn
import scala.util.Random

@main
def main(): Unit = {
  println("Hello world!")

  var variablesExamples = new VariablesExamples
  println(variablesExamples.tvShowWatched)
  variablesExamples.tvShowWatched = "night tv show"
  println(variablesExamples.tvShowWatched)
  println(variablesExamples.dogs)
  println(variablesExamples.newAge)

  println(variablesExamples.cat)
  println(variablesExamples.slashes)
  println(variablesExamples.url)
  println(variablesExamples.bunny)

  var my = new StringFunction
  println(my.myPet)
  println(my.myName.toUpperCase)
  println(my.myAnimal.toLowerCase)
  println(my.mySpace)
  println(my.mySpace.trim)

  // access to caracteres
  println(my.myName(2))
  println(my.myName.charAt(3))
  println(my.myName.substring(3))
  println(my.mySpace.substring(6, 12))

  println(my.mySpace.trim.length)
  println(s" the expression : ${1 + 3} =  something")
  println(raw"this is my new line \n new \\line")
  println(my.cat.length)
  println(my.myCar.substring(3, 6))
  println(s"the sum of my product from " +
    s"  apple ${my.apple} + orange ${my.orange} is = ${my.apple + my.orange}")

  my.price = 20
  my.product = 4
  var bill = my.billCalculation(my.price, my.product)
  println(s"the total value of the bill from ${my.client.toUpperCase} " +
    s"that has bord ${my.product} product with a price from ${my.price} " +
    s"is  = ${bill}")

  println(my.str2)

  val userInput = new UserInput
  // user input
  //     println(userInput.userInputWelcomeText)
  //     val input = userInput.getTheInputValue
  //     println(s"Welcome : ${input}")
  //
  //     println("Enter your age")
  //     val inputAge = userInput.getTheInputIntValue
  //     println(s"Hi $input your are $inputAge")

  println(s"The user generate a random number = " +
    s"${userInput.generateRandomValue}")
  println(Random.nextString(20))
  println(Random.nextPrintableChar())
  println(Iterator.continually(Random.nextPrintableChar()).take(20).mkString)

  // enter a number and multiplice this one by 5

  //  println("Enter a number that will be multiplice by 5")
  //  val num = userInput.getTheInputIntValue
  //  print(s"the result is ${num * 5}")

  // estimated the age of the user
  //  println("Please enter the your birth's year")
  //  val birthYear = userInput.getTheInputIntValue
  //  var age = userInput.getCurrentYear - birthYear
  //  println(s"You are around : $age year old")


  // numbers
  var bills = new Numbers
  bills.items = 50
  bills.price = 34
  println(s"the total price is: ${bills.getTotalPrice}")
  var nrOfPeople = 8_000_000_000L
  println(nrOfPeople.getClass)

  //  println("Please  enter a number ")
  //  val numberInt = userInput.getTheInputIntValue
  //  // multiplier the value with a double
  //  val resultDouble = numberInt * 2.5
  //  println(resultDouble.getClass)

  val cat = 3
  val dog: Byte = 5
  val day: Short = 30000
  println(cat.getClass)
  println(dog.getClass)
  println(day.getClass)

  //  val name: String = "John"
  //  println(name.getClass)
  //
  //  println("Please  enter a number ")
  //  val numberInt = userInput.getTheInputIntValue
  //  val numberToByte = numberInt.toByte
  //  val numberToShot = numberInt.toShort
  //  val numberToInt = numberInt.toInt
  //  val numberToFloat = numberInt.toFloat
  //  val numberToDouble = numberInt.toDouble
  //
  //  println(s"${numberToByte.getClass} , ${numberToShot.getClass} " +
  //    s" ${numberToInt.getClass} , ${numberToFloat.getClass} " +
  //    s" ${numberToDouble.getClass}")

  //  println("Please  enter a number ")
  //  val inputDouble = userInput.getTheInputDoubleValue
  //  println(inputDouble)
  //  println(inputDouble.getClass)

  val result = 5 + 3
  println(result)
  println("Hi " + " John")


  var operation = new OperatorAndBoolean(5, 2)

  println(s"Addition = ${operation.addition}")
  println(s"subtraction = ${operation.subtraction}")
  println(s"multiplication = ${operation.multiplication}")
  println(s"division = ${operation.division}")
  println(s"module = ${operation.modules.toInt}")


  println(operation.dozen)
  println(operation.month)

  var userName = "Lucy"
  var greeting = s"Hi $userName"
  println(greeting)
  println(s"operation = $$${operation.division + operation.division}")


  var bikes = 5
  bikes += 1
  println(bikes)
  bikes -= 1
  println(bikes)
  bikes *= 2
  println(bikes)
  bikes /= 3
  println(bikes)
  bikes %= 2
  println(bikes)


  println("Please  enter a the amount of the year ")
  val yearAmount = userInput.getTheInputDoubleValue
  val rate = 5.5
  val time = 5
  var total = operation.calculatePercentOfYear(yearAmount, rate, time)
  println(s"Interest in 1 year for this $total")
  println(f"the total amount after $time%d years with a $rate%.1f%% interest rate is: $$${total}")


}