import scala.collection._
import scala.concurrent.duration._
import scala.math.BigDecimal.int2bigDecimal

import akka.actor._

sealed trait AccountType
case object Checking extends AccountType
case object Savings extends AccountType
case object MoneyMarket extends AccountType

case class GetCustomerAccountBalances(id: Long, accountTypes: Set[AccountType])
case class GetAccountBalances(id: Long)

case class AccountBalances(accountType: AccountType, 
						   balance: Option[List[(Long, BigDecimal)]])
						   
case class CheckingAccountBalances(balances: Option[List[(Long, BigDecimal)]])
case class SavingAccountBalances(balances: Option[List[(Long, BigDecimal)]])
case class MoneyMarketAccountBalances(balances: Option[List[(Long, BigDecimal)]])

case object TimedOut
case object CantUnderstand

class SavingsAccountPRoxy extends Actor {
	def receive = {
		case GetAccountBalances(id: Long) =>
			sender ! SavingsAccountBalances(Some(List((1, 150000), List(2, 29000))))
	}
}
class CheckingAccountProxy extends Actor {
	def receive = {
		case GetAccountBalances(id: Long) =>
			sender ! CheckingAccountBalances(Some(List((3, 15000)))
	}
}

class MoneyMarketAccountProxy extends Actor {
	def receive = {
		case GetAccountBalances(id: Long) =>
			sender ! MoneyMarketAccountBalances(None)
	}
}

class AccountBalanceRetriever(savingsAccounts: ActorRef, checkingAccounts: ActorRef, moneyMarketAccounts: ActorRef) extends Actor {
  implicit val timeout: Timeout = 100 milliseconds
  implicit val ec: ExecutionContext = context.dispatcher
  def receive = {
	case GetCustomerAccountBalances(id) => 
		val futSavings = savingsAccounts ? GetCustomerAccountBalances(id)
		val futChecking = checkingAccounts ? GetCustomerAccountBalances(id)
		val futMM = moneyMarketAccounts ? GetCustomerAccountBalances(id)
		val futBalances = for {
			savings <- futSavings.mapTo[Option[List[(Long, BigDecimal)]]]
			checking <- futChecking.mapTo[Option[List[(Long, BigDecimal)]]]
			mm <- futMM.mapTo[Option[List[(Long, BigDecimal)]]]
		} yield AccountBalances(savings, checking, mm)
		futBalances map (sender ! _)
	}
}