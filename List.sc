val listInt: List[Int] = List(1,2,3,4,5)
val listString: List[String] = List("hello", "world", "bye")
val emptyList: List[_] = Nil

//Nil

//List.apply constructing

//List apply access

//.head

//.headOption

//.tail

//.size/.length

//.tail

//.contains

//.exists

//.drop

//.take

//.map

//.flatMap

//Exercises

//Write a function which takes a list of ints and adds 5 to each value

def add5ToEach(l: List[Int]): List[Int] = {
  ???
}

assert(add5ToEach(listInt) == List(6,7,8,9,10))

//Write a function which takes a list of strings and returns a list
//with the size of each of those strings

def sizeOfStrings(l: List[String]): List[Int] = {
  ???
}

assert(sizeOfStrings(listString) == List(5,5,3))

//Write a function which takes a list of ints and checks if any of
//those ints are divisible by 3.

def byThree(l: List[Int]): Boolean = {
  ???
}

assert(byThree(listInt) == true)
assert(byThree(List(1,2,4,5)) == false)

/* ======================= */

//Session 2 ???

//.filter

//.forAll

//.foldLeft

//.foldRight

//.fold

//Exercises

//Write a function which takes in a list of strings and removes
//any string that contains the letter 'o'

def removeStringWithO(l: List[String]): List[String] = {
  ???
}

assert(removeStringWithO(listString) == List("bye"))

//A Receipt contains a List of Items. Each Item consists of a price.
//The data model looks as follows:
case class Item(name: String, price: Double)
case class Receipt(items: List[Item])

//Write a function which given a receipt with a list of items,
//that will calculate the total cost by adding all the prices together
//If that list is empty, return 0
//Hint: Use foldLeft - Think

def total(receipt: Receipt): Double = {
  receipt.items.foldLeft(0: Double)((cumulative, item) => cumulative + item.price)
}

assert(total(Receipt(List(Item("pasta", 1), Item("Sauce", 0.5)))) == 1.5)
assert(total(Receipt(Nil)) == 0)
