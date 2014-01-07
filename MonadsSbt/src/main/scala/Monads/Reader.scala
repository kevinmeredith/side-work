package Monads

case class Reader[R, A](run: R => A)

// Need to come back as I don't understand the State Monad

/*object Reader {
  def readerMonad[R] = new Monad[({type f[x] = Reader[R, x]})#f] {
    def unit[A](a: => A): Reader[R, A] =
      Reader(_ => a)

    def flatMap[A,B](st: Reader[R,A])(f: A => Reader[R, B]): Reader[R, B]
  }
}*/