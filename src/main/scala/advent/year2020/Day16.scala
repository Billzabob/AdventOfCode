package advent.year2020

import atto._, Atto._
import scala.io.Source

object Day16 extends App {
  val input = Source.fromFile("/Users/nhallstrom/Desktop/input.txt").getLines().mkString("\n")

  case class Ticket(values: List[Int])
  case class Rule(name: String, range1: Range, range2: Range) {
    def valid(value: Int) = range1.contains(value) || range2.contains(value)
  }

  val range = for {
    first <- int <~ char('-')
    last  <- int
  } yield first to last

  val rule =
    for {
      name   <- takeWhile(_ != ':') <~ string(": ")
      range1 <- range <~ string(" or ")
      range2 <- range
    } yield Rule(name, range1, range2)

  val rulesParser = rule sepBy char('\n')

  val ticket = (int sepBy char(',')).map(Ticket)

  val parser = for {
    allRules     <- rulesParser <~ string("\n\nyour ticket:\n")
    myTicket     <- ticket <~ string("\n\nnearby tickets:\n")
    otherTickets <- ticket sepBy char('\n')
  } yield (allRules, myTicket, otherTickets)

  val (rules, myTicket, otherTickets) = parser.parseOnly(input).option.get

  val validTickets = (myTicket :: otherTickets).filter(_.values.forall(value => rules.exists(_.valid(value))))

  val validRules = (0 until rules.size).toList.map { fieldIndex =>
    val fieldValues = validTickets.map(_.values(fieldIndex))
    rules.filter(rule => fieldValues.forall(rule.valid))
  }

  val departureIndexes = simplify(validRules).map(_.name).zipWithIndex.collect {
    case (name, index) if name.startsWith("departure") => index
  }

  val result = departureIndexes.map(myTicket.values).map(_.toLong).product

  println(result)

  def simplify(possibleRules: List[List[Rule]]): List[Rule] = {
    def go(possibleRules: List[List[Rule]]): List[List[Rule]] = {
      if (possibleRules.forall(_.size == 1)) possibleRules else {
        val solvedRules = possibleRules.filter(_.size == 1).map(_.head)
        val step = possibleRules.map(rules => if (rules.size == 1) rules else rules.filterNot(solvedRules.contains))
        go(step)
      }
    }

    go(possibleRules).map(_.head)
  }
}
