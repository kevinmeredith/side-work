//@author dspiewak 
trait Parser[+A] extends (Stream[Character]=>Result[A])

sealed trait Result[+A]

case class Success[+A](value: A, rem: Stream[Character]) extends Result[A]

case class Failure(msg: String) extends Result[Nothing]

object RegexpParsers {
	 implicit def keyword(str: String) = new Parser[String] {
		def apply(s: Stream[Character]) = {
			val trunc = s take str.length
			lazy val errorMessage = "Expected '%s' got '%s'".format(str, trunc.mkString)

			if (trunc.lengthCompare(str.length) != 0)
				Failure(errorMessage)
			else {
				val succ = trunc.zipWithIndex forall {
					case (c, i) => c == str(i)
				}

				if(succ) Success(str, s drop str.length)
				else Failure(errorMessage)
			}
		}
	}

class SequenceParser[+A, +B](l: =>Parser[A],
							 r: =>Parser[B]) extends Parser[(A, B)] {
	lazy val left = l
	lazy val right = r

	def apply(s: Stream[Character]) = left(s) match {
		case Success(a, rem) => right(rem) match {
			case Success(b, rem) => Success((a,b), rem)
			case f: Failure => f
		}
		case f: Failure => f
	}
}


	def main(args: Array[String]) = {
		//testing simple parser
		val s: Stream[Character] = Stream('f', 'o', 'o')
		val p1 = keyword("foo")
		println("p1(s): " + p1(s))

		val s2: Stream[Character] = Stream('f', 'o', 'o', 'b', 'a', 'r')
		val p2 = keyword("bar")
		println("p2(s): " + p2(s2))

		// testing parser sequence
		val seqPar = new SequenceParser(p1, p2)
		println("seqPar: " + seqPar(s2))
	}
}