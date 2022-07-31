val listInt: List[Int] = List(1,2,3,4,5)
val listString: List[String] = List("hello", "world", "bye")
val emptyList: List[_] = Nil

//Nil is an empty list

//List.apply You can construct a List as follows
List.apply(1,2,3,4,5)
// The word apply can be removed for shorthand
List(1,2,3,4,5)

//List apply access. If you have a list, you can run apply to extract a value
//from its position. Lists are 0 base.
listInt.apply(1)
// The word apply can be removed for shorthand
listInt(1)

//.head will get the first value. It can throw an exception if the list is empty
listInt.head
//emptyList.head //Uncomment for exception

//.headOption will get the first element in a safe way, returning an option
listInt.headOption //returns Some(value)
emptyList.headOption //returns None instead of an exception

//.tail returns a List of every element in the list except the head (First value)
listInt.tail

//.size/.length
listInt.size
listString.length

//.contains checks if the list contains a specific element
listInt.contains(5)
listInt.contains(8)

// .exists takes a predicate and checks if any element in the list satisfies
// the predicate
//.exists
listInt.exists(i => i < 4)
listInt.exists(i => i > 12)

//.drop will drop X number of elements from a list
listInt.drop(3)

//.take will take the first X number of elements from a list
listInt.take(2)

// .map will execute a function on EVERY element in the list.
// This can change the List's inner type
//.map
listInt.map(i => i.toString)
listInt.map(i => i + 100)

// .flatMap is used when your map function returns List[List[_]]
// to flatten the structure by 1 to turn it to just List[_]
//.flatMap
listInt.map(i => List(i))
listInt.flatMap(i => List(i))

//Exercises

//Write a function which takes a list of ints and adds 5 to each value

def add5ToEach(l: List[Int]): List[Int] = {
  l.map(value => value + 5)
}

assert(add5ToEach(listInt) == List(6,7,8,9,10))

//Write a function which takes a list of strings and returns a list
//with the size of each of those strings

def sizeOfStrings(l: List[String]): List[Int] = {
  l.map(value => value.length)
}

assert(sizeOfStrings(listString) == List(5,5,3))

//Write a function which takes a list of ints and checks if any of
//those ints are divisible by 3.

def byThree(l: List[Int]): Boolean = {
  l.exists(value => value % 3 == 0)
}

assert(byThree(listInt) == true)
assert(byThree(List(1,2,4,5)) == false)

/* ======================= */

//Session 2 ???

// filter takes a predicate and keeps the values which satisfies the predicate
//.filter
listInt.filter(i => i % 2 == 0)

// forAll takes a predicate and executes it against every value in the list
// If they are all true, it returns true, if one or more are false, it returns false
//.forAll
listInt.forall(i => i < 4)
listInt.forall(i => i < 8)

// fold will recursively go through a list and execute a function on each element,
// returning an accumulator that can be used within the function. After consuming
// every element, the fold will return the final accumulated value
// list.foldLeft(startingAccumulator)((accumulator, value) => accumulator + value)
// This will add all the values in a list.
//.foldLeft
listInt.foldLeft(0)((acc, value) => acc + value)
listString.foldLeft("")((acc, value) => acc + value)

// foldRight does the same as foldLeft but works right to left
//.foldRight
listInt.foldRight(0)((value, acc) => acc + value)
listString.foldRight("")((value, acc) => acc + value)

//Exercises

//Write a function which takes in a list of strings and removes
//any string that contains the letter 'o'

def removeStringWithO(l: List[String]): List[String] = {
  l.filter(value => !value.contains("o"))
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
