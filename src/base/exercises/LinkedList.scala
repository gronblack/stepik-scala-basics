package base.exercises

abstract class LogList {
  def all: String

  def last: String

  def previous: LogList

  def isEmpty: Boolean

  def add(msg: String): LogList
}

class Log(head: String, tail: LogList) extends LogList {
  override def all: String = ???

  override def last: String = ???

  override def previous: LogList = ???

  override def isEmpty: Boolean = ???

  override def add(msg: String): LogList = new Log(msg, this)
}

object Empty extends LogList {
  override def all: String = ???

  override def last: String = ???

  override def previous: LogList = ???

  override def isEmpty: Boolean = ???

  override def add(msg: String): LogList = new Log(msg, Empty)
}


object main extends App {
  val list = new Log("INFO_1", new Log("INFO_2", new Log("INFO_3", Empty)))
  println(list.previous.last)
}
