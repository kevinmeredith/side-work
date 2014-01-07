package Applicative

object OptionApplicative extends Applicative[Option] {
  override def map2[A,B,C](fa: Option[A], fb: Option[B])(f: (A,B) => C): Option[C] =
    fa.flatMap(x => fb.map(y => f(x,y)))

  override def unit[A](a: => A): Option[A] =
    Some(a)
}
