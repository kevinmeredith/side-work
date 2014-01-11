package Applicative

import DataType.Data.{Failure, Success, Validation}

class ValidationHelperMethods {
  def validName(name: String): Validation[String, String]   =
    if(name != "")
      Success(name)
    else Failure("Name cannot be empty", Vector())

  def validBirthdate(birthdate: String): Validation[String, java.util.Date] =
    try {
      import java.text.SimpleDateFormat
      Success((new SimpleDateFormat("yyyy-MM-dd")).parse(birthdate))
    } catch {
      case e: Throwable => Failure("Birthdate must be in the form yyyy-MM-dd", Vector())
    }

  def validPhone(phoneNumber: String): Validation[String, String] =
    if(phoneNumber.matches("[0-9]{10}"))
      Success(phoneNumber)
    else Failure("Phone number must be 10 digits", Vector())
}
