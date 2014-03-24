package common

object IO1 {

  /*sealed trait IO[A] { self =>
    def run: A
    def map[B](f: A => B): IO[B] =
      new IO[B] { def run = f(self.run) }
    def flatMap[B](f: A => IO[B]): IO[B] =
      new IO[B] { def run = f(self.run).run }
  } */

  sealed trait IO[A] {
    def flatMap[B](f: A => IO[B]): IO[B] =
      FlatMap(this, f)
    def map[B](f: A => B): IO[B] =
      FlatMap(this, (a: A) => Return(f(a)))
  }

  case class Return[A](a: A) extends IO[A]
  case class Suspend[A](resume: () => IO[A]) extends IO[A]
  case class FlatMap[A, B](sub: IO[A], k: A => IO[B]) extends IO[B]

  @annotation.tailrec
  def run[A](io: IO[A]): A = io match {
    case Return(a) => a
    case Suspend(r) => run(r())
    case FlatMap(x, f) => x match {
      case Return(a) => run(f(a))
      case Suspend(r) => run(FlatMap(r(), f))
      case FlatMap(y, g) =>
          run(FlatMap(y, (a: Any) => FlatMap(g(a), f)))
    }
  }

  object IO extends Monad[IO]{
    def unit[A](a: => A): IO[A] = new IO[A] { def run = a }
    def flatMap[A,B](fa: IO[A])(f: A => IO[B]) = fa flatMap f
    def apply[A](a: => A): IO[A] = unit(a)
    def forever[A, B](a: IO[A]): IO[B] = {
      lazy val t: IO[B] = forever(a)
      flatMap(a)(_ => t)
    }
  }

  object Util {
    def ReadLine: IO[String] = IO { readLine }
    def PrintLine(msg: String): IO[Unit] = IO { println(msg) }

    def converter: IO[Unit] = for {
      _ <- PrintLine("enter a temperature in degrees fahrenheit")
      d <- ReadLine.map(_.toDouble)
      _ <- PrintLine((d + 32).toString)
    } yield ()

    def converterFlatMap: IO[Unit] = PrintLine("enter a temperate in degrees F").
        flatMap(x => ReadLine.map(_.toDouble)).
          flatMap(y => PrintLine((y + 32).toString))

  }
}


  /* good quote from book - each impure function has a pure function
  wanting to come out.
  def PrintLine(msg: String): IO =
    new IO { def run = println(msg) }

  def winnerMsg(p: Player): String = p.name + " has won!"

  def PrintWinner(p: Player): IO =
    new IO { def run = println(winnerMsg(p))}

  def empty: IO = new IO { def run = () }    */