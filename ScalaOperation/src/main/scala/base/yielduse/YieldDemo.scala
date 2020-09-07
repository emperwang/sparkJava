package base.yielduse

object YieldDemo {
   def main(aegs:Array[String]): Unit = {
     val valus = Seq(1,2,3,4,5)
     val jobs = for{
       vv <- valus
     } yield {
       //println(s"values = ${vv}")
       vv
     }
     jobs.map(print)
   }

}
