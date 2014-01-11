import org.scalacheck.Gen
import org.scalatest._

class TestMonadLaws extends FlatSpec {
	
/*case class Order(item: Item, quantity: Int)
case class Item(name: String, price: Double)

val str = List("foo")

	"the for expression" should "match the map" in {
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
	}*/
	/*
	val genItem: Gen[Item] = for {
		name <- Gen.stringN(3)
		price <- Gen.uniform.map(_ * 10)
	} yield Item(name, price)

	val genOrder2: Gen[Order] = for {
		item <- genItem
		quantity <- Gen.choose(1,100)
	} yield Order(item, quantity)*/
}