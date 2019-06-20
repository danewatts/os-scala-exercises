// pattern matching

//use like if else if else if else if else

//unapply

val someOption = Some("contents")

someOption match {
  case Some(x) => x
  case _ => "the option was empty"
}

case class Person(name: String, age: Option[Int])

Person.apply("pan", Some(23))
Person("pan", Some(23))

def getDetails(p: Person) ={
  p match {
    case Person(name, Some(age)) => s"hello $name, you are $age years old"
    case Person(name, None)      => s"hello $name, how old are you?"
  }
}

// ^^lists


// guards on pattern match

val someInt = 15

someInt match {
  case a if a==14 => "number is 14"
  case a if a > 10 => "greater than 10"
  case _ => "guarded matches do not have exhaustively checking "
}


// type matching


sealed trait Device
case class Phone(model: String) extends Device{
  def screenOff = "Turning screen off"
}
case class Computer(model: String) extends Device {
  def screenSaverOn = "Turning screen saver on..."
}

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



//   ______                   _
//  |  ____|                 (_)
//  | |__  __  _____ _ __ ___ _ ___  ___  ___
//  |  __| \ \/ / _ \ '__/ __| / __|/ _ \/ __|
//  | |____ >  <  __/ | | (__| \__ \  __/\__ \
//  |______/_/\_\___|_|  \___|_|___/\___||___/


// Excericse if else if else
// refactor this messy if else to use pattern matching

def dayOfTheWeek(day: String): Int = {
  if (day.toLowerCase == "monday") {
    1
  }else if (day.toLowerCase == "tuesday") {
    2
  }else if (day.toLowerCase == "wednesday") {
    3
  }else if (day.toLowerCase == "thursday") {
    4
  }else if (day.toLowerCase == "friday") {
    5
  }else if (day.toLowerCase == "saturday") {
    6
  }else if (day.toLowerCase == "sunday") {
    7
  }else {
    0
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
    case a if a%2==0 => "factor of 2"
    case a if a%3==0 => "factor of 3"
    case a if a%5==0 => "factor of 5"
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

def whatsFurnitureFor(piece: Furniture): String = ???


//assert(whatsFurnitureFor(Table()) == "Dinner goes on the table")
//assert(whatsFurnitureFor(Chair()) == "You sit on the chair")


// Exercise unapply
// complete the getNextToDo() method so that
// when parsed a list of Todos it returns the first Todos notes
// when parsed an empty list returns 'you have nothing todo today'

case class ToDo(notes: String)

def getNextTodo(toToList: List[ToDo]): String ={
  toToList match {
    case h :: _ => h.notes
    case _ => "you have nothing todo today"
  }
}

assert(getNextTodo(Nil) == "you have nothing todo today")
assert(getNextTodo(List(ToDo("go shopping"))) == "go shopping")
assert(getNextTodo(List(ToDo("go shopping"), ToDo("go to the gym"))) == "go shopping")