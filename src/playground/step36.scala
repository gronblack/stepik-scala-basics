package playground

class step36(val name: String) {
  def unary_+ : step36 = new step36(s"$name NoSurname")
}
