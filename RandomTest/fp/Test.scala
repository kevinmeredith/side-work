object Test {
  	def main(args: Array[String]) {
		val list = (-1000 to 5000).toList
		val rng = RNG.simple(42)
		//val (results, rng2) = ints(10)(rng)

		val (r, rng2) = doubleMap
		println("r: " + r)

		println("doubleMap: " + doubleMap)
		println("positiveEven: " + positiveEven)

		//val((i,d), rng2) = intDouble(rng)
		//println("i: " + i + ", d: " + d)

		//val (d, _) = double(rng)
		//println("d: " + d)
		//list.map(x => verifyPositiveInt(x))  		
	}

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

	// EXERCISE 3: Write functions to generate an (Int, Double) pair, a
	// (Double, Int) pair, and a (Double, Double, Double) 3-tuple. You
	// should be able to reuse the functions you've already written
	def intDouble(rng: RNG): ((Int,Double), RNG) = {
		val (i: Int, rng2) = rng.nextInt
		val (d: Double, _) = double(rng)
		((i,d), rng2)
	}

	// EXERCISE 4: Write a function to generate a list of random integers.
	def ints(count: Int)(rng: RNG): (List[Int], RNG) = {
		def go(count: Int, currentRng: RNG, acc: List[Int]) : (List[Int], RNG) = count match {
			case 0 => (acc, currentRng)
			case _ => val (r, rng2) = currentRng.nextInt
			          go(count - 1, rng2, acc :+ r)
		}
		go(count, rng, List[Int]())
	}

	type Rand[+A] = RNG => (A, RNG)

	def map[A, B](s: Rand[A])(f: A => B): Rand[B] = 
		rng => {
			val (a, rng2) = s(rng)
			(f(a), rng2)
		}

	def positiveEven: Rand[Int] = 
		map(positiveInt)(i => i - i % 2)
	
	// EXERCISE 5: Use map to reimplement RNG.double in a more elegant way.
	def doubleMap: Rand[Double] = 
		map(double)(i => i)
}