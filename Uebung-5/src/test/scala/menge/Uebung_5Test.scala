package menge

import org.scalatest.funsuite.AnyFunSuite

class Uebung_5Test extends AnyFunSuite {

  // Test for avgEvenOdd
  test("avgEvenOdd should compute the average of even and odd numbers") {
    val numbers = List(1, 2, 3, 4, 5, 6)
    val result = Uebung_5.avgEvenOdd(numbers)
    assert(result == (4.0, 3.0)) // Even avg: (2+4+6)/3 = 4.0, Odd avg: (1+3+5)/3 = 3.0
  }

  test("avgEvenOdd should return (0.0, 0.0) for an empty list") {
    val numbers = List.empty[Int]
    val result = Uebung_5.avgEvenOdd(numbers)
    assert(result == (0.0, 0.0))
  }

  // Test for duplicateElements
  test("duplicateElements should duplicate each element in the list") {
    val numbers = List(1, 2, 3)
    val result = Uebung_5.duplicateElements(numbers)
    assert(result == List(1, 1, 2, 2, 3, 3))
  }

  test("duplicateElements should return an empty list for an empty input") {
    val numbers = List.empty[Int]
    val result = Uebung_5.duplicateElements(numbers)
    assert(result.isEmpty)
  }

  // Test for cartesianproduct
  test("cartesianproduct should compute the Cartesian product of two lists") {
    val list1 = List(1, 2)
    val list2 = List("a", "b")
    val result = Uebung_5.cartesianproduct(list1, list2)
    assert(result == List((1, "a"), (1, "b"), (2, "a"), (2, "b")))
  }

  // Test for moduloMap
  test("moduloMap should group numbers by their modulo values") {
    val numbers = List(1, 2, 3, 4, 5, 6)
    val modValue = 2
    val result = Uebung_5.moduloMap(numbers, modValue)
    assert(result == Map(0 -> List(2, 4, 6), 1 -> List(1, 3, 5)))
  }

  // Test for countLetters
  test("countLetters should count words by their lengths") {
    val words = List("Hallo", "das", "sind", "ein", "paar", "Wörter")
    val result = Uebung_5.countLetters(words)
    assert(result == Map(5 -> 1, 3 -> 2, 4 -> 2, 6 -> 1))
  }

  // Test for wordsByLength
  test("wordsByLength should group words by their lengths") {
    val words = List("Hallo", "das", "sind", "ein", "paar", "Wörter")
    val result = Uebung_5.wordsByLength(words)
    assert(result == Map(5 -> List("Hallo"), 3 -> List("das", "ein"), 4 -> List("sind", "paar"), 6 -> List("Wörter")))
  }

  // Test for avgNumbers
  test("avgNumbers should compute average of even and odd numbers separately") {
    val numbers = List(1, 4, 5, 7, 8, 9)
    val result = Uebung_5.avgNumbers(numbers)
    assert(result == Map(false -> 5.5, true -> 6.0)) // Odd avg: (1+5+7+9)/4 = 5.5, Even avg: (4+8)/2 = 6.0
  }
}
