//scala3

class Booleans:
  val isTake = true
  
  val expression_1 = 5374 * 95847

  val expression_2 = 3237 * 153726

  var comparation = expression_1 > expression_2
  

  def byToys(toys: Int, noBrockenToys: Int): String =
   val result = if (toys < 5 && noBrockenToys >= 1) then "buy toys" else "don't buy toys"
   result

   


  def fundingApplication(animal: Int , producer : Int , familyMember : Int) : String =
    val applied = if( (animal <= 5 || producer > 0) || (familyMember >=4) ) then "you can applied for funding" else "you don't calificated for funding"
    applied

   

end Booleans

