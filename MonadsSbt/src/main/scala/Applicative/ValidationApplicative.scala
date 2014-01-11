package Applicative

import DataType.Data._

object ValidationApplicative {

  def validationApplicative[E]: Applicative[({type f[x] = Validation[E,x]})#f] =
    new Applicative[({type f[x] = Validation[E,x]})#f] {
      override def unit[A](a: => A) = Success(a)

      override def map2[A,B,C](fa: Validation[E,A], fb: Validation[E,B])(f: (A, B) => C) = (fa, fb) match {
        case (Failure(x,xs), Failure(y, ys)) => Failure(x, (y +: xs) ++ ys)
        case (Failure(x, xs), _) => Failure(x, xs)
        case (_, Failure(y, ys)) => Failure(y, ys)
        case (Success(x), Success(y)) => Success(f(x,y))
      }
    }
}