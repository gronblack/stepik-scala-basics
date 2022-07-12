package advanced.playground

import scala.collection.immutable.HashMap

object step12 {

  def duplicate[T](someList: List[T], nDups: Int): List[T] = {
    import scala.annotation.tailrec

    @tailrec
    def loop(counter: Int, acc: List[T], elem: T): List[T] = {
      if (counter == nDups) acc
      else loop(counter + 1, elem :: acc, elem)
    }
    someList.flatMap { elem =>
      loop(0, List.empty, elem)
    }
  }

  def countChars(someString: String): Map[Char, Int] = {
    someString.toLowerCase.foldLeft(Map[Char, Int]().withDefaultValue(0)) {
      (map, char) => map + (char -> (1 + map(char)))
    }
  }

  def isBalanced(str: String): Boolean = {
    str.count(_ == '(') == str.count(_ == ')')
  }

  val multiplyFunction = (x: Int, y: Int) => x * y
  def multiplyMethod(x: Int, y: Int) = x * y
  def curriedMultiplyMethod(x: Int)(y: Int): Int = x * y


  def formatter(format: String)(digit: Double): String = format.format(digit)
  val simpleFormat = formatter("%.2f") _

  def add(x: Int, y: => Int) = x + y
  def multiply(f: () => Int) = f() * 2
  def four: Int = 4
  def two(): Int = 2

//  add(1, 2)
//  add(1, (() => 2)())
//  add(1, () => 2 )
//  multiply(two _)
//  add(1, two _)
//  multiply(four)
//  multiply(() => 4)
//  add(1, four)
//  multiply(two)
//  add(two(), four)
//  multiply(12)
}
