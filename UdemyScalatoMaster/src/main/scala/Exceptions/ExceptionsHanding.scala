class ExceptionsHanding():

    var number = 0
    def handlingException(input:String): Unit = {
        try {
        number = input.toInt
      } catch {
        case f: NumberFormatException => 
            println("Number Format Exception occurred")
            f.printStackTrace()
        case ex: Exception => 
            println("Exception occurred")
            ex.printStackTrace()
       } finally {
        println("The execution has completed")
      }
    }
   
end ExceptionsHanding
