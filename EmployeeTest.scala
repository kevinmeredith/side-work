object EmployeeTest {
  def main(args: Array[String]) {
	case class Employee(name: String, department: String)
	
	val employeesByName: Map[String, Employee] = 
		List(Employee("Alice", "R&D"), Employee("Bob", "Accounting")).
		map(e => (e.name, e)).toMap
		
	val dept: Option[String] = employeesByName.get("Joe").map(_.department)
	println("dept: " + dept)

	val dept2: Option[String] = employeesByName.get("Alice").map(_.department)
	println("dept2: " + dept2)
	
	}
}