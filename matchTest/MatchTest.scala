object Test {
  def main(args: Array[String]) {
    val list: List[Double] = List(1.0, 2.0, 3.0, 4.0)
    val none = None

    case class Test()

    val test = Test()

    def f(x: Any) = x match {
    	case x: Some[Test] => println("_ matched")
    	case None => println("None matched")
    }

    f(list)
    f(none)
    f(test)
  }
}