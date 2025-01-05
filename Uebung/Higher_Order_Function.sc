//1.  create a func that accepts another func
def applyTwice(func:Int =>Int , x : Int): Int = func(func(x))

def increment(x:Int): Int = x + 1
applyTwice(increment,5) // 7
def double(x:Int): Int = x * 2
applyTwice(double , 3) // 12

def square(x:Int): Int = x * x
applyTwice(square , 2)

// 2. Implement a Function to Transform a List:
def transform ( lst : List [ Int ] , f : Int => Int ) : List [Int] = lst.map(f)
val list = List(1,2,3)
transform(list, increment) // List(2,3,4)

transform(list, double) // List(2,4,6)
transform(list, square) // List(1,4,9)

//3. Combine Two Functions:
def compose[A, B, C](f: A => B, g: B => C): A => C = g compose f

compose(increment, double)(3) // 8
compose(double, increment)(3) // 7
compose(square, increment)(3) // 16

