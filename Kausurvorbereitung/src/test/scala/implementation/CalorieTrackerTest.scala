package implementation

import org.scalatest.{BeforeAndAfter, BeforeAndAfterEach}
import org.scalatest.funsuite.AnyFunSuiteLike

class CalorieTrackerTest extends AnyFunSuiteLike with BeforeAndAfter {
  var tracker: CalorieTracker = _
  val calories: List[(String, String, List[(String, Int)])] = List(("Donald Duck", "2022-01-01",
    List(("Frühstück", 800), ("Mittag", 700), ("Snack", 200), ("Abendbrot", 500))),
    ("Donald Duck", "2022-01-02", List(("Frühstück", 700), ("Mittag", 650), ("Abendbrot", 520))),
    ("Donald Duck", "2022-01-03", List(("Frühstück", 800), ("Mittag", 700),
      ("Snack", 200), ("Abendbrot", 500), ("Snack", 150))), ("Donald Duck",
      "2022-01-04", List(("Frühstück", 850), ("Mittag", 900), ("Snack", 500),
      ("Snack", 400))), ("Donald Duck", "2022-01-05", List(("Frühstück", 600),
      ("Mittag", 700), ("Snack", 200), ("Abendbrot", 100))), ("Dagobert Duck",
      "2022-01-01", List(("Frühstück", 300), ("Mittag", 500), ("Snack", 100),
      ("Abendbrot", 200))), ("Dagobert Duck", "2022-01-02",
      List(("Frühstück", 200), ("Mittag", 300), ("Snack", 400), ("Abendbrot",
        200))), ("Dagobert Duck", "2022-01-03", List(("Frühstück", 800),
      ("Mittag", 700), ("Snack", 200), ("Snack", 200))), ("Dagobert Duck",
      "2022-01-04", List(("Frühstück", 200), ("Mittag", 300), ("Snack", 200),
      ("Snack", 500))), ("Dagobert Duck", "2022-01-05", List(("Frühstück", 200),
      ("Mittag", 700), ("Abendbrot", 500))))

  before {
    tracker = new CalorieTracker()
  }

  // Test caloriesByPerson function
  test("caloriesByPerson should return the total calories consumed by each person") {
    val result = tracker.caloriesByPerson(calories)
    val expected = Map(
      "Donald Duck" -> 10670,
      "Dagobert Duck" -> 6700
    )
    assert(result == expected)
  }

  // Test dayWithMaxCalories function
  test("dayWithMaxCalories should return the day with the maximum calorie intake") {
    val result = tracker.dayWithMaxCalories(calories)
    val expected = ("Donald Duck", "2022-01-04", 2650)
    assert(result == expected)
  }

  // Test caloriesByMeal function
  test("caloriesByMeal should return the total calories consumed for each meal") {
    val result = tracker.caloriesByMeal(calories)
    val expected = Map(
      "Frühstück" -> 5450,
      "Mittag" -> 6150,
      "Snack" -> 3250,
      "Abendbrot" -> 2520
    )
    assert(result == expected)
  }

}
