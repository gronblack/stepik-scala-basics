package base.exercises.playground

import scala.io.StdIn.readLine

object step56 {
  def guard(data: Any, maxLength: Int): String = {
    data match {
      case list: List[Any] if list.length <= maxLength => "Задан список List допустимой длины"
      case list: List[Any] if list.length > maxLength => "Длина списка больше максимально допустимого значения"
      case string: String if string.length <= maxLength => "Длина строки не превышает максимально допустимого значения"
      case string: String if string.length > maxLength => "Получена строка недопустимой длины"
      case _ => "Что это? Это не строка и не список"
    }
  }



  readLine() match {
    case Human(fullName) => println(fullName)
  }

  object Human {
    def unapply(fullName: String): Option[String] = {
      val pattern = "^(\\w+) (\\w+)$".r
      fullName match {
        case pattern(name, surname) => Some(s"${name.head}. ${surname.head}.")
        case _ => Some("Oops, something is wrong")
      }
    }
  }
}
