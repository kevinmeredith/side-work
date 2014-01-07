package Applicative

import Monads.Functor

// Exercise 2 (hard): The name `applicative` comes from the fact
// that we can formulate the Applicative interface using an alternate set of
// primitives, unit and the function apply, rather than unit and map2. Show that this
// formulation is equivalent in expressiveness by defining map2 and map in terms of unit and apply.
// Also establish that apply can be implemented in terms of map2 and unit.
// @author - pchiusano
trait ApplicativeHard[F[_]] extends Functor[F] {

  // Define `apply` in terms of map2 and unit
  def apply[A,B](fab: F[A => B])(fa: F[A]): F[B] //=    map2(fa, fab)(_(_))

  def unit[A](a: => A): F[A]

  def map[A,B](fa: F[A])(f: A => B): F[B] //=  apply(unit(f))(fa)

  def map2[A,B,C](fa: F[A], fb: F[B])(f: (A,B) => C): F[C] //=  apply(map(fa)(f.curried), fb)


}
