sealed trait Either[+E, +A] {
	def map[B](f: A => B): Either[E, B] =  this match {
		case Right(y) => Right(f(y))
		case Left(e) => Left(e)
	}

	def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = this match { 
		case Right(y) => f(y)
		case Left(e) => Left(e)
	}
}

case class Left[+E](err: E) extends Either[E,Nothing]
case class Right[+E](msg: E) extends Either[Nothing,E]