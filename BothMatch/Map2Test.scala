// EXERCISE 3: is an instance of a more general bothMatch pattern. Write a
// generic function map2, that combines two Option values using a binary function.
// If either Option value is None, then the return value is too.

object Map2Test { 
	def main(args: Array[String]) {
		val result = map2(Some(1), Some("1"))((x, y) => List(x.toString,y))
		println(result)

		val result2 = map2(Some(2), None)((x, y) => List(x.toString,y))
		println(result2)
	}

	def map2[A,B,C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = (a, b) match {
		case (None, _) => None
		case (_, None) => None
		case (Some(x), Some(y)) => Some(f(x,y))
	}
}