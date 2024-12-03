// map --> the map function transformation of each element of a collection according to given function
// signature : def map [B](function: A=> B): List[B] , it takes the functions that transforms an element
// of type A into type B and applies this functions to every element in to collection

val numbers = List(1,2,3,4,5)
val doubled = numbers.map(x => x*2 )


//  filter--> functions filters elements in a collection based on a predicate functions, it keeps elements for which the predicate return true
// signature : def filter(predicate: A => Boolean): List[A]
// it takes a predicate function p (which return Boolean) and keep only elements for which the predicate is true

val evenNumbers = numbers.filter(x=> x%2==0 )


// flatMap---> : the flatMap function is combination of map and flatten. it applies a function to each element and then flatten the result
// signature : def flatMap[B](func: A => GenTraversableOnce[B]): List[B]
// it takes a function f that producer a collection of element for each input element and the flattens all those collection into a single collection
val result = numbers.flatMap(x=>List(x, x*2 ))


// foldLeft: the foldLeft function is used to accumulate result by applying a binary operator( a function that take two argument)
  // to the elements of a collection from left to right
  // Signature : def foldLeft[B](z:B)(op:(A,B) => B):B
  /*
  * it takes a initial value z (which is of type B) and a function op that combines the accumulation and the current element , the result
   of each step becomes the accumulator for the next step
  * */

  val sum = numbers.foldLeft(0)((acc,x) => acc + x)

//fold --> the fold function is similar to foldLeft but work on both sides (left , right), it is more general that foldLeft
// since it can handle both directions
// signature def fold[A1 >: A](z: A1)(op: (A1,A1) => A1):A1
// this combines the elements of the collection using the operator op and the initial value z

val sum1 = numbers.fold(0)((acc,x) => acc + x)

/*
* reduce --> the reduce function is like fold , but it doesn't require an initial value, it use the first element of the collection
as initial accumulator and the applies the function to combine it with the rest of the element
signature: def reduce[A1 >: A](op:(A1,A1) => A1):A1
it applies the function op to the element of the collection to reduce them to single value
* */

numbers.reduce((acc, x) => acc + x)