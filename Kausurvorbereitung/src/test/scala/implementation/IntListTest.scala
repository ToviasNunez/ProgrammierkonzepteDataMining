package implementation

import org.scalatest.BeforeAndAfter
import org.scalatest.funsuite.AnyFunSuite

class IntListTest extends AnyFunSuite with BeforeAndAfter {
  // Define a variable for the test list, to be used in the setup
  var testList: IntList = _

  // Setup method executed before each test
  before {
    // Initialize the test list with a sample value before each test
    testList = Cons(1, Cons(2, Cons(3, Empty)))
  }

  // Test the changeNumber function when predicate matches some elements ******no working ********
  test("changeNumber - Predicate matches some elements") {
    val pred: Int => Boolean = _ % 2 == 0 // Predicate for even numbers
    val change: Int => IntList = x => Cons(x, Cons(x, Empty)) // Change function: duplicate the number

    // Apply changeNumber
    val result = testList.changeNumber(pred, change)

    // Expected result after applying changeNumber
    val expected = Cons(1, Cons(2, Cons(2, Cons(3, Empty)))) // This is what we expect after the transformation
    assert(result == expected)
  }

  // Test flip functionality
  test("flip - Reverse the list") {
    // The original list
    val originalList = Cons(1, Cons(2, Cons(3, Empty)))

    // Apply flip and reverse the list
    val flipped = originalList.flip

    // The expected result after flipping the list
    val expectedFlipped = Cons(3, Cons(2, Cons(1, Empty)))

    assert(flipped == expectedFlipped)
  }
  
}
