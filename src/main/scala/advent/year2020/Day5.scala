package advent.year2020

import scala.io.Source

object Day5 extends App {
  val input = Source.fromFile("/Users/nhallstrom/Desktop/input.txt").getLines().toList

  def getId(seat: String): Int = {
    val (fb, lr) = seat.splitAt(7)
    val row      = binaryBoarding(fb, 0 to 127, "F", "B")
    val col      = binaryBoarding(lr, 0 to 7, "L", "R")
    row * 8 + col
  }

  def binaryBoarding(s: String, nums: Seq[Int], top: String, bot: String): Int = {
    val (h, t) = s.splitAt(1)
    h match {
      case ""    => nums.head
      case `top` => binaryBoarding(t, nums.take(nums.size / 2), top, bot)
      case `bot` => binaryBoarding(t, nums.drop(nums.size / 2), top, bot)
    }
  }

  val ids   = input.map(getId)
  val range = ids.min to ids.max
  println(range.find(id => !ids.contains(id)))
}
