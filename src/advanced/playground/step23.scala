package advanced.playground

object step23 {

  trait DiscountMagnet {
    def apply(): String
  }

  implicit class DiscountCoupon(x: String) extends DiscountMagnet {
    override def apply(): String = {
      if (x.forall(_.isDigit)) applyDiscount(x.toInt) else "coupon applied"
    }
  }

  implicit class DiscountPercentage(x: Int) extends DiscountMagnet {
    override def apply(): String = s"$x % discount"
  }

  def applyDiscount(magnet: DiscountMagnet): String = magnet()

  val aDiscount = "fdggf"

  abstract class AbstractJsonObject {
    protected val Quotes = '"'
    def stringify: String
  }

  case class JsonObject(values: Map[String, AbstractJsonObject]) extends AbstractJsonObject {
    override def stringify: String = {
      values.map(pair => s"$Quotes${pair._1}$Quotes:${pair._2.stringify}").mkString("{", ",", "}")
    }
  }

  case class JsonString(value: String) extends AbstractJsonObject {
    override def stringify: String = s"$Quotes$value$Quotes"
  }

  case class JsonInteger(value: Int) extends AbstractJsonObject {
    override def stringify: String = value.toString
  }

  case class JsonArray(values: List[AbstractJsonObject]) extends AbstractJsonObject {
    override def stringify: String = {
      values.map(_.stringify).mkString("[", ",", "]")
    }
  }
}
