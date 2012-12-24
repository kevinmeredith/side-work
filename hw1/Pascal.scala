object Pascal {
	def main(args: List[String]) = {
		require((args length) == 2, "args length must equal 2")
		  println("inputs column:" + (args head) + " and row:" + (args last))
		  pascal((args head) toInt, (args last) toInt)
	}
	def pascal(c: Int, r: Int): Int = {
		require((((r >= 0) && (c >= 0)) 
										&& (c <= r)), 
													"r and c must be >= 0"
													  + "AND c must be <= r")
		println("c: " + c + "r: " + r)
		val R = r;
		(c, r) match {
			case (0, _) => 1
			case (R, _) => 1
			case (_, _) => pascal(c,r-1) + pascal(c-1,r-1)
		}
	}
}




