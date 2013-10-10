// Use a for expression rather than `var` Map
object ReplaceVarMapWithForExpressionTest  {
	
	def getHeadOfMap(): Unit = {
		val map1 = Map("name" -> "1", "comparison" -> "=")
		val map2 = Map("age" -> "2", "comparison" -> "=")
		val list = List(map1, map2)

		val result: List[(String, String)] = for {
			x <- list
		} yield x.head
		println(result)
		println(result.toMap)
	}

	def main(args: Array[String]) {
		getHeadOfMap()
	}

}