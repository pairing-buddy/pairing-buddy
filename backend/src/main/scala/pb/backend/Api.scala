package pb.backend

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.{Directives, Route}
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import pb.backend.infrastructure.twirl._

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class Api extends Directives {
  val route: Route =
  pathSingleSlash {
    get {
      complete {
        html.index.render("TEST")
      }
    }
  } ~
  path("start-session") {
    get {
      complete {
        "https://hangouts.google.com/hangouts/_/"
      }
    }
  } ~
  pathPrefix("assets" / Remaining) { file =>
    encodeResponse {
      getFromResource("public/" + file)
    }
  }
}

object Api extends App {
  implicit val system               = ActorSystem()
  implicit val materializer         = ActorMaterializer()
  implicit val ec: ExecutionContext = system.dispatcher
  val config = ConfigFactory.load().getConfig("pairing-buddy")

  private val host = config.getString("host")
  private val port = config.getInt("port")

  Http().bindAndHandle((new Api).route, host, port).onComplete {
    case Success(_) => println(s"Pairing buddy available at http://$host:$port/")
    case Failure(_) => system.terminate()
  }
}
