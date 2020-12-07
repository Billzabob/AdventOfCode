package advent.year2020

import cats.syntax.all._
import scala.io.Source

object Day7 extends App {
  val input = Source.fromFile("/Users/nhallstrom/Desktop/input.txt").getLines().toList

  val bagMap = input.foldMap { case s"$container bags contain $rest." =>
    val contained = rest.split(',').map(_.trim).toList.flatMap {
      case s"no other bags"       => Nil
      case s"$count $color bag$_" => List(color -> count.toInt)
    }
    Map(container -> contained.toSet)
  }

  def bagsWithin(bag: String): Int =
    bagMap(bag).map { case (color, count) =>
      count + count * bagsWithin(color)
    }.sum

  println(bagsWithin("shiny gold"))
}
