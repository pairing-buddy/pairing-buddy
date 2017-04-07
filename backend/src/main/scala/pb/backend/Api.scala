package pb.backend

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.{Directives, Route}
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._

class Api extends Directives {
  val route: Route = cors() {
    path("start-session") {
      get {
        complete {
          "https://hangouts.google.com/hangouts/_/"
        }
      }
    }
  }
}

object Api extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val ec:ExecutionContext = system.dispatcher
  val api = new Api
  Http().bindAndHandle(api.route, "localhost", 8082).onComplete {
    case Success(_) => println("started")
    case Failure(_) => system.terminate()
  }
}