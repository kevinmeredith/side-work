import scala.actors.Actor
import scala.util.Random._
import Actor._

object ThousandActors {
	def main (args: Array[String]) {
		
		// Create and start some actors
		val actors = (1 to 5) map (new Counter(_))
		for (actor <- actors) { actor.start }
		
		// Send votes to the actors (1000 votes each)
		val random = new scala.util.Random
		for (i <- 1 to 5000) {
			actors(i % actors.length) ! random.nextBoolean
		}
		
		// Tell the actors to quit
		actors foreach(_ ! "quit")
	}
}