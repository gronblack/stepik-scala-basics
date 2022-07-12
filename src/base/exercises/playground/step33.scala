package base.exercises.playground

object step33 {
  trait Configs {
    val ACCOUNT_TYPE_DEFAULT = "free"
    val ACCOUNT_TYPE_PAID = "paid"
    val SUBSCRIPTION_STATUS = "active"
  }

  object Settings {
    case class AccountSettings(
        email: String,
        password: String,
        picture: String)

    case class SubscriptionSettings(
        payment: String,
        notifications: String,
        expiration: String)
  }

  trait Subscriber {
    def subscribe(settings: Settings.SubscriptionSettings): Unit = println("subscribed")
  }

  trait Unsubscriber {
    def unsubscribe(): Unit = println("unsubscribed")
  }

  abstract class Account(accountID: String, settings: Settings.AccountSettings) extends Configs {
    def info(): Unit
  }

  class FreeAccount(
      accountID: String,
      settings: Settings.AccountSettings) extends Account(accountID, settings) with Subscriber {

    override def info(): Unit = println(s"Account Type: $ACCOUNT_TYPE_DEFAULT")
  }

  class PaidAccount(
    accountID: String,
    settings: Settings.AccountSettings) extends Account(accountID, settings) with Unsubscriber {

    override def info(): Unit = {
      println(s"Account Type: $ACCOUNT_TYPE_PAID")
      println(s"Subscription Status: $SUBSCRIPTION_STATUS")
    }
  }

  def countNumbers(s: String): Map[Char, Int] = {
    s.filter(_ != '-').groupBy(identity).collect {
      case (c: Char, s: String) => c -> s.length
    }
  }
}
