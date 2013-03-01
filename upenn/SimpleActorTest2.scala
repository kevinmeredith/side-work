import scala.actors.Actor
import Actor._

object SimpleActorTest2 {
	def main(args: Array[String]) {
		var sum = 0
		val adder = actor {
			loop {
				receive {
					case (x: Int, y: Int) =>  	println("actor adder is adding")
												sum = x + y
					}
				}
			}
		adder ! (2, 2)
		println("I got: " + sum)
	}
}