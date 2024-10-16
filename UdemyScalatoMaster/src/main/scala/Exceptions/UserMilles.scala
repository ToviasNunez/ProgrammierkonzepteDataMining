class UserMilles:
   
   var milles = 0.0
   
   def convertKilometerToMilles(kilometer: String ): Unit =
       try {
        milles = kilometer.toInt * 0.62
       } catch {
        case f: NumberFormatException => { f.printStackTrace()}
        case ex: Exception =>  { println("exception occurred") 
        ex.printStackTrace()}
       }finally {println("the execution has completed")}
       
end UserMilles