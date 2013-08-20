object Max {
	def max[A <% Ordered[A]](xs: Seq[A])(greater: (A,A) => Boolean) : A = {
	  def go(xs: Seq[A], f: (A,A) => Boolean, max: A) : A = xs match {
	      case Nil => max
		  case x :: xs => val newMax = f(x, max)
		                  go(xs, f, if (newMax) x else max)
	  }
	  
	  go(xs, greater, xs head)
	}
}


