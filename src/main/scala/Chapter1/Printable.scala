package Chapter1

//import cats.implicits.catsSyntaxEq
import cats.{Eq, Show}
import cats.syntax.show._
import cats.instances.int._
import cats.instances.string._
import cats.instances.option._
import cats.syntax.option._
import cats.syntax.eq._


final case class Cat(name: String, age: Int, color: String)

trait Printable[A] {
  def format(value: A): String
}

object PrintableInstances {
  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String = value
    }

  implicit val intPrintable: Printable[Int] =
    new Printable[Int] {
      def format(value: Int): String = value.toString
    }

  implicit val catPrintable: Printable[Cat] =
    new Printable[Cat] {
      def format(value: Cat): String = s"${value.name} is a ${value.age} year-old ${value.color} cat."
    }
}

object Printable {
  def format[A](value: A)(implicit p: Printable[A]): String =
    p.format(value)

  def print[A](value: A)(implicit p: Printable[A]): Unit =
    println(format(value))
}

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit p: Printable[A]): String =
      p.format(value)

    def print(implicit p: Printable[A]): Unit =
      println(format(p))

  }
}

object CatShow {
  implicit val catShow: Show[Cat] = Show.show[Cat] { cat =>
    s"${cat.name.show} is a ${cat.age.show} year old ${cat.color.show} cat."
  }

  println(Cat("Garfield", 38, "ginger and black").show)
}

object Felinity extends App {

  val cat1 = Cat("Garfield", 38, "orange and black")
  val cat2 = Cat("Garfield", 38, "orange and black")
  val cat3 = Cat("Heathcliff", 33, "orange and black")

  val optionCat1 = Option(cat1)
  val optionCat2 = Option(cat2)
  val optionCat3 = Option(cat3)


  implicit val catEq: Eq[Cat] =
    Eq.instance[Cat] { (cat1, cat2) =>
      cat1.name === cat2.name && cat1.age === cat2.age && cat1.color === cat2.color
    }

  val x = "08001510900"

  println(cat1 === cat2)
  println(cat1 === cat3)
  println(optionCat1 === optionCat2)
  println(optionCat1 === optionCat3)
  println(optionCat1 =!= optionCat3)



}