package playground

object step71 {
  abstract class BinaryTree[+T] {
    def value: T
    def leftChild: BinaryTree[T]
    def rightChild: BinaryTree[T]

    def isEmpty:  Boolean

    def isLeaf: Boolean
    def collectLeaves: List[BinaryTree[T]]
  }

  case object TreeEnd extends BinaryTree[Nothing] {
    override def value: Nothing = throw new NoSuchElementException
    override def leftChild: BinaryTree[Nothing] = throw new NoSuchElementException
    override def rightChild: BinaryTree[Nothing] = throw  new NoSuchElementException

    override def isEmpty: Boolean = true

    override def isLeaf: Boolean = false
    override def collectLeaves: List[BinaryTree[Nothing]] = List.empty
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

  }

}
