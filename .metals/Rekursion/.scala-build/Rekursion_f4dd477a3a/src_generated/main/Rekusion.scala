

final class Rekusion$_ {
def args = Rekusion_sc.args$
def scriptPath = """Rekusion.sc"""
/*<script>*/
/* 
If we list all the natural numbers below 10 that are multiples
of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is
23.
Write a function that finds the sum of all multiples of 3 and 5
for a given value.
 */

 def sumMultipleOf3And5(limit: Int): Int = {
    (1 until limit).filter(n => n % 3 == 0 || n % 5 == 0).sum
 }

 val result = sumMultipleOf3And5(10)
 println(s"Sum of multiples of 3 or 5 below 10 : $result")
/*</script>*/ /*<generated>*//*</generated>*/
}

object Rekusion_sc {
  private var args$opt0 = Option.empty[Array[String]]
  def args$set(args: Array[String]): Unit = {
    args$opt0 = Some(args)
  }
  def args$opt: Option[Array[String]] = args$opt0
  def args$: Array[String] = args$opt.getOrElse {
    sys.error("No arguments passed to this script")
  }

  lazy val script = new Rekusion$_

  def main(args: Array[String]): Unit = {
    args$set(args)
    val _ = script.hashCode() // hashCode to clear scalac warning about pure expression in statement position
  }
}

export Rekusion_sc.script as `Rekusion`

