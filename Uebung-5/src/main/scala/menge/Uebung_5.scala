package menge

object Uebung_5:



  /*
  * a) Gegeben sei eine Liste von beliebigen Zahlen. Schreiben Sie eine Funktion, die mittels
  eines Aggregationsoperators den Durchschnitt aller geraden Zahlen und den Durchschnitt
  aller ungeraden Zahlen. Dabei soll nur einmal durch die Liste gegangen werden.
  * */

  def avgEvenOdd(numbers: List[Int]): (Double, Double) = numbers match
    case Nil => (0.0, 0.0) // Base case: empty list results in zero averages
    case _ =>
      val (evens, odds) = numbers.partition(_ % 2 == 0) // Split numbers into even and odd
      val evenAvg = evens match
        case Nil => 0.0 // If no even numbers, average is zero
        case _ => evens.sum.toDouble / evens.size // Calculate average of even numbers
      val oddAvg = odds match
        case Nil => 0.0 // If no odd numbers, average is zero
        case _ => odds.sum.toDouble / odds.size // Calculate average of odd numbers

      (evenAvg, oddAvg)

  /*
  * b) Schreiben Sie eine Funktion, die in einer Liste von Zahlen alle Werte dupliziert (nicht
  verdoppelt). Verwenden Sie dafür nur Higher Order Functions.
  * */

  def duplicateElements(numbers: List[Int]): List[Int] = numbers match
    case Nil => Nil // Base case: empty list remains empty
    case head :: tail => List(head, head) ++ duplicateElements(tail) // Duplicate each number and recurse

  /*
  * c) Gegeben seien die beiden folgenden Listen:
  val l1=List(1,2,3,4)
  val l2=List("a","b","c")
  Schreiben Sie eine Funktion, die aus den beiden Listen ein kartesisches Produkt bildet.
  Ergebnis soll eine Liste von Tupeln sein, deren erstes Element aus l1 kommt und deren
  zweites aus l2. Verwenden Sie dafür nur Higher Order Functions.
  * */

  def cartesianproduct[A, B](list1: List[A], list2: List[B]): List[(A, B)] = (list1, list2) match
    case (Nil, _) | (_, Nil) => Nil // Base case: if either list is empty, return empty list
    case (head1 :: tail1, _) =>
      list2.map((head1, _)) ++ cartesianproduct(tail1, list2) // Create tuples and recurse

  /*
  * Aufgabe 2 : Implementieren Sie die folgenden Aufgabenstellungen:
  a) Schreiben Sie eine Funktion moduloMap(l:List[Int], mod_value:Int):Map[Int,List[Int]], die
  aus einer Liste von Zahlen, eine Map erzeugt, deren Schlüssel ein Int-Wert ist, der sich aus
  der Modulo-Rechnung des Listenwertes mit mod_value ergibt. Zu den Schlüsselwerten
  werden dann alle Ints der Ausgangsliste innerhalb einer Liste gespeichert: z.B.:*/

  def moduloMap(numbers: List[Int], mod_value: Int): Map[Int, List[Int]] = numbers match
    case Nil => Map.empty // Base case: empty list results in an empty map
    case _ =>
      numbers.groupBy(_ % mod_value) match
        case grouped if grouped.isEmpty => Map.empty // If grouping is empty, return empty map
        case grouped => grouped // Return the grouped map

  /*b) Gegeben sei eine Liste von Wörtern. Schreiben Sie eine Funktion
  countLetters(l:List[String]):Map[Int,Int], die aus der Liste von Wörtern eine Map generiert, in
  der gespeichert wird, wie viele Wörter es mit einer entsprechenden Buchstabenzahl
  (Schlüssel) gibt: z.B.:
  val w=List("Hallo","das","sind","ein","paar", "Wörter")
  countLetters(w)
  ergibt: Map(5 -> 1, 3 -> 2, 4 -> 2, 6 -> 1)
  Benutzen Sie dafür nur eine Aggregationsfunktion.*/

  def countLetters(words: List[String]): Map[Int, Int] = words match
    case Nil => Map.empty // Base case: empty list results in an empty map
    case _ => words.groupBy(_.length).map { case (length, group) => length -> group.size }

  /*c) Wandeln Sie die Funktion so um, dass nicht die Anzahl der Wörter gespeichert wird,
  sondern die Wörter selbst. Benutzen Sie nur eine Aggregationsfunktion.*/

  def wordsByLength(words: List[String]): Map[Int, List[String]] = words match
    case Nil => Map.empty // Base case: empty list results in an empty map
    case _ => words.groupBy(_.length) // Group words by their length

  /*d) Schreiben Sie eine Funktion avgNumbers(l:List[Int]):Map[Boolean, Double]. Die Funktion
  soll aus der Liste die Durchschnittswerte der geraden und der ungeraden Zahlen bilden. Der
  Schlüsselwert soll dabei ein Boolean sein, der bei true alle geraden Werte zusammenfasst
  und false bei allen ungeraden: z.B.:
  val l2= List(1,4,5,7,8,9)
  avgNumbers(l) ergibt:
  Map(false -> 5.5, true -> 6.0)*/

  def avgNumbers(numbers: List[Int]): Map[Boolean, Double] = numbers match
    case Nil => Map(true -> 0.0, false -> 0.0) // Base case: empty list results in zero averages
    case _ =>
      numbers.groupBy(_ % 2 == 0).map { case (isEven, group) =>
        isEven -> group.sum.toDouble / group.size // Calculate average for each group
      }


end Uebung_5

