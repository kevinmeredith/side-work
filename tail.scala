def tail[T](ns: List[T]) : List[T] = ns match {
     case x :: xs => xs
     case _ => List()
 }