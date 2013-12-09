package Monads

trait Functor[F[_]] {
  def map[A,B](fa: F[A])(f: A => B): F[B]
}

trait Monad[F[_]] extends Functor[F] {
	def unit[A](a: => A): F[A]
	def flatMap[A,B](ma: F[A])(f: A => F[B]): F[B]

	def map[A,B](ma: F[A])(f: A => B): F[B] = 
		flatMap(ma)(a => unit(f(a)))
	def map2[A, B, C](ma: F[A], mb: F[B])(f: (A,B) => C): F[C] =  {
		println("ma: " + ma + ", mb: " + mb)
		flatMap(ma)(a => map(mb)(b => f(a, b)))
	}

	/* Wrong answers originally for sequence & traverse due to http://stackoverflow.com/a/20037817/409976 */

	// Exercise 3: implement sequence() and traverse
	// official answer from @pchiusano
	def sequence[A](lma: List[F[A]]): F[List[A]] = 
		lma.foldRight(unit(List[A]()))((ma, mla) => map2(ma, mla)(_ :: _))

	def traverse[A, B](la: List[A])(f: A => F[B]): F[List[B]] =
		la.foldRight(unit(List[B]()))((ma, mla) => map2(f(ma), mla)(_ :: _))

	// Simple predecessor to replicateM to help me understand how to write replicateM
	def replicateOnce[A](ma: F[A]): F[List[A]] = {
		map(ma)(x => List(x))
	}

	// Exercise 4: implement replicateM
	def replicateM[A](n: Int, ma: F[A]): F[List[A]] = 
			sequence(List.fill(n)(ma))

	// Exercise 9: Implement this "compose" function
	// @author pchiusano
	def compose[A, B, C](f: A => F[B], g: B => F[C]): A => F[C] = {
		a => flatMap(f(a))(g)
	}

	// Exercise 12: Rewrite these monad identity laws in terms of flatMap
	// compose(f, unit) == f
	// compose(unit, f) == f
	// @author pchiusano
	/*compose(f, unit) == f 			// for all functions f
	a => flatMap(f(a))(unit) == f   // for all functions f
	flatMap(x)(unit) == x           // for all values x

	compose(unit, f) == f 			// for all functions f
	a => flatMap(unit)(f(a) == f) 	// for all functions f and all values x*/

	// Exercise 13: There is a third minimal set of monadic combinators: map, 
	// unit, and join. Implement join.
	// I'm confused as to why flatMap can be used if join, map and unit are 3 combinators
	def join[A](mma: F[F[A]]): F[A] = flatMap(mma)(ma => ma)

	// Exercise 14: Implement either flatMap or compose in terms of join
	// @author pchiusano
	def flatMapWithJoin[A,B](ma: F[A])(f: A => F[B]): F[B] =
		join(map(ma)(f))

	/** Exercise 15: Restate the monad laws to mention only join, map, and unit
  // Identity
  join(map(ma)(unit)) == ma
  join(unit(x)) == x        @author pchiusano

  // Associativity
  val leftY = join(map(x)(f))
  val leftZ = join(map(leftY)(g))

  val rightY = join(map(x)(g))
  val rightZ = join(map(rightY)(f))

  leftZ == rightZ

  */
}