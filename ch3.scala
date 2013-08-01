def setHead(n: String, x: List[String]) = x match {
   case Nil => List(n)
   case _ => n :: (x tail)
}

def append[A](a1: List[A], a2: List[A]): List[A] =
  a1 match {
     case Nil => a2
     case Cons(h, t) => Cons(h, append(t, a2))
}

 def init[A](l: List[A]): List[A] = l match {
  case Nil => Nil
  case _ => (l reverse).tail.reverse
}