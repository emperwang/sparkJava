package base.matchOpr

/**
  * 样例类
  * 1. 样例类仍然是类
  * 2. 样例类用 case 关键字 进行声明
  * 3. 样例类是为 模式匹配而优化的类
  * 4. 构造器中的每一个参数都成为val -- 除非它被显式的声明为 var(不建议这么做)
  * 5. 在样例类对应的伴生对象中提供apply方法 让你不用new关键字就能 构造处相应的对象
  * 6. 提供 unapply 方法让模式匹配可以工作
  * 7. 将自动生成 toString  equals  hashCode  copy
  * 8. 样例类和其他类完全一样, 你可以添加方法和字段, 扩展它们
  */
object CaseClassBase {
  def main(args:Array[String]) : Unit = {
    // 样例类匹配
    //matchCaseClass()

    // 样例类的copy
    //caseClassUse()

    // 中置表达式
    //midleOperator()

    // 匹配嵌套结构
    matchEmbedObj()

    // 密封类

  }

  /**
    *密封类
    * 1.  如果想让 case 类的所有子类都必须在声明该类的 相同源文件中定义,可以将样例类的通用超类声明为 sealed,
    *     这个超类就称之为  密封类
    * 2. 密封就是不能在其他文件中定义子类
    */
    abstract sealed class ItemSeal
  case class ItemSealClass() extends ItemSeal




  // 样例类
  abstract class Item
  case class Book(description:String, price:Double) extends Item
  case class Food(description:String, price:Double) extends Item
  // bundle 捆, discount 折扣, item:Item* 表示可变参数
  case class Bundle(description:String, discount:Double, item:Item*) extends Item
  // 嵌套结构匹配
  def matchEmbedObj():Unit = {
    val sal = new Bundle("book",10, Book("漫画",50),
      Bundle("文学", 20, Book("西湖", 80), Book("围城",40)))

    // 1.使用case语句得到 漫画
    val res = sal match{
      case Bundle(_,_,Book(desc,_),_*) => desc
    }
    println(s"res = $res")

    // 2. 通过@ 表示法将嵌套的值绑定到变量. _* 绑定剩余的 Item 到rest
    val res2 = sal match{
      case Bundle(_,_,art@Book(_,_), rest@_*) => (art, rest)
    }
    println("res2 = "+ res2)

    // 3. 不使用_* 绑定剩余Item 到 rest
    val res3 = sal match {
        // 进行匹配时, 不想接受某些值,则使用 _ 忽略即可, _* 表示所有
      case Bundle(_,_,art3@Book(_,_), rest3) => (art3, rest3)
    }
    println("rest3 = " + res3)
  }


  // 中置表达式
  def midleOperator():Unit = {
    List(1,3,5,7,9) match{
      /**
        * 1. 两个元素间 :: 叫中置表达式, 至少first seconds两个匹配才行
        * 2. fist 匹配第一个, seconds 匹配第二个, rest匹配剩余部分
        */
      case first::second::rest => println(s"first= $first, seconds= $second, res= $rest")
      case _ => println("match nothing")
    }
  }

  /**
    * 样例类的copy 方法和  带名参数
    * copy 创建一个与现有对象值相同的新对象, 并可以通过带名参数来修改
    */
  def caseClassUse():Unit = {
    val amt = new Currency(40000, "RMB")
    val amt2 = amt.copy() // 克隆, 闯进的对象和amt的属性一样
    println(s"amt = ${amt.toString}, amt2 = ${amt2.toString}")

    val amt3 = amt.copy(value = 50000)
    println(s"amt = ${amt.toString}, amt3 = ${amt3.toString}")
  }

  // 样例类的match匹配
  def matchCaseClass():Unit = {
    for (amt <- Array(Dollor(1000), Currency(1000,"RMB"), NoAmount)){
      val res = amt match{
        case Dollor(v) => "$" + v
        case Currency(v,u) => v+"   "+ u
        case NoAmount => "/./////"
        case _ => "match Nothing"
      }

      println(s"res =  $res")
    }
  }

  // 样例类
  abstract class Amount
  case class Dollor(value: Double) extends Amount // 样例类
  case class Currency(value:Double, unit:String) extends Amount // 样例类
  case object NoAmount extends Amount // 样例类

  // 类型(对象) == 序列化(serializable) ===字符串(1. 你可以保存到文件中[freeze]  2. 反序列化  3. 网络传输

}
