package base.packgeobject

/**
  * @author: ekiawna
  * @Date: 2021/3/5 18:08
  * @Description
  */
object GetPackageObj {

  def main(args:Array[String]):Unit = {
    // 使用包对象中定义的变量 和 方法
    println("name = " + name)
    packageSayHi()
  }
}
