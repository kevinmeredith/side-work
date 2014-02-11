package common


object I01 {

  def main(args: Array[String]): Unit = {
    println("calling converter")
    Util.converter
  }

  trait IO[+A] { self =>
    def run: A
    def map[B](f: A => B): IO[B] =
      new IO[B] { def run = f(self.run)}
    def flatMap[B](f: A => IO[B]): IO[B] =
      new IO[B] { def run = f(self.run).run }
    /*def ++(io: IO): IO = new IO {
       def run { self.run; io.run }
    }*/
  }

  object IO extends Monad[IO]{
    def unit[A](a: => A): IO[A] = new IO[A] { def run = a }
    def flatMap[A,B](fa: IO[A])(f: A => IO[B]) = fa flatMap f
    def apply[A](a: => A): IO[A] = unit(a)
  }

  object Util {
    def ReadLine: IO[String] = IO { readLine }
    def PrintLine(msg: String): IO[Unit] = IO { println(msg) }

    def converter: IO[Unit] = for {
      _ <- PrintLine("enter a temperature in degrees fahrenheit")
      d <- ReadLine.map(_.toDouble)
      _ <- PrintLine((d + 32).toString)
    } yield ()
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