package playground

import scala.annotation.tailrec

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
      case class node(level: Int, value: BinaryTree[T])
      val nodes = scala.collection.mutable.ListBuffer[BinaryTree[T]]()
      val queue = scala.collection.mutable.Queue[node](node(0, this))

      while (queue.nonEmpty) {
        val current = queue.dequeue()
        if (current.level == level) nodes.append(current.value)
        if (!current.value.leftChild.isEmpty) queue.enqueue(node(current.level + 1, current.value.leftChild))
        if (!current.value.rightChild.isEmpty) queue.enqueue(node(current.level + 1, current.value.rightChild))
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

    override def hasPath(tree: BinaryTree[Int], target: Int): Boolean = {
      case class NodeWithParent[A](node: BinaryTree[A], parent: NodeWithParent[A])

      if (tree == TreeEnd) return false

      val leaves = {
        val queue = scala.collection.mutable.Queue[NodeWithParent[Int]](NodeWithParent(tree, null))
        val leaves = scala.collection.mutable.Queue[NodeWithParent[Int]]()
        while (queue.nonEmpty) {
          val current = queue.dequeue()
          if (current.node.isLeaf) leaves.enqueue(current)
          if (!current.node.leftChild.isEmpty) queue.enqueue(NodeWithParent(current.node.leftChild, current))
          if (!current.node.rightChild.isEmpty) queue.enqueue(NodeWithParent(current.node.rightChild, current))
        }
        leaves
      }

      def checkLeave(leave: NodeWithParent[Int]): Boolean = {
        var current = leave
        var currentSum = 0
        while (true) {
          if (current == null) return false
          currentSum += current.node.value
          if (currentSum == target) return true
          current = current.parent
        }
        false
      }

      while (leaves.nonEmpty) {
        if (checkLeave(leaves.dequeue())) return true
      }
      false
    }

    def findAllPaths(tree: BinaryTree[String], target: String): List[List[String]] = {
      case class NodeWithParent[A](node: BinaryTree[A], parent: NodeWithParent[A])

      if (tree == TreeEnd) return List.empty

      val leaves = {
        val queue = scala.collection.mutable.Queue[NodeWithParent[String]](NodeWithParent(tree, null))
        val leaves = scala.collection.mutable.Queue[NodeWithParent[String]]()
        while (queue.nonEmpty) {
          val current = queue.dequeue()
          if (current.node.isLeaf) leaves.enqueue(current)
          if (!current.node.leftChild.isEmpty) queue.enqueue(NodeWithParent(current.node.leftChild, current))
          if (!current.node.rightChild.isEmpty) queue.enqueue(NodeWithParent(current.node.rightChild, current))
        }
        leaves.toList
      }

      def checkLeavePath(leave: NodeWithParent[String]): List[String] = {
        var current = leave
        var currentSum = 0
        val path = scala.collection.mutable.ListBuffer.empty[String]
        while (true) {
          if (current == null) return List.empty

          currentSum += current.node.value.toInt
          path.prepend(current.node.value)

          if (currentSum == target.toInt && current.parent == null) return path.toList
          current = current.parent
        }
        List.empty
      }

      leaves.map(checkLeavePath).filter(_.nonEmpty)
    }

    /*
    override def hasPath(tree: BinaryTree[Int], target: Int): Boolean = {
      val allNodesLength = {
        val queue = scala.collection.mutable.Queue[BinaryTree[Int]](tree)
        val nodes = scala.collection.mutable.ListBuffer[Int]()
        while (queue.nonEmpty) {
          val current = queue.dequeue()
          nodes.append(current.value)
          if (!current.leftChild.isEmpty) queue.enqueue(current.leftChild)
          if (!current.rightChild.isEmpty) queue.enqueue(current.rightChild)
        }
        nodes.toList
      }.length

      def loop[A <: BinaryTree[Int]](current: A, sum: Int, visitedNodesCount: Int): Boolean = {
        if (current == TreeEnd) return false
        val currentSum = current.value + sum
        if (current.isLeaf && currentSum == target) return true
        if (visitedNodesCount + 1 == allNodesLength) return false

        val left = if (current.leftChild.isEmpty) false
        else loop(current.leftChild, currentSum, visitedNodesCount + 1)

        val right = if (current.rightChild.isEmpty) false
        else loop(current.rightChild, currentSum, visitedNodesCount + 1)
        left || right
      }
      loop(tree, 0, 0)
    }
    */

  }
}
