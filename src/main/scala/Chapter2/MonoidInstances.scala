package Chapter2

import cats._
import cats.implicits._

object MonoidInstances extends App {

  println("===>" + Monoid[String].empty + "nada")
  println(Monoid[String].combine("Hi", "there"))
  println(Semigroup[String].combine("Hi", "there"))
  println(Monoid[Int].combine(30, 12))

  val a = Option(22)
  val b = Some(20)

  println(Monoid[Option[Int]].combine(a, b))

}
