package base.baseType

class Outer{
  class Inner{
    private def f(): Unit ={
      println("f function");
    }
  class InnerMost{
    f();
  }
  }
  //private  关键字修饰的方法  仅在包含了成员定义的类或对象内部可见
  // 同样的规则还使用与内部类
  //(new Inner).f();
}

object AccessModifier {

}
