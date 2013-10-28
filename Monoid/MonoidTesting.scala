// A monoid is a type together with an associative binary operation(op) 
// which has an identity element(zero). -"Functional Programming in Scala"
object MonoidTesting {
	// TODO: Figure out Monoid[Option], ordered, as well as foldMapRight/Left
	implicit val m: Monoid[Int] = intAddition

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
		/*val stringInts = List("1", "2", "3")
		val resFoldMap = foldMapRight(stringInts, intAddition)(intAddition.zero)((s, i) => intAddition.op(s.toInt, i))
		println("resFoldMap: " + resFoldMap)
		assert(resFoldMap == 6)*/

		// testing foldMapV
		val seq = IndexedSeq("1","2","3","4")
		val r = foldMapV(seq, intAddition)(x => x.toInt)
		println(r)
		assert(r == 10)

		// testing OptionMonoid
		val opts: List[Option[Int]] = List(Some(1), Some(2), None, Some(5))
		val resOpts = opts.foldLeft(optionMonoid.zero)(optionMonoid.op)
		println(resOpts)
		assert(resOpts == Some(8))

		// testing foldMap if IndexedSeq[Int] is ordered
		/*val seqInt = IndexedSeq("1","2","3","4")
		val isOrdered = foldMapV(seqInt, ordered)(x => x.toInt)
		println(isOrdered)
		assert(isOrdered == true)

		val seqInt2 = IndexedSeq("4", "3", "2", "1")
		val isOrdered2 = foldMapV(seqInt2, ordered)(x => x.toInt)
		println(isOrdered2)
		assert(isOrdered2 == false)*/

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

	// EXERCISE 2: Give a Monoid instance for combining Options:
	def optionMonoid[A](implicit m: Monoid[A]) = new Monoid[Option[A]] {
		def op(a1: Option[A], a2: Option[A]) = (a1, a2) match {
			case (Some(x), Some(y)) => Some(m.op(x, y))
			case (Some(x), _) => Some(m.op(x, m.zero))
			case (_, Some(y)) => Some(m.op(m.zero, y))
			case _ => Some(m.zero)
		}
		val zero = None
	}

	// EXERCISE 6: Implement concatenate, a function that folds a list with a
	// monoid:
	def concatenate[A](as: List[A], m: Monoid[A]): A = 
		as.foldLeft(m.zero)(m.op)

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
	/*def foldMapRight[A, B](as: List[A], m: Monoid[B])(f: A => B): B
		foldMap(as, m,)
	}*/

	// foldRight: op(a, op(b, op(c, d)))
	// foldLeft: op(op(op(a b) c) d)

	// EXERCISE 9: Implement a foldMap for IndexedSeq, a common supertype
	// for various data structures that provide efficient random access including Vector.
	// Your implementation should use the strategy of splitting the sequence in two,/
	// recursively processing each half, and then adding the answers together with the
	// monoid.
	def foldMapV[A,B](v: IndexedSeq[A], m: Monoid[B])(f: A => B): B = {
		def go(seq: List[B], acc: B): B = seq match {
			case x :: xs => go(xs, m.op(acc, x))
			case Nil => acc
		}

		val as: List[B] = v.map(f).toList
		val (a: List[B], b: List[B]) = as.splitAt(as.length / 2)
		m.op(go(a, m.zero), go(b, m.zero))
	}

	// EXERCISE 10 (hard): Use to detect foldMap whether a given
	// IndexedSeq[Int] is ordered. You will need to come up with a creative
	// Monoid. *Incomplete*
	/*val ordered = new Monoid[Int] = {
		def op(a1: Int, a2: Int) = if(a1 <= a2) a2 else Int.MaxValue 
		val zero = Int.MinValue
	}*/
}