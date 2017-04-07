package pb.frontend

import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.ext._
import org.scalajs.dom.raw.HTMLFormElement

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js
import scala.scalajs.js.Dynamic.global
import scala.scalajs.js.annotation.JSExport
import scala.util.{Failure, Success}

@JSExport
object PairingBuddyApp extends js.JSApp {
  override def main(): Unit =
    document.getElementById("start-session").asInstanceOf[HTMLFormElement].onsubmit = { e =>
      e.preventDefault()
      Ajax.get("/start-session").onComplete {
        case Success(xhr) => dom.window.location.href = xhr.responseText
        case Failure(_)   => global.alert("oh nooo")
      }
    }
}
