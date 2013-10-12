object MapWork {
	def squareList(xs: List[Int]) : List[Int] = xs match {
		case Nil => xs
		case y :: ys => y*y :: squareList(ys)
	}

	def squareListM(xs: List[Int]) : List[Int] = 
		xs map (x => x*x)

	def main(args: Array[String]) = {
		val l = List(1,2,3,4)
		val r1 = squareList(l)
		val r2 = squareListM(l)
		val expected = List(1,4,9,16)
		assert(r1 == expected)
		assert(r2 == expected)
		println("success")
	}
}