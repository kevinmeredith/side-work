import scala.math

object Variance { 
	def variance(xs: Seq[Double]): Option[Double] = xs match {
		case y  => val mean = Mean.mean(y)
					  println("mean: " + mean)
					  val a = y.flatMap(x => f(x, mean))
					  val b = a.map(x => math.pow(x, 2)).foldLeft(0.0)(_+_)
					  Some(b / y.length)
		case _  => None
	}
	
	def f(x: Double, mean: Option[Double]): Option[Double] = mean match {
		case Some(m: Double) => Some(x - m)
		case None => None
	}
}