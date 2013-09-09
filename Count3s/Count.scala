import scala.actors.Actor

object Count  extends Actor {
	
	var totalSum=0

	def act = { 
		loop {
			receive {
				case x: Int => totalSum+=x; println("totalSum = " + totalSum)
				case _ => 
			}
		}
	}

	def main(args: Array[String]) {
		val N: Int = 10

		val lists = List.range(1, N).grouped(N/2).toList

		val actor1: Actor = new CheckActor(this).start()
		val actor2: Actor = new CheckActor(this).start()

		val first = lists(0)
		val second = lists(1)

		for(item <- first) {
			actor1 ! item
		}

		for(item <- second) {
			actor2 ! item
		}

		actor1 ! "sum"
		actor2 ! "sum"

		// TODO: figure out why type mismatch error occurs
		// val sum1: Int = actor1 ! "calcSum"
		// println("sum1: " + sum1)

		Count start
	}
}