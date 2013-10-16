object StreamWork {
	def streamRange(lo: Int, hi: Int): Stream[Int] = {
		print(lo + " ")
		if(lo >= hi) Stream.empty
		else Stream.concast(lo, streamRange(lo + 1, hi))
	}

	def main(args: Array[String]) = {
		println("streamRange(1, 10).take(3) : " + streamRange(1, 10).take(3).toList)
	}
}