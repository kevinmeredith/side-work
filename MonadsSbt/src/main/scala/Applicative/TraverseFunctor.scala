package Applicative

import Functor.Functor

trait TraverseFunctor[F[_]] extends Functor[F] {
  def traverse[G[_],A,B](fa: F[A])(f: A => G[B])(implicit G: Applicative[G]): G[F[B]] =
    sequence(map(fa)(f))
}
