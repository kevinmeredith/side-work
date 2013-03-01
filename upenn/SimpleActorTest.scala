import scala.actors.Actor
import Actor._

object SimpleActorTest {
	def main(args: Array[String]) {
		val caller = self
		val adder = actor {
			var sum = 0
			loop {
				receive {
					case (x: Int, y: Int) => sum = x + y
					case "sum"            => caller ! sum
					}
				}
			}
		adder ! (2, 2)
		adder ! (3, 3)
		adder ! (4, 4) // sum should equal 8
		adder ! "sum" // This must be done before calling receive!
		receive { 
			case x => println("I got: " + x)
		}	
		println("done")
	}
}