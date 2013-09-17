sealed abstract class Stream[+A] {
	def uncons: Option[Cons[A]]
	def isEmpty: Boolean = uncons.isEmpty
	def toList: List[A] = {
		if (isEmpty) Nil
		else {
			uncons match { 
				case Some(x) => f(x)
				case _ => Nil
			}
		}
	}

	private def f[A](x: Cons[A]) : List[A] = {
		def go(x: Cons[A], list: List[A]): List[A] = x match {
			case x :: xs => go(xs, list ++ List(x))
			case _ => list
		}
	}
}

object Empty extends Stream[Nothing] {
	val uncons = None
}

sealed abstract class Cons[+A] extends Stream[A] {
	def head: A
	def tail: Stream[A]
	val uncons = Some(this)
}

object Stream {
	def empty[A]: Stream[A] = Empty

	def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = new Cons[A] {
		lazy val head = hd
		lazy val tail = tl
	}

	def apply[A](as: A*): Stream[A] =
		if (as.isEmpty) Empty else cons(as.head, apply(as.tail: _*))
}