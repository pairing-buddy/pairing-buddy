package pb.frontend

import org.scalajs.dom
import org.scalajs.dom.html
import org.scalajs.dom.ext._

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.Dynamic.global
import scala.scalajs.js.annotation.JSExport
import scala.util.{Failure, Success}

@JSExport
object PairingBuddyApp {
  @JSExport
  def main(target: html.Form): Unit = {
    target.onsubmit = { e =>
      e.preventDefault()
      Ajax.get("http://localhost:8081/start-session").onComplete {
        case Success(xhr) => dom.window.location.href = xhr.responseText
        case Failure(_) => global.alert("oh nooo")
      }
    }
  }
}
