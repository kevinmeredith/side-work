trait Monoid[A] {
	def op(a1: A, a2: A): A
	def zero: A
}
	

object ComposingMonoids {
	
	// Beginning of monoid compoisition (from FP in Scala)
	// https://github.com/pchiusano/fpinscala/.../.../17.answer.scala
	// @author - pchiusano
	def productMonoid[A, B](A: Monoid[A], B: Monoid[B]): Monoid[(A, B)] = {
		new Monoid[(A, B)] {
			def op(x: (A,B), y: (A,B) ) = (A.op(x._1, y._1) , B.op(x._2, y._2))
			val zero = (A.zero, B.zero)
		}
	}

	val intMultiplication = new Monoid[Int] {
		def op(a1: Int, a2: Int) = a1 * a2
		val zero = 1
	}

	val intAddition = new Monoid[Int] {
		def op(a1: Int, a2: Int) = a1 + a2
		val zero = 0
	}

	val concatenate = new Monoid[String] {
		def op(a1: String, a2: String) = a1 + a2
		val zero = ""
	}

	// @author - pchiusano
	def mapMergeMonoid[K,V](V: Monoid[V]): Monoid[Map[K,V]] =
		new Monoid[Map[K, V]] {
			def zero = Map()
			def op(a: Map[K, V], b: Map[K, V]) = 
				a.map {
					case (k, v) => (k, V.op(v, b.get(k).getOrElse(V.zero) ))
				}
		}

	// Exercise 20: Use monoids to compute a "bag" from an IndexedSeq
	//   scala> bag(Vector("a", "rose", "is", "a", "rose"))
	//   res0: Map[String,Int] = Map(a -> 2, rose -> 2, is -> 1)

	def bag[A](as: IndexedSeq[A]): Map[A, Int] = {
		as.foldLeft(Map[A, Int]())( (acc, el) =>
								    if (acc.contains(el) ) {
								        val x = acc.get(el).get
								       acc + (el -> (x+1))
								  	}
								  	else { acc + (el -> 1) } )
	}

	// Implementing bag with the mapMergeMonoid
	// Read the hint from pchiusano's github
	def bagWithMonoids[A](as: IndexedSeq[A]): Map[A, Int] = {
		val bagMonoid: Monoid[Map[A, Int]] = mapMergeMonoid(intAddition)
		as.map(x => (x, 1)).foldLeft(Map[A, Int]())( (acc, element) => acc ++ bagMonoid.op(Map(element), acc))
	}

	// Map("a" -> 1, "b" -> 1, "a" -> 1)
	// Map()

	def main(args: Array[String]) = {
		val x = (100, "hello")
		val y = (200, "world")

		val result: (Int, String) = productMonoid(intMultiplication, concatenate).op(x,y)
		println("result: " + result)
		assert(result._1 == 20000)
		assert(result._2 == "helloworld")

		val m1 = Map(1 -> 1, 2 -> 2)
		val m2 = Map(1 -> 10, 2 -> 20)

		val resultMap: Map[Int, Int] = mapMergeMonoid(intMultiplication).op(m1, m2)
		println("resultMap: " + resultMap)
		assert(resultMap == Map(1 -> 10, 2 -> 40))

		// bag example

		val bagData = Vector("a", "b", "a")

		val bagResult = bag(bagData)
		println("bagResult: " + bagResult)
		assert(bagResult == Map("a" -> 2, "b" -> 1))

		val bagMonoidResult = bagWithMonoids(bagData)
		println("bagMonoidResult: " + bagMonoidResult)
		assert(bagMonoidResult == Map("a" -> 2, "b" -> 1))


		println("success")
	}
}