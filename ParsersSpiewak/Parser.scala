//@author dspiewak 
class SequenceParser[+A, +B](l: =>Parser[A],
							 r: =>Parser[B]) extends Parser[(A, B)] {
	lazy val left = l      // "foo"
	lazy val right = r     // "baz"

	def apply(s: Stream[Character]) = left(s) match { // Stream('f','o','o','b','a','r')
		case Success(a, rem) => right(rem) match { // Stream('b','a','r')
			case Success(b, rem) => Success((a,b), rem) // Failure("got bar, expected baz")
			case f: Failure => f
		}
		case f: Failure => f
	}
}

// Disjunctive Combinator (OR logic)
class DisParser[+A](left: Parser[A], right: Parser[A]) extends Parser[A] {
  def apply(s: Stream[Character]) = left(s) match {
    case res@Success(_, _) => res
    case _: Failure => right(s)
  }
}

// ~> is a parser combinator for sequential composition which keeps only the right result.
// @author kmeredith
class KeepRightParser[+A](left: =>Parser[A], 
	                     right: =>Parser[A]) extends Parser[A] {
	def apply(s: Stream[Character]) = left(s) match {
		case Success(_, rem) => right(rem)
		case f: Failure => f
	}
}

// <~ is a parser combinator for sequential composition which keeps only the left result.
// @author kmeredith
class KeepLeftParser[+A](left: =>Parser[A], 
	                     right: =>Parser[A]) extends Parser[A] {
	def apply(s: Stream[Character]) = left(s) match {
		case Success(a, rem) => right(rem) match { 
			case Success(_, _) => Success(a, rem)
			case f: Failure => f
		}
		case f: Failure => f
	}
}

trait Parser[+A] extends (Stream[Character]=>Result[A]) {
	def ~[B](that: =>Parser[B]) = new SequenceParser(this, that)
	def |[AA >: A](that: Parser[AA]) = new DisParser(this, that)
}

sealed trait Result[+A]

case class Success[+A](value: A, rem: Stream[Character]) extends Result[A]

case class Failure(msg: String) extends Result[Nothing]

object RegexpParsers {
	 implicit def keyword(str: String) = new Parser[String] { 
		def apply(s: Stream[Character]) = { // s = Stream('f','o','o','b','a','r')
			val trunc = s take str.length // parser(str) = foo
			lazy val errorMessage = "Expected '%s' got '%s'".format(str, trunc.mkString)

			if (trunc.lengthCompare(str.length) != 0)
				Failure(errorMessage)
			else {
				val succ = trunc.zipWithIndex forall { // truncStr = Vector( (f,0), (o,1), (o,2))
					case (c, i) => c == str(i)         // parser = "foo"
				}

				if(succ) Success(str, s drop str.length) // Success(foo, Stream('b','a','r'))
				else Failure(errorMessage)
			}
		}
	}
	//@author kmeredith examples of above code
	def main(args: Array[String]) = {
		//testing simple parser
		val s: Stream[Character] = Stream('f', 'o', 'o')
		val p1 = keyword("foo")
		println("p1(s): " + p1.apply(s))

		val s2: Stream[Character] = Stream('f', 'o', 'o', 'b', 'a', 'r')
		val p2 = keyword("bar")
		println("p2(s): " + p2(s2))

		// testing parser sequence
		val seqPar = new SequenceParser(p1, p2)
		println("seqPar: " + seqPar(s2))

		// Using the ~ operator where "foo" and "bar" are two Parser Combinators
		// note that, I believe you can pass only a string due to the `keyword` implicit
		println("AND : " + ("foo" ~ "bar")(s2))

		val s3: Stream[Character] = Stream('f', 'o', 'o', 'b', 'a', 'z')
		println("OR :" + ("bar" | "baz")(s3)) 
		println("OR :" + ("bar" | "foo")(s3)) 

		val s4 = Stream[Character]('f', 'o', 'o', 'b', 'a', 'r', 'b', 'u', 'z', 'z')
		val krp = new KeepRightParser("foo", "bar")
		println("~> test: " + krp(s4))

		val klp = new KeepLeftParser("foo", "bar")
		println("<~ test: " + klp(s4))
	}
}