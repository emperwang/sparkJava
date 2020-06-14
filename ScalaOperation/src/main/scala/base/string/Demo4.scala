package base.string

object Demo4 {
  def main(args: Array[String]): Unit = {
    var string1 = "string1";
    string1 = string1.concat("string2");
    println(string1)

    var str1 = "manual net"
    var str2 = "www.runoob.com"
    var str3 = "slogan"
    var str4 = "for dream"
    println(str1+str2)
    println(str3.concat(str4))

  }
}
