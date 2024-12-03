import implementation.CalorieTracker
import implementation.CalorieTrackerApp.calories

abstract class IntList{
  def isEmpty:Boolean
  def head:Int
  def tail:IntList
  def prefix(elem:IntList):IntList
  def flip:IntList
  def changeNumber(pred:Int=>Boolean, change: Int=>IntList):IntList
}
case object Empty extends IntList{
  def isEmpty = true
  def head= throw new Error ("List is Empty")
  def tail= throw new Error ("List is Empty")
  def prefix(elem:IntList):IntList= Empty
  def flip:IntList= Empty
  def changeNumber(pred:Int=>Boolean, change: Int=>IntList):IntList= Empty
}
case class Cons(head:Int, tail:IntList) extends IntList{
  def isEmpty= false
  def prefix(elem:IntList):IntList= elem match {
    case Empty => this
    case Cons(h,t) => Cons(h, prefix(t))
  }
  def flip:IntList= this match
    case Cons(h, t) => Cons(h,Empty).prefix(t.flip)

  def changeNumber(pred:Int=>Boolean, change: Int=>IntList):IntList= this match
    case Cons(head, tail) => Cons(head , tail.changeNumber(pred, change).prefix(change(head)))
   // case Cons(head, tail) => Cons(head, tail.changeNumber(pred,change))
}


val calories: List[(String, String, List[(String, Int)])] =
  List(("Donald Duck", "2022-01-01",List(("Frühstück",800),
    ("Mittag", 700), ("Snack",200), ("Abendbrot", 500))), ("Donald Duck", "2022-01-02",List(("Frühstück",700), ("Mittag", 650), ("Abendbrot", 520))),
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


Cons(1,Cons(2,Cons(3,Empty))).changeNumber(_%2==0, x=>Cons(x,Cons(x,Empty)))

object CalorieTrackerApp{

  def dayWithMaxCalories(l: List[(String, String, List[(String, Int)])]): (String, String, Int) =
    l.map((person, day, meals)  => (person,day,meals.foldLeft(0)((acc , meal) => acc + meal._2))).maxBy(_._3)

  def caloriesByMeal(l: List[(String, String, List[(String, Int)])]): Map[String, Int] =
    l.flatMap{case (_, _, meals) => meals }.foldLeft(Map.empty[String, Int]){(acc, meal) =>
      val (mealType, calories) =  meal
      acc + (mealType -> (acc.getOrElse(mealType,0) + calories))
    }

  def caloriesByPerson(l:List[(String, String, List[(String, Int)])]):Map[String,Int] =
    l.foldLeft(Map.empty[String, Int]){ (acc, entry) =>
      val (person, _, meals) = entry
      val totalCalories = meals.foldLeft(0)((acc, meal) => acc + meal._2)
      acc + (person -> (acc.getOrElse(person,0) + totalCalories))
    }




}


CalorieTracker().dayWithMaxCalories(calories)

CalorieTracker().caloriesByMeal(calories)

CalorieTracker().caloriesByPerson(calories)



/*
* Aufgabe 3:
Gegeben sei die folgende Map[String,List[Int]]:
val m= Map(1->List(2,3,4,5), 2->List(1,2,3), 3→List(4,5))
Berechnen Sie für alle map-Einträge die Durchschnittswerte der Listen. Verwenden Sie
dafür die map-Funktion.*/

val m= Map(1->List(2,3,4,5), 2->List(1,2,3), 3->List(4,5))

// calculate the average from each list:
val average = m.map{case(key,values) => key -> (values.sum.toDouble/values.length)}


