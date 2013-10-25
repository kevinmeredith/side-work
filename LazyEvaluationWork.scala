object LazyEvaluationWork {
	def expr = {
		val x = { print("x") ; 1} 		// evaluated immediately
		lazy val y = { print("y"); 2} 	// lazily evaluated
		def z = { print("z"); 3} 		// evaluated upon function cal

		z + y + x + z + y + x
		// print output: x z y z
	}

	
	def expr2 = {
		def f = { print("foo");  5}
		val x = f
		print("after x's definition")
		x
	}

	def main(args: Array[String]) = { 
		println(expr2) // prints "fooafter x's definition5"
	}
}