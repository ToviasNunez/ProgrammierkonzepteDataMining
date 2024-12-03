
// Function to group a list of integers by their modulo value
def moduloMap(l: List[Int], mod_value: Int): Map[Int, List[Int]] = {
  l.groupBy(_ % mod_value)
}

val l = List(1, 4, 5, 7, 8, 9)

// Call the moduloMap function with a list and a modulo value
moduloMap(l, 3)

// Function to count the number of words of each length in a list of strings
def countLetters(l: List[String]): Map[Int, Int] = {
  l.groupBy(_.length) // group by word length
    .view.mapValues(_.size).toMap // count the number of words with each length
}

val w = List("Hallo", "das", "sind", "ein", "paar", "Wörter")

// Call the countLetters function with a list of words
countLetters(w)

// Alternative implementation of countLetters using foldLeft
def countLetters_v1(l: List[String]): Map[Int, Int] = {
  l.foldLeft(Map.empty[Int, Int]) { (acc, word) =>
    val length = word.length
    acc + (length -> (acc.getOrElse(length, 0) + 1))
  }
}

// Call the alternative countLetters function with a list of words
countLetters_v1(w)

// Function to calculate the average of even and odd numbers in a list
def avgNumbers(l: List[Int]): Map[Boolean, Double] = {
  val (even, odd) = l.partition(_ % 2 == 0) // partition into even and odd
  Map(false -> odd.sum.toDouble / odd.size, true -> even.sum.toDouble / even.size)
}

val l2 = List(1, 4, 5, 7, 8, 9)

// Call the avgNumbers function with a list of numbers
avgNumbers(l)

// List of tuples representing work hours per week for different people
val stundenProtokoll: List[(String, Int, List[Int])] = List(
  ("Hans", 1, List(7, 9, 4, 12, 8)),
  ("Hans", 2, List(8, 2, 10, 12, 12)),
  ("Hans", 3, List(8, 8, 8, 7, 9)),
  ("Hans", 4, List(8, 9, 10, 9, 8)),
  ("Monika", 1, List(6, 9, 8, 7, 8)),
  ("Monika", 2, List(7, 9, 8, 6, 9)),
  ("Monika", 3, List(12, 9, 12, 8, 7)),
  ("Monika", 4, List(6, 9)),
  ("Kevin", 1, List(6, 9, 8, 7, 8)),
  ("Kevin", 2, List(7, 8, 8, 7, 9)),
  ("Kevin", 3, List(12, 3, 12, 3, 2)),
  ("Kevin", 4, List(12, 3))
)

// Function to find the maximum work done in a single week
def maxWorkPerWeek(l: List[(String, Int, List[Int])]): (String, Int, Int) = {
  l.flatMap { case (name, week, hours) =>
    hours.map(hour => (name, week, hour))
  }.maxBy(_._3)
}

// Call the maxWorkPerWeek function with the work hours list
maxWorkPerWeek(stundenProtokoll)

// Function to find the person with the maximum total work hours
def maxWork(l: List[(String, Int, List[Int])]): (String, Int) = {
  l.map { case (name, week, hours) => (name, hours.sum) }.maxBy(_._2)
}

// Call the maxWork function with the work hours list
maxWork(stundenProtokoll)

def daylyMax(l: List[(String, Int, List[Int])]): (Int, List[String]) = {
  val maxHours = l.flatMap { case (name, week, hours) => hours }.max
  val names = l.collect {
    case (name, week, hours) if hours.contains(maxHours) => name
  }.distinct
  (maxHours, names)
}

daylyMax(stundenProtokoll)