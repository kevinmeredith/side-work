trait Either[+E, +A] {
	def map[B](f: A => B): Either[E, B] =  this match {
		case Either(x: E, y: A) => Right(f(y))
		case _ => Left()
	}
}