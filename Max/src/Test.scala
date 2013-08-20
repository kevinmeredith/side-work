object Test {
  def main(args: Array[String]) {
    val list: List[Double] = List(1.0, 2.0, 3.0, 4.0)
	def f(a: Double, b: Double) : Boolean = if(a > b) true else false
	val maxVal = Max.max(list)(f)
	println("maxVal of " + list + " = " + maxVal)
	
	val list2: List[Int] = List(1, 55, 1000, -33)
	def f2(a: Int, b: Int) : Boolean = if(a > b) true else false
	val maxVal2 = Max.max(list2)(f2)
	println("maxVal of " + list2 + " = " + maxVal2)
  } 
}