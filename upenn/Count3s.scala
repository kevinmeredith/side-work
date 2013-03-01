import scala.actors.Actor
import Actor._

object Count3s {
	def main(bigArray: List[Int]) {
		println("size of List = " + bigArray.length)
		
		val actor1 = new Count3Actor(bigArray)
		//val actor2 = new Count3Actor(secondHalf)
	}
}