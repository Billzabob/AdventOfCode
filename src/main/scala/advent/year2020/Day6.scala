package advent.year2020

import scala.io.Source

object Day6 extends App {
  val input = Source.fromFile("/Users/nhallstrom/Desktop/input.txt").getLines().mkString("\n")

  val result = input
    .split("\n\n")
    .map { group =>
      val people = group.split('\n')
      people.reduce(_ intersect _).size
    }
    .sum

  println(result)
}
