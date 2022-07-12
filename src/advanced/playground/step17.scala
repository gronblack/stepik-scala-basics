package advanced.playground

object step17 {
  case class User(name: String, bf: User)

  val users = List(
    User("Mike", User("Sam", User("Bob", User("Alice", null)))),
    User("John", User("Joe", null)),
  )

  object Service {
    def findUser(users: List[User], name: String): List[User] =
      users.find(_.name == name) match {
        case Some(user) => List(user)
        case _ =>
          println("No user")
          List.empty[User]
      }
  }

  def getBf(user: User) = List(Option(user.bf)
    .getOrElse(User("No bf", null)))


  val nameToFind = "John"
  val bf = Service.findUser(users, nameToFind).flatMap(getBf).flatMap(getBf)
}
