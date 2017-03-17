package pb.frontend

import org.scalajs.dom.html

import scala.scalajs.js.annotation.JSExport

@JSExport
object PairingBuddyApp {
  @JSExport
  def main(target: html.Div): Unit = {
    val (f, d) = ("Arturas", "Kacper")
    target.innerHTML = s"""
    <div>
      <h1>Hello World!</h1>
      <p>
        The quick brown <b>$f</b>
        jumps over the lazy <i>$d</b>
      </p>
    </div>
    """
  }
}
