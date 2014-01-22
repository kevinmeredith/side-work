package Applicative

case class Tree[+A](head: A, tail: List[Tree[A]])

class TreeTraverse extends Traverse[Tree] {
  def unit[A](a: => A): Tree[A] = Tree[A](a, List[Tree[A]]())
  def map2[A,B,C](fa: Tree[A], fb: Tree[B])(f: (A,B) => C): Tree[C] = {
    val x: Tree[B => C] = map(fa)(f.curried)
    val y: Tree[C] = apply(x)(fb)
    y
  }
}
