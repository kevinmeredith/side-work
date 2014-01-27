package Applicative

import Functor.Functor

// Exercise 13(hard): Implement map in terms of traverse for an arbitrary Traverse[F].
trait TraverseFunctor[F[_]] extends Functor[F] {
  def traverse[G[_],A,B](fa: F[A])(f: A => G[B])(implicit G: Applicative[G]): G[F[B]] =
    sequence(map(fa)(f))
}
