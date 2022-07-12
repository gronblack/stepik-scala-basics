package advanced.playground

object step21 {
  case class Course(id: Int, title: String)

  val courses = List(
    Course(0, "Scala"),
    Course(1, "Advanced Scala"),
    Course(1, "Super Advanced Scala"),
    Course(4, "Spark"),
    Course(3, "Cats")
  )

  implicit val courseOrdering: Ordering[Course] = Ordering.by(c => (c.id, c.title))

//  implicit val courseOrdering: Ordering[Course] = Ordering.fromLessThan { (courseA: Course, courseB: Course) =>
//    val idCompare = courseA.id compareTo courseB.id
//    if (idCompare == 0) courseA.title < courseB.title else idCompare < 0
//  }

  import advanced.playground.step21.Person._

  case class Person(age: Int) {

    def increaseAge: Unit = println(age + 1)
  }

  object Person {
    import scala.language.implicitConversions

    implicit def str2Person(param: String): Person = Person(param.toInt)
  }

  case class Price(price: Int)

  implicit class PriceOps(p: Price)  {
    def -(n: Int): Int = Price(p.price - n).price
  }


}
