import scala.actors.Actor

class Counter(id: Int) extends Actor {
	var yes, no = 0
	def act = loop {
		react {
			case true   => yes += 1
			case false  => no += 1
			case "quit" => 
				printf("Counter #%d got %d yes, %d no.\n", id, yes, no)
			case x =>
				println("Counter" + id + " didn't understand" + x)
			}
		}
	}