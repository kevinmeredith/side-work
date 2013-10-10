// EXERCISE 11 (hard): To gain experience with the use of State, implement a
// finite state automaton that models a simple candy dispenser. The machine has two
// types of input: You can insert a coin, or you can turn the knob to dispense candy. It
// can be in one of two states: locked or unlocked. It also tracks how many candies
// are left and how many coins it contains.
// source - http://www.manning.com/bjarnason/

/* 
The rules of the machine are as follows:
-Inserting a coin into a locked machine will cause it to unlock if there is any candy left.
-Turning the knob on an unlocked machine will cause it to dispense candy and become locked.
-Turning the knob on a locked machine or inserting a coin into an unlocked machine does nothing.
-A machine that is out of candy ignores all inputs.

The method simulateMachine should operate the machine based on the list
of inputs and return the number of coins and candies left in the machine at the end.
For example, if the input Machine has 10 coins and 5 candies in it, and a total of
4 candies are successfully bought, the output should be (14, 1).
*/

object CandyMachine {
	sealed trait Input
	case object Coin extends Input
	case object Turn extends Input

 	type State[S, +A] = S => (A,S)

	case class Machine(locked: Boolean, candies: Int, coins: Int)

	def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] = {
		val machine = Machine(true, 10, 0)
		
		def go(inps: List[Input], m: Machine) : State[Machine, (Int, Int)] = inps match {
			case x :: xs => x match {
								case Turn if(m.locked == false && m.candies > 0) => go(xs, Machine(true, m.candies - 1, m.coins))
								case Coin if(m.candies > 0) => go(xs, Machine(false, m.candies, m.coins + 1))
								case _ => go(xs, m)
			}
			case Nil => (mach: Machine) => ((mach.coins, mach.candies), mach) 
		}
		go(inputs, machine)
	}

	def main(args: Array[String]) {
		val inputs = List(Turn)
		val res = simulateMachine(inputs)
		println(res)
	}
}