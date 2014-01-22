package Applicative

class ListTraverse extends Traverse[List]{
  def unit[A](a: => A): List[A] = List(a)
  def map2[A,B,C](fa: List[A], fb: List[B])(f: (A,B) => C): List[C] = {
    val x: List[B => C] = map(fa)(f.curried)
    val y: List[C] = apply(x)(fb)
    y
  }
}
