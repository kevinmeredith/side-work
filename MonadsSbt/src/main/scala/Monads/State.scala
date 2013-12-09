package Monads

case class State[S, A](run: S => (A, S)) {
  def map[B](f: A => B): State[S, B] =
    State(s => {
      val (a, s1) = run(s)
      (f(a), s1)
    })
  def flatMap[B](f: A => State[S, B]): State[S, B] =
    State(s => {
      val (a, s1) = run(s)
      f(a).run(s1)
    })
}

object State {

  def stateMonad[S] = new Monad[({type f[x] = State[S,x]})#f] {
    def unit[A](a: => A): State[S, A] = State(s => (a,s))
    def flatMap[A,B](st: State[S,A])(f: A => State[S,B]): State[S, B] =
      st flatMap f
  }

/*type IntState[A] = State[Int, A]

object IntState extends Monad[IntState] {
  def unit[A](a: => A): IntState[A] = State(s => (a, s))
  def flatMap[A, B](st: IntState[A])(f: A => IntState[B]): IntState[B] =
    st flatMap f
} */

}

