package sample.remote.calculator

import akka.actor.Actor
import sample.remote.models.{Added, AddedResult, Subtracted, SubtractedResult}

class CalculatorActor extends Actor {
  def receive = {
    case Added(n1, n2) =>
      println("Calculating %d + %d".format(n1, n2))
      sender() ! AddedResult(n1, n2, n1 + n2)
    case Subtracted(n1, n2) =>
      println("Calculating %d - %d".format(n1, n2))
      sender() ! SubtractedResult(n1, n2, n1 - n2)

  }
}

