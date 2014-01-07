package tests

import org.scalatest._
import Monads._

class TestStateMonad extends FlatSpec {

  "testing state monad" should "pass" in {
    val intState = State.stateMonad[Int]
    println("intState.unit(5): " + intState.unit(5).map(x => x.toString))
  }
}
