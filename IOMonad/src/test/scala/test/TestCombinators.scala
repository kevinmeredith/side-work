package test

import org.scalatest._
import common.AddlCombinators
import common.IO1.IO
import common.IO1.Util.PrintLine

class TestCombinators extends FlatSpec {

  /*"running AddlCombinators[List]#forever" should "throw a StackOverflow exception" in {
    val listCombinator = new AddlCombinators[List] {
      override def unit[A](a: => A): List[A] = List(a)

      override def flatMap[A,B](ma: List[A])(f: A => List[B]): List[B] =
        ma.map(f).flatten

      override def apply[A](a: => A): List[A] = unit(a)

      override def map[A,B](fa: List[A])(f: A => B): List[B] = {
          def go(as: List[A], acc: List[B]): List[B] = as match {
            case Nil => acc
            case x :: xs => go(xs, f(x) :: acc)
          }
        go(fa, Nil)
      }
    }
    listCombinator.forever(List(1))
  } */

  "running IO.forever" should "NOT result in a StackOverflow" in {
    import common.IO1.{Suspend, Return}
    def printLine(s: String): IO[Unit] =
      Suspend( () => Return(println(s)))
    val p: IO[String] = IO.forever(printLine("still going..."))
    common.IO1.run(p)
  }

}
