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

 /*"find smallest number divisible by 1 ... 20" should "return ???" in {
    val result = Work.smallestNumDivByXNumbers(20, 2000)
    println(result)
  }

  "sumSquared - sum of squares from 1 ... 10" should "equal 2640" in {
    val x = Work.sumSquared(10)
    assert(x == 3025)
    val y = Work.sumOfSquares(10)
    assert(y == 385)
    assert(x - y == 2640)
  }

  "sumSquared - sum of squares from 1 to 100" should "equal 2.516415E7" in {
    val x = Work.sumSquared(100)
    val y = Work.sumOfSquares(100)
    val z = x - y
    println(z)
    assert(z == 25164150)
  }   */

  "the 6th prime number" should "equal 13" in {
    val sixthPrime = Work.findNthPrime(6)
    println(sixthPrime)
    assert(sixthPrime == 13)
  }

  "the 10,001th prime number" should "equal 104743" in {
    val x = Work.findNthPrime(10001)
    println(x)
    assert(x == 104743)
  }


}
