import scala.annotation.tailrec

/*
* Recursive Practice Exercises
1 Introduction
This document provides a list of recursive exercises for practicing various recur-
sive algorithms and building problem-solving skills.
*/
/*
1. Factorial Calculation: Write a function to calculate the factorial of a
number using recursion.



*/

def factorial(n: Int): Int = {
  if(n==0) 1 else n * factorial(n-1)
}

factorial(3)

  /*
2. Fibonacci Sequence: Implement a recursive function to find the n-th
Fibonacci number.*/

def fibonacci(n:Int): Int = {
  if(n==0)0
  else if(n == 1) 1
  else fibonacci(n -1 )+ fibonacci(n -2)
}

fibonacci(5)

/*
3. String Reversal: Write a recursive function to reverse a given string.

*/
def reverseString(s:String):String = {
  if(s.isEmpty) s
  else reverseString(s.tail) + s.head
}

/*
4. Palindrome Check: Create a function that checks if a string is a palin-
drome using recursion.

*/
@tailrec
def isPalindrome(s:String):Boolean = {
  if(s.length<=1) true
  else if(s.head != s.last) false
  else isPalindrome(s.tail.init)
}


isPalindrome("racecar")


/*
5. Sum of Array Elements: Write a function that calculates the sum of
all elements in an array or list recursively.*/

def sumArray(arr: Array[Int]): Int = {
  if(arr.isEmpty) 0 else arr.head + sumArray(arr.tail)
}
val arr = Array(1 , 23 ,4 ,4)
sumArray(arr)



/*
6. Power Calculation: Implement a recursive function to calculate ab for
given base a and exponent b.*/
def powerCalculate(a: Int , b: Int): Int ={
  if(b==0) 1 else a * powerCalculate(a , b-1)
}

powerCalculate(2,3)


/*
7. Greatest Common Divisor (GCD): Use recursion to find the GCD of
two integers using the Euclidean algorithm.

*/
def gcd(a:Int, b: Int):Int ={ if(b==0) a else gcd(b,a%b)}

gcd(48,18)

/*
8. Binary Search: Implement a recursive binary search function to find a
target element in a sorted array.
*/

def binarySearch(arr: Array[Int] , target : Int , low : Int , high : Int): Int = {
  if(low > high) -1
  else {
    val mid = (low + high)/2
    if(arr(mid) == target) target
    else if( target < arr(mid)) binarySearch(arr , target , low , mid-1)
    else binarySearch(arr , target , mid + 1 , high)
  }
}
 val arr_1 = Array(1 ,3 , 5 ,7 ,9)
binarySearch(arr , 5 , 0 , arr.length-1)

// Define test cases
val testCases = Seq(
  (5, "Expecting index 2"),     // 5 is at index 2
  (9, "Expecting index 4"),     // 9 is at index 4
  (15, "Expecting index 7"),    // 15 is at index 7
  (2, "Expecting -1 (not found)"), // 2 is not in the array
  (8, "Expecting -1 (not found)")  // 8 is not in the array
)

for ((target, description) <- testCases) {
  val result = binarySearch(arr_1, target, 0, arr_1.length - 1)
  println(s"Searching for $target: $description => Result: $result")
}




/*
9. Tower of Hanoi: Solve the Tower of Hanoi puzzle using recursion.
*/
def towerOfHanoi(n: Int , source:String , destination:String, auxiliary:String): Unit = {
  if(n==1) s"Move disk 1 from $source to $destination"
  else {
    towerOfHanoi(n-1 , source , auxiliary , destination)
    println(s"Move disk $n from $source to $destination")
    towerOfHanoi(n-1 , auxiliary , destination , source)
  }
}

towerOfHanoi(2 , "A" ,"B" ,"C")

/*
10. Count Occurrences: Write a recursive function to count occurrences of
a character in a string.
*/

def countCaracter(s:String , c:Char):Int = {

  if(s.isEmpty) 0
  else if(s.head == c) 1 + countCaracter(s.tail , c)
  else countCaracter(s.tail, c)
}

countCaracter("banana", 'a')





/*
11. Subsets of a Set: Generate all possible subsets of a set recursively.
*/

def generateSubsets[T](set:Set[T]): Set[Set[T]]= {
  if(set.isEmpty) Set(Set())
  else {
    val element = set.head
    val subsetWithoutElement = generateSubsets(set.tail)
    subsetWithoutElement ++ subsetWithoutElement
  }
}
val inputSet = Set(1, 2, 3)
val allSubsets = generateSubsets(inputSet)
println("All subsets: " + allSubsets)


/*
12. Sum of Digits: Write a recursive function that calculates the sum of
digits in a given number.1

*/

def sumOfDigits(n: Int): Int = {
  if(n==0) 0 else n%10 + sumOfDigits(n/10)
}

sumOfDigits(12345)



/*
13. Generate Permutations: Write a function to generate all permutations
of a string or list.
*/

def generatePermutations[T](input: List[T]):List[List[T]] = {
  if (input.length <= 1) List(input)
  else input.flatMap{ elem => generatePermutations(input.filterNot( _ == elem)).map{
    perm => elem :: perm
  }
  }
}

val inputList = List(1, 2, 3)
val permutations = generatePermutations(inputList)
permutations

/*
14. Find Minimum in Array: Use recursion to find the minimum element
in an array.

*/
def findMinArray(arr: Array[Int]): Int = {

  if(arr.length == 0) arr(0)
  else math.min(arr(0),findMinArray(arr.tail))
}
val array = Array(5, 3, 7, 1, 4)
findMinArray(array)

/*
15. Binary Tree Traversals: Implement recursive functions for inorder,
preorder, and postorder traversals of a binary tree.
16. Find Depth of a Binary Tree: Write a recursive function to determine
the maximum depth of a binary tree.
17. Check Balanced Parentheses: Use recursion to check if a string with
parentheses is balanced.
18. Find Paths in a Grid: Write a recursive function to count all possible
paths from the top-left to the bottom-right corner of a grid.
19. Recursive Merge Sort: Implement merge sort recursively to sort an
array.
20. Count Ways to Reach Step: Count the ways to reach the top of a
staircase with n steps if 1 or 2 steps can be taken at a time.
* */