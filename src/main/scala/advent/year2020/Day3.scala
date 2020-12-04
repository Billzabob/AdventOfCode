package advent.year2020

import scala.io.Source

object Day3 extends App {
  val input = Source.fromFile("/Users/nhallstrom/Desktop/input.txt").getLines().toList

  def treeCount(h: Int, v: Int): Int = input.zipWithIndex.count { case (line, index) =>
    line.charAt(index * h / v % line.size) == '#' && index % v == 0
  }

  val slopes = List(
    1 -> 1,
    3 -> 1,
    5 -> 1,
    7 -> 1,
    1 -> 2
  )

  val result = slopes.map { case (h, v) => treeCount(h, v) }.foldLeft(1L)(_ * _)

  println(result)
}
