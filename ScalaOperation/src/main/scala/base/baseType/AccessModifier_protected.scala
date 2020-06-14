package base.baseType

object AccessModifier_protected {
  def main(args:Array[String]):Unit={

  }
}

class Super{
    protected def f(): Unit = {
      println("f function")
    }
    class Sub extends Super{
      f();
    }

    class Other{
      (new Super).f();
    }
  }
