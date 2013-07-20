object FunSets {
	def union(s: Set[Int], t: Set[Int]): Set[Int] = t match {
		case Set() => s
		case Set(x, xs) => union(s + x, xs)
		case _ => throw new Error("bad input")
	}
}