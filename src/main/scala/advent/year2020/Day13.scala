package advent.year2020

import fs2.Stream
import scala.io.Source

object Day13 extends App {
  val input = Source.fromFile("/Users/nhallstrom/Desktop/input.txt").getLines().toList

  val result = Stream
    .emits(input(1).split(','))
    .map(_.toLongOption)
    .zipWithIndex
    .collect { case (Some(bus), index) => (bus, index) }
    .zipWithScan(1L) { case (step, (bus, _)) => step * bus }
    .scan(0L) { case (start, ((bus, offset), step)) =>
      Stream
        .iterate(start)(_ + step)
        .find { time =>
          (time + offset) % bus == 0
        }
        .compile
        .last
        .get
    }
    .compile
    .last

  println(result)
}
