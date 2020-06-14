package base.string

object Demo2 {
  val buf = new StringBuilder;
  def main(args:Array[String]):Unit={
    buf += 'a';    //添加字符
    buf ++="bcdef"; //添加字符串
    println("buf is "+buf)
  }
}
