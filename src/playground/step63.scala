package playground

import scala.annotation.tailrec

object step63 {
  def add(network: Map[String, Set[String]], location: String): Map[String, Set[String]] = {
    network + (location -> Set())
  }

  def remove(network: Map[String, Set[String]], location: String): Map[String, Set[String]] = {
    @tailrec
    def loop(destinations: Set[String], acc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (destinations.isEmpty) acc
      else loop(destinations.tail, disconnect(acc, location, destinations.head))

    val disconnected = loop(network(location), network)
    disconnected - location
  }

  def connect(network: Map[String, Set[String]], pointA: String, pointB: String): Map[String, Set[String]] = {
    network + (pointA -> (network(pointA) + pointB)) + (pointB -> (network(pointB) + pointA))
  }

  def disconnect(network: Map[String, Set[String]], pointA: String, pointB: String): Map[String, Set[String]] = {
    network + (pointA -> (network(pointA) - pointB)) + (pointB -> (network(pointB) - pointA))
  }

  def nFlights(network: Map[String, Set[String]], location: String): Int = {
    network(location).size
  }

  def mostFlights(network: Map[String, Set[String]]): String = {
    network
      .toList
      .sortWith((pointA, pointB) => pointA._2.size > pointB._2.size)
      .head._1
  }

  def nLocationsWithNoFlights(network: Map[String, Set[String]]): Int = {
    network.count(_._2.isEmpty)
  }

  def isConnected(network: Map[String, Set[String]], pointA: String, pointB: String): Boolean = {
    @tailrec
    def search(goal: String, queue: Set[String], visited: Set[String]): Boolean = {
      if (queue.isEmpty) return false
      if (queue.head == goal) return true
      val newPoints = network(queue.head).diff(visited)
      search(goal, queue.tail ++ newPoints, visited ++ newPoints)
    }
    search(pointB, Set(pointA), Set.empty)
  }
}
