package advent.year2020

import fs2.{Pipe, Pull, Stream}

object utils {
  def countConsecutive[F[_], A](f: (A, A) => Boolean): Pipe[F, A, Int] = stream => {
    def go(count: Int, last: A, s: Stream[F, A]): Pull[F, Int, Unit] = {
      s.pull.uncons1.flatMap {
        case Some((h, t)) if f(last, h) => go(count + 1, h, t)
        case Some((h, t))               => Pull.output1(count) >> go(1, h, t)
        case None                       => Pull.output1(count)
      }
    }

    stream.pull.uncons1.flatMap {
      case Some((h, t)) => go(1, h, t)
      case None         => Pull.done
    }.stream
  }
}
