package playground

object step57 {
  val chatbot: String => Option[String] = {
    val partialFunction: PartialFunction[String, String] = {
      case "hello" => "Hi, I'm Bot"
      case "bye" => "Bye-bye"
      case "what's up" => "sup-sup"
    }
    partialFunction.lift
  }
}
