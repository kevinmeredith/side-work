object PackFunction {
	
	// Put duplicates into a separate list
	def pack[T](xs: List[T]): List[List[T]] = {
		def go[T](ys: List[T], acc: List[List[T]]) : List[List[T]] = ys match {
			case Nil => acc
			case x :: xs_ => val r: List[T] = ys.takeWhile(a => a == x)
							 go(ys.drop(r.length), acc :+ r)
		}
		go(xs, List(Nil).filter(_ != Nil))
	}

	def main(args: Array[String]) = {
		val list = List("a", "a", "a", "b", "b", "c", "c", "c", "a")
		val r = pack(list)
		println("r : " + r)

		val r2 = pack(List("a", "a", "b", "b", "c", "a"))
		println("r2: " + r2)
	}

}