// pattern matching

//use like if else if else if else if else
// Pattern matching is started by taking your value and then writing `match`
// You then use "case statements" to compare the values.
// The case statements match will go through each case statement till it finds
// a case that matches and stops at the first one.
val string = "String"
string match {
  case "hello" => println("first if was true")
  case "goodbye" => println("second if was true")
  case _ => println("Catch all")
}
// The _ case statement is a catch all. Just like the else of an if else statement.
// You can give the _ a variable name to use it in the right side of your case statement
// This will still catch all.
string match {
  case "hello" => println("first if was true")
  case "goodbye" => println("second if was true")
  case value => println("Catch all: " + value)
}

//unapply

// Patternmatching can be used to deconstruct some types
// Here we are looking at an option and extracting a value from inside of it
// This is done using the unapply function

val someOption = Some("contents")

someOption match {
  case Some(x) => x
  case _ => "the option was empty"
}

// This is an example of a data model - Do not focus on the implementation of this.
case class Person(name: String, age: Option[Int])

// When using the apply function, scala allows shorthand where you can remove
// the word apply but still execute it. This is syntactical sugar.
Person.apply("pan", Some(23))
Person("pan", Some(23))

def getDetails(p: Person) ={
  p match {
    case Person(name, Some(age)) => s"hello $name, you are $age years old"
    case Person(name, None)      => s"hello $name, how old are you?"
  }
}


// guards on pattern match

// Guards can make pattern matching very powerful. It will match a value where a given
// predicate is satisfied.

val someInt = 15

// case a would match anything, but in this case it matches anything where a is equal to 14.
someInt match {
  case a if a==14 => "number is 14"
  case a if a > 10 => "greater than 10"
  case _ => "guarded matches do not have exhaustively checking "
}


// type matching

// Base type
sealed trait Device
// Concrete type that implements Device. You can say a Phone IS a Device
case class Phone(model: String) extends Device{
  def screenOff = "Turning screen off"
}
// Concrete type that implements Device. You can say a Computer IS a Device
case class Computer(model: String) extends Device {
  def screenSaverOn = "Turning screen saver on..."
}

// You can pattern match based off of a type. In this case our function gets a device,
// We then match based off of the different types of device. This is done in the
// case statement with `case variable: Type =>`
def goIdle(device: Device) = device match {
  case p: Phone => p.screenOff
  case c: Computer => c.screenSaverOn
}

val phone = Phone("nokia")

goIdle(phone)

//What is the difference between method A of matching and method B of matching?

def tuple = {
  ("some string", 5)
}

//method A
tuple match {
  case (_, 5) => tuple
}

//method B
tuple match {
  case x@(_, 5) => x
}


//^ tuple is a function. In "method A" the function tuple is called twice.
// Once on the right side and once before the match. If this function has a side affect
// (Like a http call) that side affect would happen twice.
// Method B gives the name x to the Tuple as well as deconstructing the tuple to
// match based on the values inside. x is then referred to on the right side meaning
// the function isn't called twice.



//   ______                   _
//  |  ____|                 (_)
//  | |__  __  _____ _ __ ___ _ ___  ___  ___
//  |  __| \ \/ / _ \ '__/ __| / __|/ _ \/ __|
//  | |____ >  <  __/ | | (__| \__ \  __/\__ \
//  |______/_/\_\___|_|  \___|_|___/\___||___/


// Exercises if else if else
// refactor this messy if else to use pattern matching

def dayOfTheWeek(day: String): Int = {
//  if (day.toLowerCase == "monday") {
//    1
//  }else if (day.toLowerCase == "tuesday") {
//    2
//  }else if (day.toLowerCase == "wednesday") {
//    3
//  }else if (day.toLowerCase == "thursday") {
//    4
//  }else if (day.toLowerCase == "friday") {
//    5
//  }else if (day.toLowerCase == "saturday") {
//    6
//  }else if (day.toLowerCase == "sunday") {
//    7
//  }else {
//    0
//  }
  day.toLowerCase match {
    case "monday" => 1
    case "tuesday" => 2
    case "wednesday" => 3
    case "thursday" => 4
    case "friday" => 5
    case "saturday" => 6
    case "sunday" => 7
    case _ => 0
  }
}

List("not a day", "Monday", "tuesday", "Wednesday", "thursday", "FRIDAY", "saturday", "sunday")
  .zipWithIndex
  .foreach(d => assert(dayOfTheWeek(d._1)== d._2))

// Exercise guards
// produce a method that when parsed an int returns 'factor of N' if it is a
// multiple of 2,3 or 5 and if not says '2,3,5 not a factor'

def lowestFactor(n: Int) ={
  n match {
    case a if a % 2 == 0 => "factor of 2"
    case a if a % 3 == 0 => "factor of 3"
    case a if a % 5 == 0 => "factor of 5"
    case _ => "2,3,5 not a factor"
  }
}


assert(lowestFactor(1) == "2,3,5 not a factor")
assert(lowestFactor(7) == "2,3,5 not a factor")
assert(lowestFactor(4) == "factor of 2")
assert(lowestFactor(9) == "factor of 3")
assert(lowestFactor(15) == "factor of 3")

// Exercise types
// complete whatsFurnitureFor() so that when it is parsed a Table, 'Dinner goes on the table' is returned
// and when it is parsed a Chair 'You sit on the chair' is returned
sealed trait Furniture
case class Table() extends Furniture
case class Chair() extends Furniture

def whatsFurnitureFor(piece: Furniture): String = {
  piece match {
    case t: Table => "Dinner goes on the table"
    case c: Chair => "You sit on the chair"
  }
}


assert(whatsFurnitureFor(Table()) == "Dinner goes on the table")
assert(whatsFurnitureFor(Chair()) == "You sit on the chair")


// Exercise unapply
// complete the getNextToDo() method so that
// when parsed a list of Todos it returns the first Todos notes
// when parsed an empty list returns 'you have nothing todo today'

case class ToDo(notes: String)

def getNextTodo(toToList: List[ToDo]): String ={
  toToList match {
    case a if a.nonEmpty => a.head.notes
    case _ => "you have nothing todo today"
  }
}

assert(getNextTodo(Nil) == "you have nothing todo today")
assert(getNextTodo(List(ToDo("go shopping"))) == "go shopping")
assert(getNextTodo(List(ToDo("go shopping"), ToDo("go to the gym"))) == "go shopping")