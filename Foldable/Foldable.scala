trait Foldable[F[_]] {
	def foldRight[A, B](as: F[A])(f: (A, B) => B): B
	def foldLeft[A, B](as: F[A])(f: (B, A) => B): B
	def foldMap[A, B](as: F[A])(f: A => B)(mb: Monoid[B]): B
	def concatenate[A](as: F[A])(m: Monoid[A]): A = foldLeft(as)(m.zero)(m.op)
}

trait Foldable[List[_]] {
	def foldRight[A, B](as: List[A])(f: (A, B) => B): B = foldLeft(as.reverse)((s, i) => f(s, i))
	def foldLeft[A,B](as: List[A])(f: (A, B) => B): B = {
		go(bs: List[A], acc: B): B = bs match {
			case x :: xs => go(xs, f(x, acc))
			case Nil => acc
		}
	go(as, ???)
	}
	def foldMap[A, B](as: List[A])(f: A => B)(mb: Monoid[B]): B = {
		as.map(f).foldLeft(mb.zero)(mb.op)
	}
}