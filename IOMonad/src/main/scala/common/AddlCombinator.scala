package common

trait AddlCombinators[F[_]] extends Monad[F] with Functor[F] {
  var count = 0;

  def forever[A, B](a: F[A]): F[B] = {
    lazy val t: F[B] = { println(count); count = count + 1; forever(a) }
    flatMap(a)(_ => t)
  }
}