import scala.math.BigInt
object Main extends App {
	
	val fibs: Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: 
		fibs.zip(fibs.tail).map(n => {
			println("Adding %d and %d".format(n._1, n._2))
			n._1 + n._2
		})

	fibs take 5 foreach println
	//fibs take 6 foreach println
}