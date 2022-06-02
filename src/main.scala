
object main extends App {
  import playground.step71._

  val tree = Node(1,
    Node(2,
      Node(4,
        TreeEnd,
        TreeEnd),
      Node(5,
        TreeEnd,
        Node(8,
          TreeEnd,
          TreeEnd))),
    Node(3,
      Node(6,
        TreeEnd,
        TreeEnd),
      Node(7,
        TreeEnd,
        TreeEnd)))

  println(tree)
  println(tree.collectLeaves.map(_.value).sortWith((a, b) => a < b))
}
