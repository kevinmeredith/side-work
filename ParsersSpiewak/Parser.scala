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


	def main(args: Array[String]) = {
		val s: Stream[Character] = Stream('f', 'o', 'o')
		val res = keyword("foo")
		println("res: " + res(s))
	}
}