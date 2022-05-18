package Chapter2

import cats._
import cats.implicits._

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Monoid {
  def apply[A](implicit monoid: Monoid[A]): Monoid[A] =
    monoid

  implicit val booleanAndMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def empty: Boolean = true

    def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  implicit val booleanOrMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  implicit val booleanExclusiveMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = (x && !y) || (!x && y)
  }

  implicit val booleanXnorMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean =
      (!x || y) && (x || !y)
  }

  implicit def setUnionMonoid[A](): Monoid[Set[A]] =
    new Monoid[Set[A]] {
      override def empty: Set[A] = Set.empty[A]

      override def combine(x: Set[A], y: Set[A]): Set[A] = x union y
    }

  implicit def setIntersectionSemigroup[A](): Semigroup[Set[A]] =
    new Semigroup[Set[A]] {
      override def combine(x: Set[A], y: Set[A]): Set[A] = x intersect y
    }

  implicit def symDiffMonoid[A]: Monoid[Set[A]] =
    new Monoid[Set[A]] {
      override def empty: Set[A] = Set.empty[A]

      override def combine(x: Set[A], y: Set[A]): Set[A] =
        (x diff y) union (y diff x)
    }

  def associativeLaw[A](x: A, y: A, z: A)
                       (implicit m: Monoid[A]): Boolean =
    m.combine(x, m.combine(y, z)) ==
      m.combine(m.combine(x, y), z)

  def identityLaw[A](x: A)
                    (implicit m: Monoid[A]): Boolean =
    (m.combine(x, m.empty) == x) &&
      (m.combine(m.empty, x) == x)


//  val intSetMonoid = Monoid[Set[Int]]
//  val strSetMonoid = Monoid[Set[String]]
//
//  intSetMonoid.combine(Set(1, 2), Set(2, 3))
//  strSetMonoid.combine(Set("A", "B"), Set("B", "C"))
}

object Chapter2 extends App {

  val intSetMonoid = Monoid[Set[Int]]
  val strSetMonoid = Monoid[Set[String]]

  println("Int => " + intSetMonoid.combine(Set(1, 2), Set(2, 3)))
  println("Str => " + strSetMonoid.combine(Set("A", "B"), Set("B", "C")))

}
