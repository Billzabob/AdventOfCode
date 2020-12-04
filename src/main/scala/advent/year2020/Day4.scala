package advent.year2020

import atto._, Atto._
import scala.io.Source

object Day4 extends App {
  val input = Source.fromFile("/Users/nhallstrom/Desktop/input.txt").getLines().mkString("\n")

  val requiredItems = List("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

  def between(min: Int, max: Int): Parser[Int] =
    int.filter(a => a >= min && a <= max)

  val itemParser = for {
    name  <- take(3) <~ (char(':'))
    value <- stringOf(noneOf(" \n"))
  } yield Item(name, value)

  val passportParser = (itemParser sepBy oneOf(" \n")).map { items =>
    requiredItems.forall(items.map(_.name).contains) && items.forall(_.valid)
  }
  val passportsParser = passportParser sepBy1 string("\n\n")

  println(passportsParser.parseOnly(input).option.map(_.toList.count(valid => valid)))

  case class Item(name: String, value: String) {
    def valid: Boolean = {
      val parser = name match {
        case "byr" => between(1920, 2002)
        case "iyr" => between(2010, 2020)
        case "eyr" => between(2020, 2030)
        case "hgt" => (between(150, 193) <~ string("cm")) | (between(59, 76) <~ string("in"))
        case "hcl" => char('#') ~> manyN(6, hexDigit)
        case "ecl" => choice(List("amb", "blu", "brn", "gry", "grn", "hzl", "oth").map(string))
        case "pid" => manyN(9, digit)
        case "cid" => string(value)
        case _     => err("")
      }
      (parser <~ endOfInput).parseOnly(value).option.isDefined
    }
  }
}
