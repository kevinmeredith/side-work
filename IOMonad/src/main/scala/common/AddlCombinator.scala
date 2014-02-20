package common

trait AddlCombinators[F[_]] extends Monad[F] with Functor[F] {
  def forever[A, B](a: F[A]): F[B] = {
    lazy val t: F[B] = forever(a)
    flatMap(a)(_ => t)
  }
}