package common

case class Player(name: String) {}

trait IO[+A] { self =>
  def run: A
  def map[B](f: A => B): IO[B] =
    new IO[B] { def run = f(self.run)}
  def flatMap[B](f: A => IO[B]): IO[B] =
    new IO[B] { def run = f(self.run).run }
  def ++(io: IO): IO = new IO {
    def run { self.run; io.run }
  }
}

object IO extends Monad[IO]{
  def unit[A](a: => A): IO[A] = new IO[A] { def run = a }
  def flatMap[A,B](fa: IO[A])(f: A => IO[B]) = fa flatMap f
  def apply[A](a: => A): IO[A] = unit(a)
}

object Util {
  def ReadLine: IO[String] = IO { readLine }
}

  /*def PrintLine(msg: String): IO =
    new IO { def run = println(msg) }

  def winnerMsg(p: Player): String = p.name + " has won!"

  def PrintWinner(p: Player): IO =
    new IO { def run = println(winnerMsg(p))}

  def empty: IO = new IO { def run = () }    */

 }
