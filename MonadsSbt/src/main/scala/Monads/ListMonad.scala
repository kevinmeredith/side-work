package Monads

object ListMonad extends Monad[List] {
  def unit[A](a: => A): List[A] = List(a)

  def flatMap[A,B](ma: List[A])(f: A => List[B]): List[B] =
    ma.map(x => f(x)).flatten
}
