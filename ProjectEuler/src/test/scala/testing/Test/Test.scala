package testing.Test

import common.Work
import org.scalatest._

class Test extends FlatSpec {
  /*
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
  }*/

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

  /*
  // http://en.wikipedia.org/wiki/List_of_prime_numbers
  "the 500th prime number" should "equal 3571" in {
    val x = Work.findNthPrime(500)
    println(x)
    assert(x == 3571)
  }

  "the 10,001th prime number" should "equal 104743(wrong on PE though)" in {
    val x = Work.findNthPrime(10)
    println(x)
    assert(x == 104743)
  } */

  "the largest 5-digit consecutive product" should "equal ???" in {
    val x = """73167176531330624919225119674426574742355349194934
    96983520312774506326239578318016984801869478851843
    85861560789112949495459501737958331952853208805511
    12540698747158523863050715693290963295227443043557
    66896648950445244523161731856403098711121722383113
    62229893423380308135336276614282806444486645238749
    30358907296290491560440772390713810515859307960866
    70172427121883998797908792274921901699720888093776
    65727333001053367881220235421809751254540594752243
    52584907711670556013604839586446706324415722155397
    53697817977846174064955149290862569321978468622482
    83972241375657056057490261407972968652414535100474
    82166370484403199890008895243450658541227588666881
    16427171479924442928230863465674813919123162824586
    17866458359124566529476545682848912883142607690042
    24219022671055626321111109370544217506941658960408
    07198403850962455444362981230987879927244284909188
    84580156166097919133875499200524063689912560717606
    05886116467109405077541002256983155200055935729725
    71636269561882670428252483600823257530420752963450"""

    val result = Work.problem8(5, x)
    println(result)
  }


}
