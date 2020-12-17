package advent.year2020

import scala.collection.mutable

object Day15 extends App {
  val input         = List(9, 3, 1, 0, 8, 4)
  val numbersSpoken = input.init.zipWithIndex.to(mutable.Map)
  var last          = input.last

  for (i <- input.size until 2020) {
    val lastIndex = i - 1
    val next = numbersSpoken.get(last) match {
      case Some(value) => lastIndex - value
      case None        => 0
    }
    numbersSpoken += last -> lastIndex
    last = next
  }
  println(last)
}
