package Chapter1

import cats.Show
import cats.instances.AllInstances
import cats.syntax.show._

import java.util.Date

object MeetCats extends App with AllInstances {

  val showInt = Show.apply[Int]
  val showString = Show.apply[String]

  val intAsString = showInt.show(123)
  val stringAsString = showString.show("abc")

  val showInt2 = 123.show
  val showString2 = "abc".show

  implicit val dateShow: Show[Date] = new Show[Date] {
    def show(date: Date): String =
      s"${date.getTime}ms since the epoch"
  }

  println(new Date().show)
}
