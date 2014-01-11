package tests

import Applicative.ValidationApplicative
import DataType.Data._
import org.scalatest._
import _root_.org.scalatest._
import java.util.Date

class TestApplicableWebForm extends FlatSpec with Matchers {

  val applicative = ValidationApplicative.validationApplicative[String]

  "one success and failure" should "result in a single failure" in {
    val s: Validation[String, String] = Success("win!")
    val f: Validation[String, String] = Failure("lose", Vector[String]())
    val result = applicative.map2(s, f)((x, y) => x + y)
    println("result: " + result)
    assert(result == Failure("lose", Vector[String]()))
  }

  "two failures" should "result in a two failures" in {
    val f1: Validation[String, String] = Failure("lost once", Vector[String]())
    val f2: Validation[String, String] = Failure("lost again", Vector[String]())
    val result = applicative.map2(f1, f2)((x, y) => x + y)
    println("result: " + result)
    assert(result == Failure("lost once", Vector[String]("lost again")))
  }

  "two successes" should "result in a success" in {
    val s1: Validation[String, String] = Success("win once")
    val s2: Validation[String, String] = Success("win again")
    val result = applicative.map2(s1, s2)((x, y) => x + "," + y)
    println("result: " + result)
    assert(result == Success("win once,win again"))
  }

}
