package playground

import scala.annotation.tailrec

object step58 {
  def compare(v1: String, v2: String): Int = {
    val a1 = v1.split("\\.").map(Integer.parseInt).toList
    val a2 = v2.split("\\.").map(Integer.parseInt).toList

    @tailrec
    def recursion(array1: List[Int], array2: List[Int]): Int = {
      val h1 = array1.headOption
      val h2 = array2.headOption
      val compareResult = compareHelper(h1, h2)
      if (array1.tail.isEmpty && array2.tail.isEmpty) {
        compareResult
      } else {
        if (compareResult != 0) compareResult
        else {
          val tail1 = if (array1.tail.isEmpty) List(0) else array1.tail
          val tail2 = if (array2.tail.isEmpty) List(0) else array2.tail
          recursion(tail1, tail2)
        }
      }
    }

    def compareHelper(one: Option[Int], two: Option[Int]) = {
      one.getOrElse(0).compare(two.getOrElse(0))
    }

    recursion(a1, a2)
  }
}
