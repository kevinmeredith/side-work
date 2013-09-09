object ParallelTest {
  def main(args: Array[String]) {
    val start = System.nanoTime()
	val list = (1 to 1000000).toList.par
	println("with par: elapsed: " + (System.nanoTime() - start) / 1000000 + " milliseconds")
	
	val start2 = System.nanoTime()
	val list2 = (1 to 1000000).toList
	println("without par: elapsed: " + (System.nanoTime() - start2) / 1000000 + " milliseconds")
  } 
}


