import scala.annotation.tailrec

object main extends App {
  import playground.step63._

  val locations = Set("7", "5", "3", "11", "8", "2", "9", "10")
  val point7 = "7" -> Set("8","11")
  val point5 = "5" -> Set("11")
  val point3 = "3" -> Set("8", "10")
  val point11 = "11" -> Set("2", "9", "10")
  val point8 = "8" -> Set("9")
  val point2 = "2" -> Set.empty
  val point9 = "9" -> Set.empty
  val point10 = "10" -> Set.empty

  @tailrec
  def buildNetwork(network: Map[String, Set[String]], locations: Set[String]): Map[String, Set[String]] = {
    if (locations.isEmpty) return network
    buildNetwork(add(network, locations.head), locations.tail)
  }

  val network = buildNetwork(Map.empty, locations)
  val connected = connect(network, "5", "11")
  println(network)
  println(connected)
}
