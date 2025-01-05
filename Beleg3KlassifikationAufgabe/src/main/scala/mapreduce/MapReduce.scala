package mapreduce

import java.text.SimpleDateFormat
import java.util.Locale

object MapReduce {

  val dateFormatPattern= "yyyy.MM.dd G 'at' HH:mm:ss z"
  val sdf= new SimpleDateFormat(dateFormatPattern,Locale.ENGLISH)

  /*
    Write a function that determines how many jobs each user sumbmitted
    Result: Map (key:user, value: number)
   */
  def numberOfJobsPerUser(l:List[((Int,(String,String,String,Int)))]):List[(String,Int)]={
  ???
   /* BasicOperations.mapReduce[...](

      kv => ...,
      kv=> ...,
      l
    )*/
  }

  /*
  Write a function that determines how many times a job name was used from each user
  Result: Map (key:(user,Job), value: number)
 */
  def numberOfJobsPerUserUsingACertainName(l:List[(Int,(String,String,String,Int))]):List[((String,String),Int)]={
  ???
    /*
    BasicOperations.mapReduce[...](

      kv => ...,
      kv => ...),
      l
    )

     */
  }

  /*
    Write a function that determines all job names (without duplicates)
    Result: List(jobnames)
*/
  def distinctNamesOfJobs(l:List[(Int,(String,String,String,Int))]):List[String]={
    ???
    /*
    BasicOperations.mapReduce[...](

      kv=> ...,
      kv => ...,
      l
    ).map(_._1)

     */
  }

  /*
    Write a function that determines how many jobs lasted more than 20sec
    Result: Map (key:("more" or "less"), value: number)
  */
  def moreThan20Seconds(l:List[(Int,(String,String,String,Int))]):List[(String,Int)]={
    ???
    /*
    BasicOperations.mapReduce[...](

      kv=> ...,
      kv=> ...,
      l
    )

     */
  }

  /*
    Write a function that determines the number of were submitted per day
    Result: Map (key:day- format "YYYY-MM-dd" , value: number)
  */
  def numberOfJobsPerDay(l:List[(Int,(String,String,String,Int))]):List[(String,Int)]={
    ???
    /*
    val outDF= new SimpleDateFormat("YYYY-MM-dd")

    BasicOperations.mapReduce[...](

      kv=> ...,
      kv=> ...,
      l
    )*/
  }

}
