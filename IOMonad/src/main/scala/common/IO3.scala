package common

object IO3 {

  sealed trait Free[F[_], A] extends Monad[F]
  case class Return[F[_], A](a: A) extends Free[F, A]
  case class Suspend[F[_], A](s: F[Free[F,A]]) extends Free[F,A]
  case class FlatMap[F[_],A,B](s: Free[F,A],
                                f: A => Free[F, B]) extends Free[F,B]

  //type Free1[F[_]] = ({type f[a] = Free[F, a]})#f
  //def flatMap[A, B](mx: Free1[A])(mf: A => Free1[B]): Free1[B] =

  // see http://stackoverflow.com/a/22769525/409976
  def freeMonad[F[_]]: Monad[({type f[a] = Free[F, a]})#f] = {
    new Monad[({type f[x] = Free[F, x]})#f] {
      override def apply[A](a: => A): Free[F, A] = Return(a)
      override def flatMap[A, B](mx: Free[F, A])(mf: A => Free[F, B]): Free[F, B] = mx match {
        case Return(a) => FlatMap(mx, mf)
        case Suspend(a) => FlatMap(a, mf)             // not sure how to extract Free[F,A] from Suspend(s: F[Free[F, A]])
        case FlatMap(a, f) => flatMap(Return(a))(mf)  // calling flatMap(Return(a))(mf) seems to fit the signature of flatMap
      }
    }
  }

  /*sealed trait Console[R]
  case class ReadLine[R](k: Option[String] => R) extends Console[R]
  case class PrintLine[R](s: String, k: () => R) extends Console[R]

  object Console {
    type ConsoleIO[A] = Free[Console, A]

    def readLn: ConsoleIO[Option[String]] =
      Suspend(ReadLine((s: Option[String]) => Return(s)))

    def printLn(s: String): ConsoleIO[Unit] =
      Suspend(PrintLine(s, () => Return(())))
  }*/

  // Exercise 1: Free is a monad for any choice of F. Implement map
  // and flatMap methods on the Free trait, and give the Monad instance
  // for Free[F,_]


}
