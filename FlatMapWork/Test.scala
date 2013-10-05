/**
 * Working through exercise posted on Stackoverflow: 
 * http://stackoverflow.com/questions/14598990/confused-with-the-for-comprehension-to-flatmap-map-transformation
 */

object Test {
	case class Customer(value: Int)
	case class Consultant(portfolio: List[Customer])
	case class Branch(consultants: List[Consultant])
	case class Company(branches: List[Branch])

	def getCompanyValue(company: Company): Int = {

	  val valuesList = for {
	    branch     <- company.branches     // fllatens company
	    consultant <- branch.consultants   // flattens branch
	    customer   <- consultant.portfolio // flattens consultant
	  } yield (customer.value)             // maps across all `Customer`'s of every portfolio in 
	                                       // all branches of the entire company

	  valuesList reduce (_ + _)            // reduces List[Int] -> Int, adding together each customer.value
	}

	def main(args: Array[String]) = {
		val customer1 = Customer(1)
		val customer2 = Customer(2)
		val customer3 = Customer(3)

		val consultant1 = Consultant(List(customer1, customer2, customer3))
		val branch = Branch(List(consultant1))
		val company = Company(List(branch))

		println("getCompanyValue(company) : " + getCompanyValue(company))
	}
}