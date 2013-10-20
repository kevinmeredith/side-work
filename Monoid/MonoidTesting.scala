object MonoidTesting1 {
	trait Monoid[A] {
		def op(a1: A, a2: A): A
		def zero: A
	}


	val stringMonoid = new Monoid[String] {
		def op(a1: String, a2: String) = a1 + a2
		val zero = ""
	}

	val listMonoid[A] = new Monoid[List[A]] {
		def op(a1: List[A], a2: List[A]) = a1 ++ a2
		val zero = Nil
	}

	// Exercise 1: Give Monoid instances for integration addition and multiplication as well as the 
	// Boolean operators. "FP in Scala"
	val intAddition: Monoid[Int] = new Monoid[Int] {
		def op(a1: Int, a2: Int) = a1 + a2
		val zero = ""
	}

	val intMultiplication: Monoid[Int] = new Monoid[Int] {
		def op(a1: Int, a2: Int) = a1 * a2
		val zero = 0
	}

	val booleanOr: Monoid[Boolean] = new Monoid[Boolean] {
		def op(a1: Boolean, a2: Boolean) = a1 && a2
		val zero = false
	}
	
	val booleanAnd: Monoid[Boolean] = new Monoid[Boolean] {
		def op(a1: Boolean, a2: Boolean) = a1 || a2
		val zero = false
	}
	
}