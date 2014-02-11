package test

import common.IO1.IO
import common.IO1.Util._
import org.scalatest._

class IOTesting extends FlatSpec {

  "Testing `echo`" should "show a user's input" in {
    val echo = ReadLine.flatMap(PrintLine)
    println("enter text")
    echo.run
  }

  "Read an Int" should "read a user's input and convert to an Int" in {
    val readInt = ReadLine.map(x => {println("_.toInt"); x.toInt})
    println("enter an int")
    readInt.run
  }

  "the user should be prompted for two inputs" should "print out their product" in {
    val readInts: Int = readInt * readInt
    println("readInts:" + readInts)

  }

  "calling `PrintLine('hello world')`" should "print out 'hello world'" in {
    val printLine = PrintLine("hello world")
    printLine.run
  }
}
