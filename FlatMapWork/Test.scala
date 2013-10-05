object Test {
	case class Customer(value: Int)
	case class Consultant(portfolio: List[Customer])
	case class Branch(consultants: List[Consultant])
	case class Company(branches: List[Branch])

	def getCompanyValue(company: Company): Int = {

	  val valuesList = for {
	    branch     <- company.branches      
	    consultant <- branch.consultants
	    customer   <- consultant.portfolio
	  } yield (customer.value)

	  valuesList reduce (_ + _)
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