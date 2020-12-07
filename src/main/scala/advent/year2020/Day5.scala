package advent.year2020

import scala.io.Source

object Day5 extends App {
  val input = Source.fromFile("/Users/nhallstrom/Desktop/input.txt").getLines().toList

  def getId(seat: String): Int = {
    val (fb, lr) = seat.splitAt(7)
    val row      = Integer.parseInt(fb.replace('F', '0').replace('B', '1'), 2)
    val col      = Integer.parseInt(lr.replace('L', '0').replace('R', '1'), 2)
    row * 8 + col
  }

  val ids   = input.map(getId)
  val range = ids.min to ids.max
  println(range.find(id => !ids.contains(id)))
}
