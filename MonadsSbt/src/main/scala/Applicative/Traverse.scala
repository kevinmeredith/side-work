package Applicative

trait Traverse[F[_]] {
  def unit[A](a: => A): F[A]
  def map2[A,B,C](fa: F[A], fb: F[B])(f: (A,B) => C): F[C]

  def apply[A,B](fab: F[A => B])(fa: F[A]): F[B] =
    map2(fa, fab)((x, f) => f(x))

  def map[A,B](fa: F[A])(f: A => B): F[B] =
    map2(fa, unit(()))((a, _) => f(a))

  def traverse[G[_]:Applicative,A,B](fa: F[A])(f: A => G[B]): G[F[B]] =
    sequence(map(fa)(f))

  def sequence[G[_]:Applicative, A](fma: F[G[A]]): G[F[A]] =
    traverse(fma)(ma => ma)
}
