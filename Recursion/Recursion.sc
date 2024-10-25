/*If we list all the natural numbers below 10 that are multiples
of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is
23.
Write a function that finds the sum of all multiples of 3 and 5
for a given value.*/

def sumMultiplesOf3And5(limit: Int): Int = {
  (1 until limit).filter(n => n % 3 == 0 || n % 5 == 0).sum
}

// Example usage
val result = sumMultiplesOf3And5(10)
println(s"Sum of multiples of 3 or 5 below 10: $result")

def isGoodEnough(x: Double, estimated: Double):Boolean = (estimated * estimated - x).abs<0.000001

def improve(x: Double, estimate: Double): Double = (x/estimate)/2

def iter_sqrt(x:Double , estimate:Double):Double = {
  if isGoodEnough(x,estimate)then estimate else iter_sqrt(x,improve(x,estimate))
}





//Calculation of the 10001 prime number

def isPrime(n:Int): Boolean = {
  if (n<=1)false
  else if(n==2) true
  else if (n%2 ==0) false
  else {
    var divisor = 3
    while(divisor * divisor <= n){
      if(n%divisor == 0) return false
      divisor +=2
    }

    return true
  }
}


def findNthPrime(n:Int): Int = {
  var count = 0
  var candidate = 1
  while (count < n )
    {candidate += 1
    if (isPrime(candidate)) count += 1}
  candidate
}


val nthPrime = findNthPrime(10001)
println(s"the 10001st prime number is: $nthPrime")


// Patter Matching
def multiple2(y:Double):Double = y match {
  case 0 => 0
  case _ if ((y % 3 ==0) || (y % 5 ==0)) => (y + multiple2(y-1))
  case _ if (y>0) => multiple2(y-1)
  case _ => throw new Error("No negative Numbers!")
}// Example usage
val r1 = multiple2(10)
println(s"Sum of multiples of 3 or 5 below 10: $r1")