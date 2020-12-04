package advent.year2020

import scala.io.Source

object Day2 extends App {
  val input = Source.fromFile("/Users/nhallstrom/Desktop/input.txt").getLines()

  val result = input.count {
    case s"$first-$second $letter: $password" => {
      password.charAt(first.toInt - 1) == letter.head ^ password.charAt(second.toInt - 1) == letter.head
    }
  }

  println(result)
}
