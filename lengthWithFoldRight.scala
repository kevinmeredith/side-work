def length[A](l: List[A]): Int =
  foldRight(l, 0)((A,B)=>B+1)