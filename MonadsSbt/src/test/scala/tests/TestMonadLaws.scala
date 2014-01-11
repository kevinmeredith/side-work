package tests

import Monads.ListMonad
import org.scalatest._
import _root_.org.scalatest._

class TestMonadLaws extends FlatSpec with Matchers {

	/*"Calling join on List(1,2,3) with x => List(x)" should "result in List(10, 20, 30)" in {
		val list: List[Int] = List(1,2,3)
		def f(x: Int) = List(x*10)
    val expected = List(10, 20, 30)

    val result = ListMonad.flatMap(list)(f)
    println("result: " + result)
    println("expected: " + expected)
    assert(result == expected)
	} */

	/*"the for expression" should "match the map" in {
		val genOrder: Gen[Order] = for {
			name <- str
			price <- Gen.choose(1, 100).map(_ * 10)
			quantity <- Gen.choose(1,100)
		} yield Order(Item(name, price), quantity)
		println("for-expression: " + genOrder)
	}

	"the map representation" should "match the map" in {
		val genOrder: Gen[Order] = str.flatMap(name => Gen.uniform.map(_ * 10)
												  .map(price => Gen.choose(1, 100)
												  .map(quantity => Order(Item(name, price), quantity))))

		println("map " + genOrder)
	}

	val genItem: Gen[Item] = for {
		name <- Gen.stringN(3)
		price <- Gen.uniform.map(_ * 10)
	} yield Item(name, price)

	val genOrder2: Gen[Order] = for {
		item <- genItem
		quantity <- Gen.choose(1,100)
	} yield Order(item, quantity)
	*/
}