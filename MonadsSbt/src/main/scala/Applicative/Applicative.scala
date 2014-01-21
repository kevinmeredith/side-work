package Applicative

import Monads.Functor

trait Applicative[F[_]] extends Functor[F] {
  // primitive combinators
  def map2[A,B,C](fa: F[A], fb: F[B])(f: (A,B) => C): F[C]
  def unit[A](a: => A): F[A]

  // derived combinators
  def map[A,B](fa: F[A])(f: A => B): F[B] =
    map2(fa, unit(()))((a, _) => f(a))

  // see http://stackoverflow.com/questions/20485363/implementing-applicativetraverse
  def traverse[A,B](as: List[A])(f: A => F[B]): F[List[B]] =
    as.foldRight(unit(List[B]()))((elem, acc) => map2(f(elem), acc)(_ :: _))

  def sequence[A](fas: List[F[A]]): F[List[A]] =
    fas.foldRight(unit(List[A]()))((elem, acc) => map2(elem, acc)((x, y) => x :: y))

  def replicateM[A](n: Int, fa: F[A]): F[List[A]] =
    sequence(List.fill(n)(fa))

  def product[A, B](fa: F[A], fb: F[B]): F[(A,B)] =
    map2(fa, fb)((x, y) => (x,y))

  def apply[A,B](fab: F[A => B])(fa: F[A]): F[B] =
    map2(fa, fab)((x, f) => f(x))

  // Exercise 3: The apply method is useful for implementing map3, map4, and so on
  // and the pattern is straightforward. Implement map3 and map4 using only
  // unit, apply, and the curried available on functions.
  def map3[A,B,C,D](fa: F[A],
                    fb: F[B],
                    fc: F[C])(f: (A, B, C) => D): F[D] = {
    def foo: (A => B => C => D) = f.curried
    def fooF: F[A => B => C => D] = unit(foo)
    val x: F[B => C => D] = apply(fooF)(fa)
    val y: F[C => D] = apply(x)(fb)
    val z: F[D] = apply(y)(fc)
    z
  }

  def map4[A,B,C,D,E](fa: F[A],
                      fb: F[B],
                      fc: F[C],
                      fd: F[D])(f: (A, B, C, D) => E): F[E] = {
    def foo: (A => B => C => D => E) = f.curried
    def fooF: F[A => B => C => D => E] = unit(foo)
    val a: F[B => C => D => E] = apply(fooF)(fa)
    val b: F[C => D => E] = apply(a)(fb)
    val c: F[D => E] = apply(b)(fc)
    val d: F[E] = apply(c)(fd)
    d
  }

  def sequenceMap[K, V](ofa: Map[K,F[V]]): F[Map[K, V]] =
    ofa.foldLeft(unit(Map[K,V]()))((x, y) => map2(x, y._2)((a, b) => (a + (y._1 -> b))))

  /* TODO: figure out how to write map2
  def product[G[_]](G: Applicative[G]): Applicative[({type f[x] = (F[x], G[x])})#f] = {
    val self = this
    new Applicative[({type f[x] = (F[x], G[x])})#f] {
      def unit[A](a: => A): (F[A], G[A]) = (self.unit(a), G.unit(a))
      override def apply[A,B](fs: (F[A => B], G[A => B]))(p: (F[A], G[A])) =
        (self.apply(fs._1)(p._1), G.apply(fs._2)(p._2))

      /*override def map2[A,B,C](fa: (F[A], G[A]), fb: (F[B], G[B]))(f: (A,B) => C): (F[C], G[C]) = {

        val x: (F[A], F[B]) = (fa._1, fb._1)
        val y: (G[A], G[B]) = (fa._2, fb._2)
        val fc: F[C] = map(x)(f.tupled)
        val gc: G[C] = map(y)(f.tupled)
        (fc, gc)
      }*/
    }
  }   */
}

