package pb.frontend

import io.circe.generic.auto._
import io.circe.parser._
import org.scalajs.dom._
import org.scalajs.dom.ext._
import org.scalajs.dom.raw.HTMLFormElement
import pb.frontend.implicits._
import pb.shared.protocol.Session

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("PairingBuddyApp")
object PairingBuddyApp extends js.JSApp {
  override def main(): Unit =
    document.getElementById("start-session").asInstanceOf[HTMLFormElement].onsubmit = { e =>
      e.preventDefault()

      val futurePath = for {
        xhr     <- Ajax.get("/start-session")
        session <- decode[Session](xhr.responseText).toFuture
      } yield session.path

      futurePath.foreach(window.location.href = _)
    }
}

object implicits {
  implicit class EitherExtension[A](val either: Either[io.circe.Error, A]) extends AnyVal {
    def toFuture: Future[A] = either match {
      case Right(r) => Future.successful(r)
      case Left(e)  => Future.failed(e)
    }
  }
}
