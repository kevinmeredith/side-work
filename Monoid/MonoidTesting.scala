// A monoid is a type together with an associative binary operation(op) which has an identity element(zero).
object MonoidTesting {
	def main(args: Array[String]) = {

		val words = List("Hic", "Est", "Barbarus")

		val fl = words.foldLeft(stringMonoid.zero)(stringMonoid.op)
		println("fl : " + fl)
		assert(fl == "HicEstBarbarus")

		val fr = words.foldRight(stringMonoid.zero)(stringMonoid.op)
		println("fr: " + fr)
		assert(fr == "HicEstBarbarus")
		
		// testing trimMonoid
		val res = trimMonoid.op( ("Hic"), (trimMonoid.op("est ", "chorda ")) )
		println("res : " + res)
		assert(res == "Hic est chorda")

		// testing concatenation monad
		val str = List("Hello", "World")
		val resStr = concatenate(str, concatMonad)
		println("resStr: " + resStr)
		assert(resStr == "HelloWorld")

		// test foldMap
		val ints = List(1, 2, 3)
		val strs = foldMap(ints, concatMonad)( (x: Int) => x.toString)
		println(strs)
		assert(strs == "123")

		//def foldMapRight[A, B](as: List[A], m: Monoid[B])(z: B)(f: (A, B) => B): B = {		
		val stringInts = List("1", "2", "3")
		val resFoldMap = foldMapRight(stringInts, intAddition)(intAddition.zero)((s, i) => intAddition.op(s.toInt, i))
		println("resFoldMap: " + resFoldMap)
		assert(resFoldMap == 6)

		println("success")
	}

	trait Monoid[A] {
		def op(a1: A, a2: A): A
		def zero: A
	}

	val stringMonoid = new Monoid[String] {
		def op(a1: String, a2: String) = a1 + a2
		val zero = ""
	}

	def listMonoid[A]= new Monoid[List[A]] {
		def op(a1: List[A], a2: List[A]) = a1 ++ a2
		val zero = Nil
	}

	// Exercise 1: Give Monoid instances for integration addition and multiplication as well as the 
	// Boolean operators. "FP in Scala"
	val intAddition = new Monoid[Int] {
		def op(a1: Int, a2: Int) = a1 + a2
		val zero = 0
	}

	val intMultiplication = new Monoid[Int] {
		def op(a1: Int, a2: Int) = a1 * a2
		val zero = 1
	}

	val booleanOr = new Monoid[Boolean] {
		def op(a1: Boolean, a2: Boolean) = a1 && a2
		val zero = false
	}
	
	val booleanAnd = new Monoid[Boolean] {
		def op(a1: Boolean, a2: Boolean) = a1 || a2
		val zero = true
	}


	// EXERCISE 5: Write a monoid instance for that String inserts spaces
	// between words unless there already is one, and trims spaces off the ends of the
	// result.
	def trimMonoid = new Monoid[String] {
		def op(a1: String, a2: String) = a1.trim + " " + a2.trim
		val zero = ""
	}
	
	//def trimMonoid(s: String): Monoid[String] = {

	// EXERCISE 2: Give a Monoid instance for combining Options:
	/*def optionMonoid[A](implicit aMonoid:Monoid[A]) = new Monoid[Option[A]] {
		def op(a1: Option[A], a2: Option[A]) = (a1, a2) match {
			case (Some(x), Some(y)) => Some(aMonoid.op(x, y))
			case _ => None
		}
		val zero = // op(x, zero) === x === op(zero, x) 
	}*/

	// EXERCISE 6: Implement concatenate, a function that folds a list with a
	// monoid:
	def concatenate[A](as: List[A], m: Monoid[A]): A = 
		as.foldLeft(m.zero)((s, i) => m.op(s, i))

	val concatMonad = new Monoid[String] {
		def op(a1: String, a2: String) = a1 + a2
		def zero = ""
	}

	// But what if our list has an element type that doesn't have a Monoid instance?
	// Well, we can always map over the list to turn it into a type that does.
	def foldMap[A, B](as: List[A], m: Monoid[B])(f: A => B): B = {
		val bs = as.map(f)
		bs.foldLeft(m.zero)((s, i) => m.op(s, i))
	}

	// EXERCISE 8 (hard): The foldMap function can be implemented using either
	// foldLeft or foldRight. But you can also write foldLeft and foldRight
	// using foldMap! Try it.
	// def foldRight[B](z: B)(op: (A, B) => B): B
	def foldMapRight[A, B](as: List[A], m: Monoid[B])(z: B)(f: (A, B) => B): B = {
		def go(ys: List[A], acc: B) : B = ys match {
			case x :: xs => go(xs, f(x, acc))
			case Nil => acc
		}
		go(as, z)
	}
}