val noneStringValue: Option[String] = None
val someStringValue: Option[String] = Some("Hey")
val nonIntValue: Option[Int] = None
val someIntValue: Option[Int] = Some(1)

//.get

//.getOrElse

//.isDefined

//.isEmpty

//.exists

//.contains

//.filter

//.map

//.flatten

//.flatMap

//.fold

//Pattern matching

//Exercise 1
//Write a function which adds 1 to an option if the value is Some.
//If the value is None, it should return 0
def add1OrElse0(opt: Option[Int]): Int = {
  ???
}

//Tests for method above
assert(add1OrElse0(Some(1)) == 2)
assert(add1OrElse0(Some(2)) == 3)
assert(add1OrElse0(None) == 0)

//Exercise 2
//Write a method that takes in an option.
// If that option is an Option[Int] return a string of "Its an int"
// If that option is an Option[String] that is equal to Some("Hello") then return "Hey"
// If that option is an Option[String] that is equal to Some("Goodbye") then return "Bye bye"
// If that option is None then return "Nothing there"
// Anything else should return an empty string
def optionToString(opt: Option[Any]): String = {
  ???
}

//Tests
assert(optionToString(Some(1)) == "Its an int")
assert(optionToString(Some(2)) == "Its an int")
assert(optionToString(Some("Hello")) == "Hey")
assert(optionToString(Some("Goodbye")) == "Bye bye")
assert(optionToString(None) == "Nothing there")
assert(optionToString(Some(true)) == "")
assert(optionToString(Some(Nil)) == "")


//Exercise 3
//A Person may or may not have a middle name. The data model is represented as follows.
case class Person(middleName: Option[String])

//Write a function which takes an optional person, if that person exists,
//return their middle name as an optional value (Note the middle name may not exist)
def getMiddleName(person: Option[Person]): Option[String] = {
  ???
}

//Tests dont worry about format.
assert(getMiddleName(Some(Person(Some("Michael")))) == Some("Michael"))
assert(getMiddleName(Some(Person(Some("Preston")))) == Some("Preston"))
assert(getMiddleName(None) == None)
assert(getMiddleName(Some(Person(None))) == None)
