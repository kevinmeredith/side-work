object StreamFPIS {
	def toList[A](stream: Stream[A]): List[A] = {
		def go(s: Stream[A], acc: List[A]): List[A] = s match {
			case x #:: xs => go(xs, acc :+ x)
			case _ => acc
		}
		go(stream, Nil)
	}

	def main(args: Array[String]) = { 
		val s = Stream(1,2,3)
		val output = StreamFPIS.toList(s)
		assert(output == List(1,2,3))
	}
} 