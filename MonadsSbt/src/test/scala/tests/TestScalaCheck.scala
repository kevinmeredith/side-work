package tests

import org.scalatest._

class TestScalaCheck extends FlatSpec {

  case class Order(item: Item, quantity: Int)
  case class Item(name: String, price: Double)

	/*"a test" should "print out Gen.choose(1, 100)" in {
		val myGen = for {
		  n <- Gen.choose(10,20)
		  m <- Gen.choose(2*n, 500)
		} yield (n,m)

    /*"testing Monad's associativity law" should "prove true" in{
      val genOrder: Gen[Order]
    } */

		println(myGen)

		val c = Gen.oneOf("Hello", "World")
		println(c)

	} */
}