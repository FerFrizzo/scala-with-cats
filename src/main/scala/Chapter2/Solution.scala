package Chapter2

import scala._
import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random.nextInt

object solution extends App {

  def solution(n: Int): Int = {
    val binary = n.toBinaryString
    val list: List[String] = binary.split("").toList

    var diff = 0

    if (list.count(_ == "0") == 0) 0
    else
      for (i <- 0 until list.count(_ == "1")) {
        for (j <- 1 until list.count(_ == "1")) {
          val posI = list.indexOf("1", i)
          if (i == list.count(_ == "1") - 1) {
            val posJ = list.lastIndexOf("1")
            if (posJ - posI > diff) {
              diff = posJ - posI
            }
          } else {
            val posJ = list.indexOf("1", j) - 1
            if (posJ - posI > diff)
              diff = posJ - posI
          }
        }
      }

    diff
  }

  def solutionB(a: Int): Int = {
    val binary = a.toBinaryString.toList
    var counter: Int = 0
    val listOfMaxCount: mutable.ArrayBuffer[Int] = ArrayBuffer(0)
    val listStarted: ArrayBuffer[Boolean] = ArrayBuffer(false)

    binary.foreach { i =>
      if (i == '1') {
        if (listStarted.last) {
          if (counter > listOfMaxCount.max) {
            listOfMaxCount += counter
          }
        }
        counter = 0
        listStarted += true
      }
      if (i == '0')
        counter = counter + 1

    }
    listOfMaxCount.max
  }

  @tailrec
  def solutionC(a: Array[Int], k: Int): Array[Int] = {
    if (a.length == 0 || a.length == 1) a
    else {
      val listAux = ArrayBuffer(a.last)
      for (i <- a.indices) {
        if (i < a.length - 1) {
          listAux += a(i)
        }
      }
      if (k > 0)
        solutionC(listAux.toArray, k - 1)
      else listAux.toArray
    }

  }

  println(s"should be  [9, 7, 6, 3, 8] " + solutionC(Array(3, 8, 9, 7, 6), 3).toList)
  println(s"should be  [0, 0, 0] " + solutionC(Array(0, 0, 0), 1).toList)
  println(s"should be  [1, 2, 3, 4] " + solutionC(Array(1, 2, 3, 4), 4).toList)

  //  println(s"should be 5 ==> ${solutionB(1041)}")
  //  println(s"should be 0 ==> ${solutionB(15)}")
  //  println(s"should be 0 ==> ${solutionB(32)}")
  //}
}