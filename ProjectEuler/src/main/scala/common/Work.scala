package common

import scala.annotation.tailrec

object Work {

  /**
   * Problem 1. If we list all the natural numbers below 10 that are multiples of 3 or 5, we get
   * 3, 5, 6 and 9. The sum of these multiples is 23. Find the sum of all the multiples
   * of 3 or 5 below 1000.
   */
  def problem1: Int = {
    val x = Stream.range(1, 1000).filter {
      x => (x % 3 == 0) || (x % 5 == 0)
    }
    x.foldLeft(0)(_ + _)
  }

  /**
   * Problem 2. Each new term in the Fibonacci sequence is generated by adding the previous two terms.
   * By starting with 1 and 2, the first 10 terms will be:
   * 1, 2, 3, 5, 8, 13,  21, 34, 55, 89
   * By considering the terms in the Fibonacci sequence whose values do not exceed four million,
   * find the sum of the even-valued terms.
   */
  def fib(count: Long) = {
    @tailrec
    def go(num1: Long, num2: Long, count: Long, stream: Stream[Long]): Stream[Long] = count match {
      case 1 => stream
      case n => go(num1 + num2, num1, n - 1, stream :+ (num1 + num2))
    }
    go(1, 1, count, Stream[Long](1))
  }

  def fibCountBefore4Mil: Long = {
    @tailrec
    def go(x: Long): Long = {
      if(fib(x).exists{ x => x > 4000000} ) x - 1
      else go(x + 1)
    }
    go(1)
  }

  def problem2: Long = {
    val fibs = fib(fibCountBefore4Mil)
    //val y = fibs.map{x => if(x > 4000000) 0 else x}.map{x => if(x % 2 == 0) x else 0}
    fibs.filter{x => x % 2 == 0}.foldLeft(0L)(_ + _)
  }

  // TODO: memoize primes
  val primes = scala.collection.mutable.HashSet[Long]()

  /**
   * The prime factors of 13195 are 5, 7, 13 and 29. What is the
   * largest prime factor of the number 600851475143?
   */
  def isPrime(x: Long): Boolean = {
    println("is prime?: " + x)
    if(x < 2) false
    if(primes.contains(x)) true
    @tailrec
    def go(p: Long): Boolean = p match {
      case 1 => primes.add(p); true    // only divisible by 1 (and itself)
      case a if x % a == 0 => false
      case _ => go(p - 1)
    }
    go(x - 1)
  }

  def largestPrime(x: Long): Option[Long] = {
    @tailrec
    def go(p: Long): Option[Long] = p match {
      case a if a < 2 => None
      case a if (isPrime(a) && x % a == 0) => Some(a)
      case _ => go(p - 1)
    }
    go(x - 1)
  }
}
