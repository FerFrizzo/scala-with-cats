val lst = List("um", "dois", "tres")
lst.mkString(", ")
val lst2 = List("um", "dois")
val lst1 = List("um")
val listaVazia = List.empty[String]
def criaFrase(lst: List[String]): String = {
  if (lst.isEmpty) ""
  else if (lst.size == 1) lst.head
  else {
    val inicio = lst.slice(0, lst.size - 1)
    val fim = lst.slice(lst.size - 1, lst.size)
    s"${inicio.mkString(", ")} e ${fim.head}"

  }
}
println(criaFrase(lst))
println(criaFrase(lst2))
println(criaFrase(lst1))
println(criaFrase(listaVazia))


val mapTest = Map("a" -> "b")
println(mapTest("b"))