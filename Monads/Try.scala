// From Reactive Programming (Odersky course)
abstract class Try[T] {
	def flatMap[U](f: T => Try[U]): Try[U] this match {
		case Success(x) => try f(x) catch { case NonFatal(ex) => Failure(ex) }
		case fail: Failure => fail
	}

	def map[U](f: T => U): Try[U] = this match {
		case Success(x) => Try(f(x))
		case fail: Failure => fail
	}


	// t map f == t flatMap (x => Try(f(x))
	//  	   == t flatMap (f andThen Try)
}