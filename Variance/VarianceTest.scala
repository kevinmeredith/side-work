// EXERCISE 2: Implement the variance function (if the mean is m, variance
// is the mean of math.pow(x - m, 2), see definition) in terms of mean and
// flatMap.

object VarianceTest { 
  def main(args: Array[String]) {
	val x = List(1.0, 2.0, 3.0, 4.0, 5.0)
	val variance: Option[Double] = Variance.variance(x)
	
	println("variance: " + variance.getOrElse(0))
  }
}