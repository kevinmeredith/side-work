object LazyEvaluationWork {
	def expr = {
		val x = { print("x") ; 1} 		// evaluated immediately
		lazy val y = { print("y"); 2} 	// lazily evaluated
		def z = { print("z"); 3} 		// evaluated upon function cal

		z + y + x + z + y + x
		// print output: x z y z
	}

	def main(args: Array[String]) = { 
		expr
	}
}