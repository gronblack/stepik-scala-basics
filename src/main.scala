
object main extends App {
  import advanced.playground.step23._

  val data = JsonObject(Map(
    "name" -> JsonString("Bob"),
    "items" -> JsonArray(
      List(
        JsonInteger(1),
        JsonString("Stuff to buy"),
        JsonArray(List(JsonString("oneElem"), JsonString("twoElem"))))
    )
  ))
  println(data.stringify)
}
