package advanced.playground

import scala.language.implicitConversions
import scala.reflect.ClassManifestFactory.AnyVal

object step22 {
  case class Customer(id: String, name: String)
  case class Order(orderId: String, customer: Customer, date: String)

  sealed trait Checker[T] {
    def compare(orderA: T, orderB: T): Boolean
  }

  object CustomerCheck extends Checker[Order] {
    override def compare(orderA: Order, orderB: Order): Boolean =
      orderA.customer.id == orderB.customer.id
  }

  object DateAndCustomerCheck extends Checker[Order] {
    override def compare(orderA: Order, orderB: Order): Boolean =
      (orderA.customer.id == orderB.customer.id) && (orderA.date == orderB.date)
  }

  val greeting = "My name is [ X ] and I'm a [ Y ]. And I have [ Z ] years of experience."

  case class Greeting(text: String) {
    def name: String = "[ X ]"
    def occupation: String = "[ Y ]"
    def level: String = "senior"
  }

  object Greeting {
    implicit def string2Greeting(value: String): Greeting = Greeting(value)
  }

  implicit class RichString(value: String) {
    def isMiddle: Boolean = value == "middle"
  }
}
