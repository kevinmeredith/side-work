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
		
		val res = trimMonoid.op( ("Hic"), (trimMonoid.op("est ", "chorda ")) )
		println("res : " + res)
		assert(res == "Hic est chorda")

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
		val zero = 0
	}

	val booleanOr = new Monoid[Boolean] {
		def op(a1: Boolean, a2: Boolean) = a1 && a2
		val zero = false
	}
	
	val booleanAnd = new Monoid[Boolean] {
		def op(a1: Boolean, a2: Boolean) = a1 || a2
		val zero = false
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
	def concatenate[A](as: List[A], m: Monoid[A]): A = {
		def go(xs: List[A], acc: A): A = xs match {
			case y :: ys => go(ys, m.op(acc, y))
			case Nil => acc
		}
		go(as, Nil)
	}
}