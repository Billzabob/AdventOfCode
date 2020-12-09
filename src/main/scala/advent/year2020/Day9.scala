package advent.year2020

import fs2.Stream
import scala.io.Source

object Day9 extends App {
  val input = Stream.emits(Source.fromFile("/Users/nhallstrom/Desktop/input.txt").getLines().toList.map(_.toLong))

  val total = 393911906L

  val stream =
    Stream
      .iterate(2)(_ + 1)
      .flatMap { slideValue =>
        input
          .sliding(slideValue)
          .find(_.sum == total)
      }
      .map(queue => queue.max + queue.min)
      .head

  println(stream.compile.last)
}
