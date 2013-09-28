object Test {
	
  	def main(args: Array[String]) {
		def verifyPositiveInt(i: Int) : Unit = {
			val rng = RNG.simple(i)
			val (r, _) = rng.nextInt
			val (posR, _) = positiveInt(rng)
			
			r match {
				case x if x < 0 => println("[" + i + "] , r: " + r + " and positive r : " + posR)
				case _ => ()
			}
  		}

		// EXERCISE 2: Write a function to generate a Double between 0 and 1, not
		// including 1. Note: you can use Int.MaxValue to obtain the maximum positive
		// integer value and you can use x.toDouble to convert an Int, x, to a Double. 
		// (www.manning.com/bjarnason/)
  		def double(rng: RNG): (Double, RNG) = {
			val (r, _) = rng.nextInt
			val d: Double = ( math.abs(r).toDouble / Int.MaxValue ) match {
				case 1 => .99999
				case x: Double => x
			}
			(d, rng)
  		}

  		val list = (-1000 to 5000).toList
  		val rng = RNG.simple(42)
  		val (d, _) = double(rng)
  		println("d: " + d)
  		//list.map(x => verifyPositiveInt(x))  		
	}

	// EXERCISE 1: Write a function that uses RNG.nextInt to generate 
	// a random positive integer. Make sure to handle the corner case 
	// when nextInt returns Int.MinValue, which doesn't have a positive 
	// counterpart. (www.manning.com/bjarnason/)
	def positiveInt(rng: RNG): (Int, RNG) = {
		val (r, rng2) = rng.nextInt
		val posR = r match {
			case Int.MinValue => Int.MaxValue
			case x if x < 0 => math.abs(x)
			case _ => r
		}
		(posR, rng2)
	}
}