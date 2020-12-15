package advent.year2020

import cats.Endo
import cats.syntax.all._
import scala.io.Source

object Day12 extends App {
  val input = Source.fromFile("/Users/nhallstrom/Desktop/input.txt").getLines().toList

  def rotateOne: Endo[(Int, Int)] = { case (x, y) => (y, -x) }

  case class Ship(x: Int, y: Int, waypointX: Int, waypointY: Int) {
    def rotate(rotationDegrees: Int): Ship = {
      val numRotates   = (rotationDegrees / 90 + 4) % 4
      val doRotation   = List.fill(numRotates)(rotateOne).foldK
      val (newX, newY) = doRotation(waypointX -> waypointY)
      Ship(x, y, newX, newY)
    }
  }

  val result = input.foldLeft(Ship(0, 0, 10, 1)) { case (s @ Ship(x, y, waypointX, waypointY), line) =>
    line match {
      case s"N$num" => Ship(x, y, waypointX, waypointY + num.toInt)
      case s"S$num" => Ship(x, y, waypointX, waypointY - num.toInt)
      case s"E$num" => Ship(x, y, waypointX + num.toInt, waypointY)
      case s"W$num" => Ship(x, y, waypointX - num.toInt, waypointY)
      case s"L$num" => s.rotate(-num.toInt)
      case s"R$num" => s.rotate(num.toInt)
      case s"F$num" => Ship(x + waypointX * num.toInt, y + waypointY * num.toInt, waypointX, waypointY)
    }
  }

  println(result.x.abs + result.y.abs)
}
