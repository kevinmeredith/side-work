// EXERCISE 11 (hard): To gain experience with the use of State, implement a
// finite state automaton that models a simple candy dispenser. The machine has two
// types of input: You can insert a coin, or you can turn the knob to dispense candy. It
// can be in one of two states: locked or unlocked. It also tracks how many candies
// are left and how many coins it contains.
// source - http://www.manning.com/bjarnason/

object CandyMachine {
	sealed trait Input
	case object Coin extends Input
	case object Turn extends Input

	case class Machine(locked: Boolean, candies: Int, coins: Int)

	def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] = {

	}

	def main(args: Array[String]) {
		val inputs = List(Turn)
		val res = simulateMachine(inputs)
	}
}