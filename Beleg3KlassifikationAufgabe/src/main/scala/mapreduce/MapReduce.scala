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
    BasicOperations.mapReduce[Int,     // KeyIn
      (String, String, String, Int),  // ValueIn
      String,                         // KeyMOut
      Int,                            // ValueMOut
      String,                         // KeyROut
      Int ](
      kv => List((kv._2._2,1)), // kv._2._2 refers to the user name
      kv => List((kv._1,kv._2.sum)), // Sum values grouped by user name
      l
    )
  }

  /*
  Write a function that determines how many times a job name was used from each user
  Result: Map (key:(user,Job), value: number)
 */
  def numberOfJobsPerUserUsingACertainName(l:List[(Int,(String,String,String,Int))]):List[((String,String),Int)]={

    BasicOperations.mapReduce[Int,     // KeyIn
    (String, String, String, Int),  // ValueIn
    (String, String),               // KeyMOut (user, job name)
    Int,                            // ValueMOut (count)
    (String, String),               // KeyROut (user, job name)
    Int](
      kv => List(((kv._2._2,kv._2._3),1)), // kv._2._2 refers to the user name, kv._2._3 refers to the job name
      kv => List((kv._1,kv._2.sum)), // Sum values grouped by user name and job name
      l
    )


  }

  /*
    Write a function that determines all job names (without duplicates)
    Result: List(jobnames)
*/
  def distinctNamesOfJobs(l:List[(Int,(String,String,String,Int))]):List[String]={


    BasicOperations.mapReduce[Int,     // KeyIn
      (String, String, String, Int),  // ValueIn
      String,                         // KeyMOut (job name)
      Int,                            // ValueMOut (dummy count)
      String,                         // KeyROut (job name)
      Int                             // ValueROut (dummy count)
     ](
      kv=> List((kv._2._3,1)), // kv._2._3 refers to the job name
      kv => List((kv._1,1)), // Count the number of times a job name appears
      l
    ).map(_._1)


  }

  /*
    Write a function that determines how many jobs lasted more than 20sec
    Result: Map (key:("more" or "less"), value: number)
  */
  def moreThan20Seconds(l:List[(Int,(String,String,String,Int))]):List[(String,Int)]={

    BasicOperations.mapReduce[Int,     // KeyIn
    (String, String, String, Int),  // ValueIn
    String,                         // KeyMOut ("more" or "less")
    Int,                            // ValueMOut (count)
    String,                         // KeyROut ("more" or "less")
    Int](
      kv=> if(kv._2._4>20) List(("more",1)) else List(("less",1)), // kv._2._4 refers to the duration
      kv=> List((kv._1,kv._2.sum)), // Sum values grouped by "more" or "less"
      l
    )


  }

  /*
    Write a function that determines the number of were submitted per day
    Result: Map (key:day- format "YYYY-MM-dd" , value: number)
  */
  def numberOfJobsPerDay(l:List[(Int,(String,String,String,Int))]):List[(String,Int)]={

    val outDF= new SimpleDateFormat("yyyy-MM-dd")

    BasicOperations.mapReduce[Int,     // KeyIn
    (String, String, String, Int),  // ValueIn
    String,                         // KeyMOut (date in YYYY-MM-dd format)
    Int,                            // ValueMOut (count)
    String,                         // KeyROut (date in YYYY-MM-dd format)
    Int                             // ValueROut (total count)
    ](

      kv=> {
      val date = outDF.format(new SimpleDateFormat("yyyy.MM.dd").parse(kv._2._1.split(" ")(0)))
      List((date, 1)) // Map date to 1
      },
      kv=> List((kv._1,kv._2.sum)), // Sum values grouped by date
      l
    )
  }

}
