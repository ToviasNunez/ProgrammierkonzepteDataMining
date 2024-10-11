import java.time.Year
import scala.io.StdIn
import scala.util.Random

class UserInput:

  // get user input (online form / typing / command line)
  def userInputWelcomeText: String =
    val text = "Please enter you name"
    text

  def getTheInputValue:String =
      val input: String = StdIn.readLine()
      input
  // transformation the input into a number

  def getTheInputIntValue: Int =
    val input: Int = StdIn.readLine().toInt
    input.toInt
  // generate random number
  def generateRandomValue: Int =
   val random = Random().nextInt(100)
   random

  def getCurrentYear: Int =
   var currentYear = Year.now.getValue
   currentYear

end UserInput

