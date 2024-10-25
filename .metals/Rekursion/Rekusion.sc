/* 
If we list all the natural numbers below 10 that are multiples
of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is
23.
Write a function that finds the sum of all multiples of 3 and 5
for a given value.
 */

 def sumMultipleOf3And5(limit: Int): Int = {
    (1 until limit).filter(n => n % 3 == 0 || n % 5 == 0).sum
 }

 val result = sumMultipleOf3And5(10)
 println(s"Sum of multiples of 3 or 5 below 10 : $result")