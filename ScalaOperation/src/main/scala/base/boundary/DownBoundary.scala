package base.boundary

/**
  * 下界
  * 1. java 下界  <T super A>   <? super A>
  * 2. scala 下界  [T >: A]  [_ >: A]
  */
object DownBoundary {
  def main(args:Array[String]):Unit = {
    // 满足下界的约束
    // 下界是 Animal, 而 Earth 是 Animal的父类
    biophony(Seq(new Earth, new Earth)).map(_.sound())

    // 满足约束
    biophony(Seq(new Animal, new Animal)).map(_.sound())

    // animal的子类
    biophony(Seq(new Bird, new Bird)).map(_.sound())

    // 无关的类
    biophony(Seq(5,6,7)).map(x => {
      println(x.getClass.getSimpleName)
    })
  }

  /**
    * 1. 和 Animal 直系等的,是Animal父类的还是父类处理, 是Animal子类的按照Animal处理
    * 2. 和Animal无关的,一律按照Object 处理
    */
  def biophony[T >: Animal](thing: Seq[T])= thing

  class Earth{
    def sound(): Unit ={
      println("hello earth")
    }
  }

  class Animal extends Earth {
    override def sound(): Unit = {
      println("animal sound")
    }
  }

  class Bird extends Animal {
    override def sound(): Unit = {
      println("bird sounds.")
    }
  }

}
