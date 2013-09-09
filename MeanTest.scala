object MeanTest {
  def main(args: Array[String]) {
    val list: List[Double] = List(1.0, 2.0, 3.0, 4.0)
	val mean = Mean.mean(list)
	println("mean of " + list + " = " + mean)
	
	val emptyList = List[Double]()
	val mean2 = Mean.mean(emptyList)
	println("mean of " + emptyList + " = " + mean2)
  } 
}