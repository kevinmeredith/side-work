object Parentheses {
	def main(args: String) = {
		  println("input: " + args)
		  balance(args toList)
	}
	def balance(chars: List[Char]): Boolean = {
		require ( !(chars isEmpty) , "input chars must not be empty")
			def process(innerChars: List[Char], unclosedParens: List[Char]): Boolean = {
					println("innerChars = " + innerChars + " unclosedParens = " + unclosedParens)
					innerChars match {
						case Nil if (unclosedParens isEmpty)            => false
						case Nil if !(unclosedParens isEmpty)           => true
						case '(' :: tail                                => process(innerChars tail, '(' :: unclosedParens)
						case (')' :: tail) if (unclosedParens isEmpty)  => false
						case ')' :: tail				                => process(innerChars tail, unclosedParens drop 1)
						case _                                          => process(innerChars tail, unclosedParens)
					}
			}
		process(chars, Nil)
	}
}

// ( ) )(