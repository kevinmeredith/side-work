package Applicative

class OptionTraverse extends Traverse[Option] {
  def unit[A](a: => A): Option[A] = Option[A](a)
  def map2[A,B,C](fa: Option[A], fb: Option[B])(f: (A,B) => C): Option[C] = {
    val x: Option[B => C] = map(fa)(f.curried)
    val y: Option[C] = apply(x)(fb)
    y
  }
}
