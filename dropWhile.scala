def dropWhile[A](l: List[A])(f: A => Boolean) : List[A] = l match {
       case x :: xs if f(x) => x :: dropWhile(xs)(f)
       case x :: xs if !f(x) => dropWhile(xs)(f)
       case Nil => l
       case _ => Nil
      }