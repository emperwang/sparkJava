package base.matchOpr

object ListMatch {
   def main(args:Array[String]):Unit={
     var ll = List("--ip","192.168.30.10","--host","name2")
     parse(ll)
   }

  def parse(args: List[String]):Unit = args match{
    case ("--ip" | "-i"):: value :: tail =>
      println("ip= " + value)
      parse(tail)

    case("--host"|"-h") :: value :: tail =>
      println("host="+value)
      parse(tail)

    case Nil =>  println("match nothing")// No-op

    case _ => println("nothing ")
  }

}
