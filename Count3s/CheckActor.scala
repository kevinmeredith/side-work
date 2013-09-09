import scala.actors.Actor

/**
* This actor checks if a number is divisible by 3.
*/
class CheckActor(actor: Actor) extends Actor {  
 println("created actor")

 var sum = 0
 def act = loop {
 	react {
 		case x:Int if (x % 3 == 0) => sum+=1
 		case "sum" => println("received sum: " + sum); actor ! sum
 		case "calcSum" => sum
 		case _ => 
 	}
 }
}