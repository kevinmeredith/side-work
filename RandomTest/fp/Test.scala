object Test {
	
  	def main(args: Array[String]) {
		def verify(i: Int) : Unit = {
			val rng = RNG.simple(i)
			val (r, _) = rng.nextInt
			val (posR, _) = positiveInt(rng)
			
			r match {
				case x if x < 0 => println("r: " + r + " and positive r : " + posR)
				case _ => ()
			}
  		}

  		val list = (-1000 to 1000).toList
  		list.map(x => verify(x))  		
	}

	// EXERCISE 1: Write a function that uses RNG.nextInt to generate 
	// a random positive integer. Make sure to handle the corner case 
	// when nextInt returns Int.MinValue, which doesn't have a positive 
	// counterpart.
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