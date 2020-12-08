package advent.year2020

import atto.Atto._
import scala.io.Source

object Day8 extends App {
  val input = Source.fromFile("/Users/nhallstrom/Desktop/input.txt").getLines().map(parse).toArray

  def parse(s: String): (String, Int) =
    ((take(3) <~ char(' ')) ~ int).parseOnly(s).option.get

  def run(currentInstruction: Int, seenInstructions: Set[Int], total: Int): Option[Int] = {
    if (seenInstructions contains currentInstruction) None
    else if (currentInstruction == input.size) Some(total)
    else {
      def nop             = run(currentInstruction + 1, seenInstructions + currentInstruction, total)
      def acc(count: Int) = run(currentInstruction + 1, seenInstructions + currentInstruction, total + count)
      def jmp(count: Int) = run(currentInstruction + count, seenInstructions + currentInstruction, total)
      input(currentInstruction) match {
        case ("nop", count) => nop orElse jmp(count)
        case ("acc", count) => acc(count)
        case ("jmp", count) => jmp(count) orElse nop
      }
    }
  }

  println(run(0, Set.empty, 0))
}
