package implementation

class CalorieTracker {

  // a) dayWithMaxCalories: Find the day with the maximum total calories
  def dayWithMaxCalories(l: List[(String, String, List[(String, Int)])]): (String, String, Int) = {
    l.map { case (person, day, meals) =>
      val totalCalories = meals.foldLeft(0)((acc, meal) => acc + meal._2)  // Sum calories for the day
      (person, day, totalCalories)  // Return a tuple (person, day, totalCalories)
    }.maxBy(_._3)  // Get the tuple with the maximum calorie count (based on the totalCalories)
  }

  // b) caloriesByMeal: Calculate total calories for each type of meal
  def caloriesByMeal(l: List[(String, String, List[(String, Int)])]): Map[String, Int] = {
    l.flatMap { case (_, _, meals) =>
      meals  // Flatten the list of meals from each day
    }.foldLeft(Map.empty[String, Int]) { (acc, meal) =>
      val (mealType, calories) = meal
      acc + (mealType -> (acc.getOrElse(mealType, 0) + calories))  // Aggregate the calories for each meal type
    }
  }

  // c) caloriesByPerson: Calculate total calories consumed by each person
  def caloriesByPerson(l: List[(String, String, List[(String, Int)])]): Map[String, Int] = {
    l.foldLeft(Map.empty[String, Int]) { (acc, entry) =>
      val (person, _, meals) = entry
      val totalCalories = meals.foldLeft(0)((acc, meal) => acc + meal._2)  // Sum calories for the day
      acc + (person -> (acc.getOrElse(person, 0) + totalCalories))  // Add the daily total to the person's total calories
    }
  }
}

object CalorieTrackerApp extends App {
  // Sample data
  val calories: List[(String, String, List[(String, Int)])] = List(("Donald Duck", "2022-01-01",
    List(("Frühstück",800), ("Mittag", 700), ("Snack",200), ("Abendbrot", 500))),
    ("Donald Duck", "2022-01-02",List(("Frühstück",700), ("Mittag", 650), ("Abendbrot", 520))),
    ("Donald Duck", "2022-01-03",List(("Frühstück",800), ("Mittag", 700),
    ("Snack",200), ("Abendbrot", 500), ("Snack",150))),("Donald Duck",
    "2022-01-04",List(("Frühstück",850), ("Mittag", 900), ("Snack",500),
    ("Snack", 400))),("Donald Duck", "2022-01-05",List(("Frühstück",600),
    ("Mittag", 700), ("Snack",200), ("Abendbrot", 100))), ("Dagobert Duck",
    "2022-01-01",List(("Frühstück",300), ("Mittag", 500), ("Snack",100),
    ("Abendbrot", 200))), ("Dagobert Duck", "2022-01-02",
    List( ("Frühstück",200), ("Mittag", 300), ("Snack",400), ("Abendbrot",
      200))), ("Dagobert Duck", "2022-01-03",List(("Frühstück",800),
    ("Mittag", 700), ("Snack",200), ("Snack", 200))), ("Dagobert Duck",
    "2022-01-04",List(("Frühstück",200), ("Mittag", 300), ("Snack",200),
    ("Snack", 500))), ("Dagobert Duck", "2022-01-05",List(("Frühstück",200),
    ("Mittag", 700), ("Abendbrot", 500))))

  // Create an instance of the CalorieTracker
  val tracker = new CalorieTracker()

  // Test dayWithMaxCalories
  println(tracker.dayWithMaxCalories(calories))  // Should print the person and day with the max calories

  // Test caloriesByMeal
  println(tracker.caloriesByMeal(calories))  // Should print the total calories for each meal type

  // Test caloriesByPerson
  println(tracker.caloriesByPerson(calories))  // Should print the total calories for each person
}
