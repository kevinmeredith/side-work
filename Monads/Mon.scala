trait Functor[F[_]] {
  def map[A,B](fa: F[A])(f: A => B): F[B]
}

trait Mon[F[_]] extends Functor[F] {
	def map2[A, B, C] (
		fa: F[A], fb: F[B])(f: (A, B) => C): F[C] = 
			fa.flatMap(a => fb.map(b => f(a,b)))

	def map[A, B](fa: F[A])(f: A => B): F[B]

	def flatMap[A, B](fa: [A])(f: A => F[B]): F[B]
}