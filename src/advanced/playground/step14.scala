package advanced.playground

object step14 {
  trait Customer {
    def print: Unit = println("customer")
  }

  trait Boss {
    def print: Unit = println("boss")
  }

  trait Employee extends Boss {
    override def print: Unit = {
      println("employee")
      super.print
    }
  }

  class BusinessEnv extends Boss with Employee with Customer {
    override def print: Unit = {
      println("business env")
      super.print
    }
  }

  class Modifier[A](var modifier: A)
//  {
//    def modifier: A = someVar
//
//    def modifier_=(value: A): Unit = {
//      someVar = value
//    }
//  }
}
