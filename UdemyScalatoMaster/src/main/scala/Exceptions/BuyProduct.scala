class BuyProduct:
  var productName = ""
  var amount = 0
  val price = 9.99
  var totalPrice = 0.0
  def enterProductName(input: String) = {
        try {
            productName = input
        } catch {
            case ex: Exception => ex.printStackTrace()
        }finally {println(" execution is done")}
        
  }
    
   def enterProductAmount(input: String) = {
        try {
            amount = input.toInt
            totalPrice = amount * price
        } catch {
            case f: NumberFormatException => f.printStackTrace()
            case ex: Exception => ex.printStackTrace()
        }finally {println(" execution is done")}
        
  }
     
end BuyProduct