package base.baseType

object VariableDemo {
  def main(args:Array[String]):Unit={
    //变量声明  可以进行修改
    var myVar : String = "foo";
    myVar = "Foo";
    println(myVar);

    //变量定义时  必须进行初始化   不然会报错
    //var noInit : String;
    //val noInit :String;

    //一次声明多个变量
    val xmax,ymax = 100;
    println(xmax)
    println(ymax)

    //这是个常量变量  不能修改
    val MyVal = "test";
    //MyVal = "test2";
    println(MyVal);
  }
}
