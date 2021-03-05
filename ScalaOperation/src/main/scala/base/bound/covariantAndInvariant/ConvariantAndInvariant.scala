package base.bound.covariantAndInvariant

/**scala 的协变(+)  逆变(-)  协变 convariant  逆变 contravariant 不可变 invariant
  * C[+T]  如果 A是B的子类,那么 C[A] 是C[B]的子类, 称为协变
  * C[-T]  如果A是B的子类, 那么C[B] 是C[A] 的子类, 称为逆变
  * C[T]  无论A和B是什么关系, C[A] 和C[B] 没有从属关系,  称为不变
  */
object ConvariantAndInvariant {
  def main(args:Array[String]) :Unit = {
    val t1: Temp3[Sub] = new Temp3[Sub]("tempt3, no variant.")
    // error 不支持逆变
    //val t2:Temp3[Sub] = new Temp3[Super]("h supper.")
    // error
    //val t3:Temp3[Super] = new Temp3[Sub]("h sub.")

    val t4:Temp3[Sub] = new Temp3[Sub]("h temp3 sub")
    // 协变
    val t5:Temp4[Super] = new Temp4[Sub]("super with sub")
    // error
    //val t51:Temp4[Sub] = new Temp4[Super]("sub with super")

    // 逆变
    val t6:Temp5[Sub] = new Temp5[Super]("sub with super")
    // error
    //val t7:Temp5[Super] = new Temp5[Sub]("super with sub")
    //val t8:Temp5[Super] = new Temp5[Sub]("")

  }

  class Super
  class Sub extends Super

  // 协变
  class Temp4[+A](title:String) {
    override def toString: String = {
      title
    }
  }

  // 逆变
  class Temp5[-A](title:String) {
    override def toString: String = {
      title
    }
  }

  // 不变
  class Temp3[A](title:String){
    override def toString: String = {
      title
    }
  }
}
