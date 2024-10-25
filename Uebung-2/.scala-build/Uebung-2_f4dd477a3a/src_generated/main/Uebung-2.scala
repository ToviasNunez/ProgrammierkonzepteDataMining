

final class Uebung$minus2$_ {
def args = Uebung$minus2_sc.args$
def scriptPath = """Uebung-2.sc"""
/*<script>*/

// 1- 
def  or(x:Boolean, y: Boolean): Boolean = {if(!x) false else y }

or(true,false)
or(false,true)
or(true,true)

// 2-
def BigOrSmallZero(a: Integer): String = {
  if(a > 0) {
    "bigger as zero"
  } else if(a < 0) {
    "small than zero "
  } else {
    "egual zero"
  }
}

val result  = BigOrSmallZero(2)


//  3. sichbarkeit von variable
val x={
  val offset = 1
  {
    val x = 2
    val offset = 10
    x + offset
  } + {
    val x = 5
    x + offset
  }
}

// 4-
def squareUnder(x:Double, max:Double):Double = {
 var result = x
  while (result * result <= max){
    result *=result
  }
  result
}


var r1  = squareUnder(2,48)

def loop(start:Int,end:Int): Int = {
 val (s,e) = if(start > end) (end ,start) else (start,end)
  var sum = 0
  for (i <- s to e) {
    sum += i
  }
  sum

}


r1 = loop(9,0)

//5 - top-down Approach

def teilerTopDown(zahl: Int): Int = {
  for(i <- (zahl-1) to 1 by -1){
    if(zahl % i == 0){
      return i
    }
  }
  1
}

r1 = teilerTopDown(28)
println(s"teiler from $r1")


// Bottom-Up Approach
def teilerBottomUp(zahl: Int): Int = {
  var largestDivisor = 1  // Start with 1 as the smallest divisor
  for (i <- 2 until zahl) {
    if (zahl % i == 0) {
      largestDivisor = i  // Update the largest divisor
    }
  }
  largestDivisor
}

r1 = teilerBottomUp(28)
println(s"teiler 2 from $r1")

// uebung 6
def quersumme(zahl: Int): Int = {
  zahl.toString.map(_.asDigit).sum
}
/*
Die Funktion quersumme nimmt einen Parameter zahl: Int entgegen.
Die Methode .toString wandelt die Zahl in eine Zeichenkette um, sodass jede Ziffer der Zahl als einzelnes Zeichen dargestellt wird.
Die Methode .map(_.asDigit) wandelt jedes Zeichen wieder in eine Ziffer (eine Zahl) um.
Die Methode .sum summiert alle Ziffern der Zahl.
 */





r1 = quersumme(12345)

def quersumme1(zahl: Int): Int = {
  var num = zahl
  var sum = 0
  while (num != 0) {
    sum += num % 10  // Letzte Ziffer extrahieren und zur Summe hinzufügen
    num = num / 10   // Zahl um eine Ziffer reduzieren
  }
  sum
}
r1 = quersumme1(12345)

// 7-
def fibo(x:Int): Int= {
  if(x==0)0
 else if(x==1)1
 else fibo(x-1) + fibo(x-2)
}
/*
Die Funktion fibo nimmt einen Parameter x: Int entgegen, der die Position in der Fibonacci-Folge darstellt.
Wenn x gleich 0 ist, gibt die Funktion 0 zurück.
Wenn x gleich 1 ist, gibt die Funktion 1 zurück.
Für alle anderen Werte berechnet die Funktion die Summe der Fibonacci-Zahlen für x - 1 und x - 2 (rekursive Definition der Fibonacci-Zahlen).
 */

r1 = fibo(10)
def fibo1(x:Int): BigInt= {
  if(x==0)0
  else if(x==1)1
  else {
    var a : BigInt =0
    var b : BigInt =1
    var result : BigInt = 0
    for (i <- 2 to x){
      result = a + b
      a = b
      b = result
    }
    result

  }
}

var r2:BigInt = fibo1(100)


// 9 -


// function to check if a number is divisible by all numbers from 1 to x
def isDivisibleByAll(n: Int, x: Int) : Boolean = {
  //check divisibility for  all number from 1 to x
  (1 to x).forall(i => n % i == 0)
}

// function to find the smaller number dicisuble by all numbers from 1 to x
def smallestMultiple(x:Int): Int ={
  var number = x
  while(!isDivisibleByAll(number,x)){
    number +=1
  }
  number

}

// Test the function for X = 10 (should return 2520)
val result10 = smallestMultiple(10)
println(s"Smallest multiple for numbers 1 to 10: $result10")

// Test the function for X = 20 (should return 232792560)
val result20 = smallestMultiple(20)
println(s"Smallest multiple for numbers 1 to 20: $result20")
/*</script>*/ /*<generated>*//*</generated>*/
}

object Uebung$minus2_sc {
  private var args$opt0 = Option.empty[Array[String]]
  def args$set(args: Array[String]): Unit = {
    args$opt0 = Some(args)
  }
  def args$opt: Option[Array[String]] = args$opt0
  def args$: Array[String] = args$opt.getOrElse {
    sys.error("No arguments passed to this script")
  }

  lazy val script = new Uebung$minus2$_

  def main(args: Array[String]): Unit = {
    args$set(args)
    val _ = script.hashCode() // hashCode to clear scalac warning about pure expression in statement position
  }
}

export Uebung$minus2_sc.script as `Uebung-2`

