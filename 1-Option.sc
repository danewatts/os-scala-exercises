// Option is a data type for a value which may or may not be present
// We can use it to get around null values in java
// An Option value can either be `None` meaning no value is there
// Or it can be Some(value) which means the value is present.

val noneStringValue: Option[String] = None
val someStringValue: Option[String] = Some("Hey")
val noneIntValue: Option[Int] = None
val someIntValue: Option[Int] = Some(1)

// .get Will return the value inside of the option if it is there
// If it is not it there it will throw an exception
// Be careful using .get
//.get
//noneStringValue.get //Uncomment for exception
someStringValue.get

//.getOrElse pulls out the value or gives a default if there option is None
someStringValue.getOrElse("default")
noneStringValue.getOrElse("default")

//.isDefined returns a boolean on whether the value is there or not
someStringValue.isDefined
noneStringValue.isDefined

//.isEmpty !.isDefined
someStringValue.isEmpty
noneStringValue.isEmpty

// .exists will unwrap your value and execute a predicate on the value.
// It returns true if your value satisfies the predicate. If it does not,
// OR the value is None, then it returns false
//.exists
someIntValue.exists(value => value < 5)
noneIntValue.exists(value => value < 5)

// .contains Checks whether an option contains a specific value. Returns
// false if it doesnt and false for None.
//.contains
someStringValue.contains("Hey")
someStringValue.contains("Hgydauhijoiey")

// .filter takes in a predicate. If your option is Some(value) and the value
// satisfies the predicate, you retain your Some(value). If it does not satisfy
// the predicate it will change your value to None. If the value is None it will
//remain None
//.filter
someIntValue.filter(value => value < 7)
someIntValue.filter(value => value > 7)
noneIntValue.filter(value => value < 7)

// "Mapping" something is essentially unwrapping the value and executing
// a function on the value then wrapping it back up. The return type is still
// an option. A None will remain a None. A .map can change the inner type of the
// option
//.map
someIntValue.map(value => value.toString)
someIntValue.map(value => value + 10)
noneIntValue.map(value => value + 10)

// flatten is used to conver an Option[Option[Type]] to just an Option[Type]
// If any of the Options are None, the whole thing becomes None
//.flatten
Some(Some(7)).flatten
Some(None).flatten
(None: Option[Option[String]]).flatten

// flatMap is used in place of .map(_).flatten
// If you map an option, and that returns an Option[Option[type]]
// You can use .flatMap instead
//.flatMap
val x: Option[Option[Int]] = someIntValue.map(value => Some(value))
x.flatten
someIntValue.flatMap(value => Some(value))

// Fold takes two parameters. It is essentially .map(_).getOrElse(default)
// The first parameter is the default that will be returned if
// the option is None. The second parameter is a function that will be ran
// if the value is Some(value)
//.fold
someIntValue.fold(0)(value => value + 10)
noneIntValue.fold(0)(value => value + 10)

//Exercise 1
//Write a function which adds 1 to an option if the value is Some.
//If the value is None, it should return 0
def add1OrElse0(opt: Option[Int]): Int = {
  opt.map(value => value + 1).getOrElse(0)
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
  opt.map {
    case i: Int => "Its an int"
    case "Hello" => "Hey"
    case "Goodbye" => "Bye bye"
    case _ => ""
  }.getOrElse("Nothing there")
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
  person.flatMap(value => value.middleName)
}

//Tests dont worry about format.
assert(getMiddleName(Some(Person(Some("Michael")))) == Some("Michael"))
assert(getMiddleName(Some(Person(Some("Preston")))) == Some("Preston"))
assert(getMiddleName(None) == None)
assert(getMiddleName(Some(Person(None))) == None)
