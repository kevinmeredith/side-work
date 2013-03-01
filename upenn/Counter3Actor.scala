import scala.actors.Actor
import Actor._

class Count3Actor(bigArray: List[Int]) extends Actor {
	var num3s = 0
	def act = for (i <- 0 to bigArray.length) {
		if (i % 3 == 0) { num3s += 1 }
	}
	println("count of #'s divisible by 3 = " + num3s)
}

			