package advent.year2020

import fs2.Stream
import scala.io.Source
import utils.countConsecutive

object Day10 extends App {
  val input = Source.fromFile("/Users/nhallstrom/Desktop/input.txt").getLines().map(_.toInt).toList.sorted

  val joltsStream = Stream.emits(input.prepended(0).appended(input.last + 3))

  def incremented(a: Int, b: Int) = b - a == 1

  def tribonacci: Int => Int = {
    case 0 => 0
    case 1 => 0
    case 2 => 1
    case m => tribonacci(m - 1) + tribonacci(m - 2) + tribonacci(m - 3)
  }

  val result =
    joltsStream
      .through(countConsecutive(incremented))
      .filter(_ > 1)
      .map(a => tribonacci(a + 1))
      .compile
      .fold(1L)(_ * _)

  println(result)
}
