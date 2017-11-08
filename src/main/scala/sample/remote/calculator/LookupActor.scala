package sample.remote.calculator

import scala.concurrent.duration._
import akka.actor.Actor
import akka.actor.ActorIdentity
import akka.actor.ActorRef
import akka.actor.Identify
import akka.actor.ReceiveTimeout
import akka.actor.Terminated
import sample.remote.models.{Added, AddedResult, Subtracted, SubtractedResult}

class LookupActor(path: String) extends Actor {

  sendIdentifyRequest()

  def sendIdentifyRequest(): Unit = {
    context.actorSelection(path) ! Identify(path)
    import context.dispatcher
    context.system.scheduler.scheduleOnce(3.seconds, self, ReceiveTimeout)
  }

  def receive = identifying

  def identifying: Actor.Receive = {
    case ActorIdentity(`path`, Some(actor)) =>
      context.watch(actor)
      context.become(active(actor))
    case ActorIdentity(`path`, None) => println(s"Remote actor not available: $path")
    case ReceiveTimeout              => sendIdentifyRequest()
    case _                           => println("Not ready yet")
  }

  def active(actor: ActorRef): Actor.Receive = {
    case op: Added => actor ! op
    case op: Subtracted => actor ! op

    case AddedResult(n1, n2, r) =>
      printf("\nAdd result: %d + %d = %d\n", n1, n2, r)
    case SubtractedResult(n1, n2, r) =>
      printf("\nSub result: %d - %d = %d\n", n1, n2, r)
    case Terminated(`actor`) =>
      println("Calculator terminated")
      sendIdentifyRequest()
      context.become(identifying)
    case ReceiveTimeout =>
    // ignore

  }
}
