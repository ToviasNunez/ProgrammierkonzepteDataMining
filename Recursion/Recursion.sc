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
  case _ if (y > 0) => multiple2( y - 1)
  case _ => throw new Error("No negative Numbers!")
}// Example usage
val r1 = multiple2(10)
println(s"Sum of multiples of 3 or 5 below 10: $r1")


// Prime test
def primetest(n:Long):Boolean = {
  val endVal = (math.sqrt(n).toLong+1)
  def primeHelper(curVal: Int): Boolean = {
    if(curVal >= endVal)true
    else if (n% curVal == 0) false
    else primeHelper(curVal+1)
  }
  if(n==1) false else primeHelper(2)
}





val prime = findNthPrime(10001)
println(s"the 10001st prime number is: $prime")

// Prime counter
def calcNthPrim(n: Int): Long = {
  def calcHelper(n: Int, nr:Long): Long ={
    val p = primetest(nr)
    if(n<= 1 && p)nr
    else if(p) calcHelper(n-1,nr+1)
    else calcHelper(n, nr+1)

  }
  calcHelper(n,2)
}

val prim = calcNthPrim(10001)
println(s"the 10001st calc prime number is: $prim")


//how to get the prime numbers

def isPrime_1(n: Int): Boolean= {
  if(n<=1)false
  else if (n == 2)true
  else !(2 until Math.sqrt(n).toInt +1).exists( i => n%i == 0)

}

def listOfPrimes(limit:Int ): List[Int] = {
  (2 to limit).filter(isPrime_1).toList
}

val primes = listOfPrimes(50)
println(primes)




// Function to calculate the nth prime number
def calcNthPrim_v1(n: Int): Long = {

  // Helper function that checks each number for primality and counts primes
  def calcHelper(primeCount: Int, candidate: Long): Long = {
    // Check if the current number (candidate) is prime
    if (primetest(candidate)) {
      // If primeCount is 1, we've found the nth prime
      if (primeCount == 1) candidate
      else calcHelper(primeCount - 1, candidate + 1)  // Decrement primeCount and continue
    } else {
      // If not prime, move to the next number
      calcHelper(primeCount, candidate + 1)
    }
  }

  // Start searching from 2 (the first prime number)
  calcHelper(n, 2)
}

val pri = calcNthPrim_v1(10001)
println(s"the 10001st calc prime number is: $pri")
