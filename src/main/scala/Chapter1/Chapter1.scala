package Chapter1

import JsonWriterInstances._
import JsonSyntax._
import OptionWriter._
import PrintableInstances._
import PrintableSyntax._
import cats.implicits.catsSyntaxEq
import cats.{Eq, Show}
import cats.syntax.show._
import cats.instances.string._
import cats.instances.option._
import cats.instances.int._
import cats.instances.long._
import cats.syntax.option._

import java.util.Date


object Chapter1 extends App {

  Json.toJson(Person("Fernando", "fernandofrizzo@gmail.com"))
  Person("Sabine", "sabinewoz2@yahoo.com").toJson
  Json.toJson(Option(Person("Mira", "mirafrizzo@gmail.com")))
  Option(Person("Mira2", "mirafrizzo2@gmail.com")).toJson

  Printable.print(Cat("princesa", 1, "gray"))
  Cat("princesa", 1, "gray").print

  val eqInt = Eq[Int]

  println(eqInt.eqv(123, 123))
  println(eqInt.eqv(123, 124))

  println(1.some == none[Int])
  println(1.some != none[Int])
  println(1.some === none[Int])
  println(1.some =!= none[Int])

  implicit val dateEq: Eq[Date] =
    Eq.instance[Date]{ (date1, date2) =>
      date1.getTime === date2.getTime
    }

  val x = new Date()
  val y = new Date()

  println(x === x)
  println(x === y)

  println("Fel -> " + Felinity.catEq)
}
