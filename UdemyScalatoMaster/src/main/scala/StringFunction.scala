class StringFunction :
  var myPet = "crocodile"
  val myName = "michelle"
  val myAnimal = "Giraffe"
  val mySpace = s"   String with space from: :  $myName "

  // access to particular caracteres

  // String interpolation  add value into my string / variable
   // 3 type s , f , raw
    // expression start with s " String examples $variable "
    // expression ${ 1 + 3 }
    // user raw

    var cat = "my cat name is \" Fluffy\" "
    val myCar = "my car wont start"

    val apple = 3
    val orange = 5

   val client = "mary"
   var product: Int = 0
   var price: Int = 0;

  def billCalculation(a: Int, b: Int): Int =
    var total = a * b
    total
   // multiline String  \n separator

   // """ operator
  var str2  =
  """
    |this is a multiline
    string
    that was create
    |""".stripMargin

end StringFunction

