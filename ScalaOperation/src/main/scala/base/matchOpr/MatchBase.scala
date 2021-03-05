package base.matchOpr

object MatchBase {
  def main(args: Array[String]): Unit = {
    //matchd1()
    // 匹配守卫
    //matchGrade()
    // 参数匹配
    //matchParam()
    // 类型匹配
    //typeMatch()

    // 数组匹配
    //matchArray()

    // 列表匹配
    //matchList()

    // 匹配元素
    //matchTuple()

    // 匹配对象
    //matchObject()

    // 变量声明中的模式
    //paramMode()

    // for循环中的 匹配
    forMatch()
  }

  // for 循环中的 匹配
  def forMatch():Unit = {
    val map = Map("A"->1, "B"->0, "C"-> 3)
    for ((k,v) <- map){
      println(k + "-> " + v)
    }

    // 只遍历处 value=0 的key-value,其他的过滤掉
    println("-------------(k,0) <- map-----------------")
    for((k,0) <- map){
      println(k +" --> " + 0)
    }

    // 另外一种写法
    println("------(k,v) <- map if v==0")

    for((k,v) <- map if v>= 1){
      println(k +" --> " + v)
    }
  }

  /**
    * match 中每一个case都可以单独提取出来, 意思是一样的
    */
  def paramMode():Unit = {
     val (x, y,z) = (1,2,"hello")
    println(s"x= $x, y= $y, z=$z")

    // 其中q=BigInt/3   r = BigInit%3
    val (q,r) = BigInt(10)/%3
    println(s"q= $q, r= $r")

    val arr = Array(1,7,2,9)
    // 提取出 arr的前两个元素
    val Array(first, sec, _*) = arr
    println(s"first= $first, sec= $sec")

  }


  /**
    * 对象匹配
    * 1. case 中对象的unapply 方法(对象提取器) 返回some集合,则为匹配成功
    * 2. 返回None集合,则为匹配失败
    */
  def matchObject():Unit = {
     val number:Double = Square(5.0)

    /** note:
      *1. 当匹配到 case Square,调用Square.unapply(z:Double), z的值就是number
      * 2. 如果对象提取器unapply(z:Double) 返回的值是 Some,则表示匹配成功,并把值赋值为n
      * 3. 如果对象提取器unapply返回的是None,则表示匹配不成功
      */
    number match {
      case Square(n) => println("match, n = " + n)
      case _ => println("match nothing")
    }
  }

  object Square{
    /** note
      * 1. unapply 方法是对象的提取器
      * 2. 接收z:Double 类型
      * 3. 返回类型是 Option[Double]
      * 4. 返回的值是 Some(math.sqrt(z))  返回z的开平方的值,并放入some中
      */
    def unapply(z: Double): Option[Double] = {
      println("unapple 被调用 z= " + z)
      //Some(math.sqrt(z))
      None
    }

    def apply(z: Double): Double =  z * z
  }

  /**
    *  匹配 tuple
    */
  def matchTuple():Unit = {

    for ( pair <- Array((0,1), (1,0), (10,30), (1,1), (1,0,3))){
      val result = pair  match{
          // 匹配0开头的元祖
        case (0, _) => "0...."
          // 匹配 0 结束的元祖
        case (y,0) => y
          // 匹配两个元素的 tuple
        case (x,y) => (y,x)
          // 匹配三个元素的 tuple
        case (x,y,z) => (z,y,x)
        case _ => "other"
      }

      println(s"res =  $result")
    }
  }


  /**
    * 列表 匹配
    */
  def matchList():Unit= {
     for (ll <- Array(List(0),List(1,2), List(88), List(0,0,0), List(1,0,0))){
       val result = ll  match{
           // 匹配只有一个元素为0的list
         case 0::Nil => "0"
           // 匹配两个元素的list
         case x::y::Nil => x+" "+y
           // 匹配开头是0 的list
         case 0::tail => "0...."
           // 匹配只有一个元素的list
         case x::Nil => x
         case _ => "nothing"
       }

       println(s"result=   $result")
     }
  }

  /**
    * 数组匹配
    * 1. Array(0) 匹配只有一个元素且 为0的数组
    * 2. Array(x,y) 匹配数组有两个元素,并将两个元素赋值为x和y. 当然可以一次类推Array(x,y,z) 匹配数组有三个元素
    * 3. Array(0,_*) 匹配以0开始的数组, _* 表示剩余所有
    *
   */
  def matchArray():Unit = {
     val arrs = Array(Array(0), Array(1,0),
    Array(0,1,0), Array(1,1,0,1))
    for(vv <- arrs){

      val res = vv match{
        case Array(0) => "0"
        case Array(x,y) => x+"=" + y
        case Array(0, _*) => "begin with 0"
        case Array(x,y,w,z) => "four elements"
        case _ => "nothing"
      }
      println(s"res=  $res")
    }
  }


  /**
    * 1. Map[String, Int] 和Map[Int, String] 是两种不同的类型
    * 2. 在进行类型匹配时,编译器会预先检测是否有可能的匹配,如果没有则报错
    * 3. case i:Int => i  表示首先将 (i=obj)obj赋值给i, 之后再进行类型判断
    * 4. 如果case _ 出现在mathc中, 则表示隐藏变量名, 即不使用,而不是表示默认匹配
    */
  // 根据类型去进行匹配, 可以匹配任意类型
  def typeMatch(): Unit = {
    val a = 8
    // 说明 obj 实例的类型 根据a的 值来返回
    val obj = if (a == 1) 1
    else if(a == 2) "2"
    else if (a == 3) BigInt(3)
    else if(a == 4) Map("aa" -> 1)
    else if(a == 5) Map(1 -> "aa")
    else if(a == 6) Array(1,2,3)
    else if(a == 7 ) Array("aa", 1)
    else if(a == 8) Array("aa")
    // result 根据 obj的类型来匹配
    val result = obj match {
      case a:Int => a
      //case b:Map[String, Int] => "object is String- int map"
      //case c:Map[Int, String] => "object is int-string map"
      case d:Array[String] => d
      case e:Array[Int] => "object is int array"
      case f:BigInt => Int.MaxValue
      case _ => "nothing"
    }

    println(s"result=${result.toString}")
  }



  // 模式中使用变量
  // 如果在case 关键字后跟变量名, 那么match前表达式的值会赋值给那个变量
  def matchParam():Unit = {
    val ch = 'U'
    ch match {
      case '+' => println("Ok ~")
        // 这里的 case mychar 就是把 ch的值赋值给 mychar
      case mychar => println("ok ~ "+mychar)
      //case _ => println("ok~  match _")
    }

    // match 是一个表达式, 因此可以有返回值
    // 返回值就是匹配到的代码块的最后一句话的值
    //val ch1 = '+'
    val ch1 = '*'
    val res = ch1 match {
      case '+' => ch1 + " hello"
      case _ => println("ok ~")
    }
    println(s"res = $res")
  }

  // case中使用 条件守卫
  def matchGrade(): Unit ={
      for (ch <- "+-3"){
        var sign = 0
        var digit = 0
        ch match{
          case '+' =>  sign = 1
          case '-' => sign = -1
          /** note:
            *   如果case 后有条件守卫 即if, 那么这时的 _ 不是表示默认匹配
            *   表示忽略传入的 ch
            */
          case _ if ch.toString.equals("3") => digit = 3
          case _ if (ch > 110 || ch < 120) => println("ch > 10")
          case _ => sign = 2
        }
        println(s"digit=$digit and sign=$sign")
      }
  }

  /**
    * 1. 类似于java 中的switch, case是关键字
    * 2. 如果匹配成功,则执行 => 后面的代码块
    * 3. 匹配的顺序是从上到下, 匹配到一个就执行对应的代码
    * 4. => 后面的代码块, 不要写 break, 会自动的退出 match
    * 5. 如果一个都没有匹配到, 则执行 case _ 后面的代码块
    */
  def matchd1(): Unit= {
    val oper = "-"
    val n1 = 20
    val n2 = 10
    var res = 0

    oper match {
      case "+" => res = n1 + n2
      case "-" => res = n1 - n2
      case "*" => res = n1 * n2
      case "/" => res = n1 / n2
      case _ => println("match nothing")
    }
    println(s"res=$res")
  }


}
