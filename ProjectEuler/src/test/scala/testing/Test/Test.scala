package testing.Test

import common.Work
import org.scalatest._

class Test extends FlatSpec {

  "problem 1" should "return sum of all numbers <= 1000 divisible by 3 or 5" in {
    println(Work.problem1)
  }

  "problem 2" should "return sum of fibonacci numbers < 4,000,000" in {
     println(Work.problem2)
  }

  "find count for fib" should "that should be just less than 4,000,000" in {
    println(Work.fibCountBefore4Mil)
  }

  /*"fib function" should "print out first 10 fibonacci numbers" in {
    val result = Work.fib(10L).toList
    println(result)
    assert(result == List[Long](1, 2, 3, 5, 8, 13,  21, 34, 55, 89))
  }*/

  "find largest prime factor of 33" should "equal 11" in {
    val result = Work.largestPrime(33)
    println(result)
    assert(result.get == 11)
  }

  "find largest prime factor of 28" should "equal 7" in {
    val result = Work.largestPrime(28)
    println(result)
    assert(result.get == 7)
  }

  "find largest prime factor of 7" should "equal None" in {
    val result = Work.largestPrime(7)
    println(result)
    assert(result == None)
  }

  "find largest prime factor of 13195" should "equal 29" in {
    val result = Work.largestPrime(13195)
    println(result)
    assert(result.get == 29)
  }

  /*"find largest prime factor of 600851475143" should "equal ..." in {
    val result = Work.largestPrime(600851475143L)
    println(result)
  }

  "find largest palindrome for product of 2 digit numbers" should "equal 9009" in {
    val result = Work.getLargestPalindromeForXDigits(2)
    println(result)
    assert(result == 9009)
  }

  "find largest palindrome for product of 3 digit numbers" should "equal ???" in {
    val result = Work.getLargestPalindromeForXDigits(3)
    println(result)
  }

  "find smallest number divisible by 1 ... 10" should "return 2520" in {
    val result = Work.smallestNumDivByXNumbers(10, 1)
    println(result)
    assert(result.get == 2520)
  } */

 "find smallest number divisible by 1 ... 20" should "return ???" in {
    val result = Work.smallestNumDivByXNumbers(20, 2000)
    println(result)
  }
}
