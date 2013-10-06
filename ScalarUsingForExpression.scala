object ScalarUsingForExpression  {
	
	def scalarProduct(xs: List[Double], ys: List[Double]) : Double = {
		val result = for {
			x <- xs
			y <- ys
		} yield (x*y)
		println(result)
		result.foldRight(0.0)(_ + _)
	}

	def main(args: Array[String]) {
		val xs = List(1.0, 2.0, 3.0)
		val ys = List(1.0, 1.0, 1.0)

		println("scalarProduct(xs, ys) : " + scalarProduct(xs, ys))
		//assert(scalarProduct(xs, ys) == 6)
	}

}