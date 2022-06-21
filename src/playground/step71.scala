package playground

object step71 {
  abstract class BinaryTree[+T] {
    def value: T

    def leftChild: BinaryTree[T]

    def rightChild: BinaryTree[T]

    def isEmpty: Boolean

    def isLeaf: Boolean

    def collectLeaves: List[BinaryTree[T]]

    def countLeaves: Int

    def nodesAtLevel(level: Int): List[BinaryTree[T]]

    def collectNodes(): List[T]

    def hasPath(tree: BinaryTree[Int], target: Int): Boolean
  }

  case object TreeEnd extends BinaryTree[Nothing] {
    override def value: Nothing = throw new NoSuchElementException

    override def leftChild: BinaryTree[Nothing] = throw new NoSuchElementException

    override def rightChild: BinaryTree[Nothing] = throw new NoSuchElementException

    override def isEmpty: Boolean = true

    override def isLeaf: Boolean = false

    override def collectLeaves: List[BinaryTree[Nothing]] = List.empty

    override def countLeaves: Int = 0

    override def nodesAtLevel(level: Int): List[Nothing] = List.empty

    override def collectNodes(): List[Nothing] = List.empty

    override def hasPath(tree: BinaryTree[Int], target: Int): Boolean = false
  }

  case class Node[+T](
      override val value: T,
      override val leftChild: BinaryTree[T],
      override val rightChild: BinaryTree[T])
    extends BinaryTree[T] {

    override def isEmpty: Boolean = false

    override def isLeaf: Boolean = leftChild == TreeEnd && rightChild == TreeEnd

    override def collectLeaves: List[BinaryTree[T]] = {
      val queue = scala.collection.mutable.Queue[BinaryTree[T]](this)
      val leaves = scala.collection.mutable.ListBuffer[BinaryTree[T]]()
      while (queue.nonEmpty) {
        val current = queue.dequeue()
        if (current.isLeaf) leaves.append(current)
        if (!current.leftChild.isEmpty) queue.enqueue(current.leftChild)
        if (!current.rightChild.isEmpty) queue.enqueue(current.rightChild)
      }
      leaves.toList
    }

    override def countLeaves: Int = collectLeaves.size

    override def nodesAtLevel(level: Int): List[BinaryTree[T]] = {
      case class Node(level: Int, value: BinaryTree[T])
      val nodes = scala.collection.mutable.ListBuffer[BinaryTree[T]]()
      val queue = scala.collection.mutable.Queue[Node](Node(0, this))

      while (queue.nonEmpty) {
        val current = queue.dequeue()
        if (current.level == level) nodes.append(current.value)
        if (!current.value.leftChild.isEmpty) queue.enqueue(Node(current.level + 1, current.value.leftChild))
        if (!current.value.rightChild.isEmpty) queue.enqueue(Node(current.level + 1, current.value.rightChild))
      }
      nodes.toList
    }

    override def collectNodes(): List[T] = {
      val queue = scala.collection.mutable.Queue[BinaryTree[T]](this)
      val nodes = scala.collection.mutable.ListBuffer[T]()

      while (queue.nonEmpty) {
        val current = queue.dequeue()
        nodes.append(current.value)
        if (!current.leftChild.isEmpty) queue.enqueue(current.leftChild)
        if (!current.rightChild.isEmpty) queue.enqueue(current.rightChild)
      }
      nodes.toList
    }

    def hasPath(tree: BinaryTree[Int], target: Int): Boolean = {
      def rec (acc: Int, tr: BinaryTree[Int]): Boolean =
        tr match {
          case _ if tr.isEmpty => acc==target
          case _ if tr.isLeaf => (tr.value + acc) == target
          case _ => rec(tr.value+acc,tr.rightChild)||rec(tr.value+acc,tr.leftChild)
        }

      rec(0,tree)
    }

    def findAllPaths(tree: BinaryTree[String], target: String): List[List[String]] = {
      case class PathAndSum(path: List[String], sum: Int)

      def loop(acc: Int, path: List[String], allPaths: List[PathAndSum], node: BinaryTree[String]): List[PathAndSum] = {
        if (node == TreeEnd) return List.empty

        if (node.isLeaf) {
          PathAndSum(node.value :: path, node.value.toInt + acc) :: allPaths
        } else {
          val newAcc = node.value.toInt + acc
          val newPath = node.value :: path
          loop(newAcc, newPath, allPaths, node.leftChild) ++ loop(newAcc, newPath, allPaths, node.rightChild)
        }
      }
      loop(0, List.empty, List.empty, tree)
        .filter(_.sum == target.toInt)
        .map(_.path.reverse)
    }

  }
}
