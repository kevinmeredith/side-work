package tests

import Monads.ListMonad
import org.scalatest._
import _root_.org.scalatest._
import Applicative.OptionApplicative

class TestApplicative extends FlatSpec with Matchers {

  "Testing out OptionApplicative#map3" should "succeed)" in {
    def foo(x: Int, y: Int, z: Int): Int = x*y*z
    val result = OptionApplicative.map3(Some(1), Some(2), Some(3))(foo)
    val expectedResult = Some(6)
    println("map3 result:" + result)
    assert(result == expectedResult)
  }

  "Testing out OptionApplicative#map4" should "succeed" in {
    def foo(a: Int, b: Int, c: Int, d: Int): Int = a*b*c*d
    val result = OptionApplicative.map4(Some(1), Some(2), Some(3), Some(4))(foo)
    val expectedResult = Some(24)
    println("map4 result:" + result)
    assert(result == expectedResult)
  }
}