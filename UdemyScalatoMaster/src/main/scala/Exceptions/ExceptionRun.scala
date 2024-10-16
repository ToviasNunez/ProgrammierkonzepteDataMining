import scala.io.StdIn


@main
def ExceptionRun(): Unit = {
 /* println("Enter age")
    val input = StdIn.readLine()
    
   /*  throw IllegalStateException(" I don't like this input")  // this will happend first  */

    val exceptionsHanding = new ExceptionsHanding()
    exceptionsHanding.handlingException(input)
    println(s"the ${exceptionsHanding.number} was multiplice for 5 = ${exceptionsHanding.number * 5}")
 */  

 /*
    println("Enter ur Kilometer")
    val input = StdIn.readLine()
     val userMilles = new UserMilles()
     userMilles.convertKilometerToMilles(input)
     println(s"The user made $input km that is on mille : ${userMilles.milles}")
    */
 
    val buyProduct = new BuyProduct()


    println("Enter the product name")
    val productName = StdIn.readLine()
     buyProduct.enterProductName(productName)

    println("Enter the Amount of the product")
    val productAmount = StdIn.readLine()
    buyProduct.enterProductAmount(productAmount)

    println(s"The product : $productName amount $productAmount total price" +
      s"${buyProduct.totalPrice}")
     

}
