package Applicative

object TraverseImplementation {

  val listTraverse = new Traverse[List] {

    override def map2[A, B, C](fa: List[A],fb: List[B])(f: (A, B) => C): List[C] = {
      val zipped = fa zip fb
      map(zipped)(f.tupled)
    }

    override def unit[A](a: => A): List[A] = List(a)

    override def traverse[G[_],A,B](as: List[A])(f: A => G[B])(implicit G: Applicative[G]): G[List[B]] =
      as.foldLeft(G.unit(List[B]()))((acc, elem) => G.map2(acc, f(elem))((a, b) => a :+ b))
    }

  val optionTraverse = new Traverse[Option] {
    override def map2[A, B, C](fa: Option[A],fb: Option[B])(f: (A, B) => C): Option[C] = {
      (fa, fb) match {
        case (None, _) => None
        case (_, None) => None
        case (_, _) => Some(f(fa.get, fb.get))
      }
    }

    override def unit[A](a: => A): Option[A] = Some(a)

    override def traverse[G[_],A,B](oa: Option[A])(f: A => G[B])(implicit G: Applicative[G]): G[Option[B]] =
      oa match {
        case None => G.unit(None)
        case _ => {
                  val a: A = oa.get
                  val x: G[B] = f(a)
                  G.map(x)(Some(_))
                }
      }
  }
}
