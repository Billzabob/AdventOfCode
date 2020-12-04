package advent.year2020

import fs2.Stream
import scala.io.Source

object Day1 extends App {
  val input = Source.fromFile("/Users/nhallstrom/Desktop/input.txt").getLines().toList.map(_.toLong)

  val stream = for {
    a <- Stream.emits(input)
    b <- Stream.emits(input)
    c <- Stream.emits(input)
  } yield (a, b, c)

  val result =
    stream
      .find { case (a, b, c) => a + b + c == 2020 }
      .compile
      .toList
      .headOption
      .map { case (a, b, c) => a * b * c }

  println(result)
}
