package base.implicits

/**
  * @author: ekiawna
  * @Date: 2021/3/5 13:43
  * @Description
  */
class MySql{
  def insert(): Unit = {
    println("mysql insert.")
  }
}

class DB {
  def delete(): Unit = {
    println("db delete .. ")
  }
  def update(): Unit = {
    println("update...")
  }
}


object ImplicitFunction2 {

  def main(args: Array[String]):Unit= {
    // 编写一个隐式函数  丰富 MySql 功能
    implicit def addDelete(msql: MySql): DB = {
      new DB
    }

    val msql = new MySql
    msql.insert()
    msql.delete()
    msql.update()
  }
}
