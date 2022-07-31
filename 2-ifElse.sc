
// if
if(true) {
  println("It was true")
}

if(5 < 3) {
  println("How was it true???")
}
// else
if("hello" != "goodbye"){
  println("It was true")
} else {
  "How was it false???"
}

// combine them to make
// else if
val string = "String"
if(string == "hello") {
  println("first if was true")
} else if(string == "goodbye") {
  println("second if was true")
} else {
  println("Catch all")
}

// the last line of a 'if' or 'else' is the return statement as to why we dont need
// to write 'return' like in java. it is also why we can chain if on to else.

//Exercise 1
//write a method that when I pass a 24hr hour returns 'good morning'
// and 'good evening' as you would expect

def greeter(hour: Int): String = {
  if (hour < 12) {
    "good morning"
  } else {
    "good evening"
  }
}

////Exercise 1 assertions
List.range(0,12).foreach{hour=>
  assert(greeter(hour) == "good morning")
}
List.range(12,25).foreach{hour=>
  assert(greeter(hour) == "good evening")
}


//Exercise 2
//write a method that when I pass a 24hour  returns 'good morning'
// and 'good evening' as you would expect
// but when it is 12 it replies 'its lunch time!'

def lunchGreeter(hour: Int):String = {
  if (hour < 12) {
    "good morning"
  } else if (hour == 12) {
    "its lunch time!"
  }else {
    "good evening"
  }
}


////Exercise 2 assertions
List.range(0,12).foreach{hour=>
  assert(lunchGreeter(hour) == "good morning")
}
assert(lunchGreeter(12) == "its lunch time!")
List.range(13,25).foreach{hour=>
  assert(lunchGreeter(hour) == "good evening")
}



//Exercise 3
// write a method that when parsed a first name and a second name confirms that it is peter pan
// when given (peter, pan) return 'yes its you'
// when given any thing else return 'no your not the real pan'

def isItPeterPan(firstname: String, surname: String):String = {
  if (firstname == "peter" && surname == "pan") "yes its you" else "no your not the real pan"
}

////Exercise 3 assertions
assert(isItPeterPan("peter", "pan") == "yes its you")
assert(isItPeterPan("dane", "pan") == "no your not the real pan")
assert(isItPeterPan("peter", "pascal") == "no your not the real pan")
assert(isItPeterPan("captain", "hook") == "no your not the real pan")


//Exercise 4
// write a method that when i pass a 24hr hour,
// returns the following depending on the hour
// 9 - 'you may have breakfast'
// 11 - 'you may have an apple'
// 12 - 'you may have a tuna sandwich'
// 13 - 'you may have melon slices'
// 16 - 'you may have protein shake'
// 18 - 'you may have dinner'
// and at any other time it returns - 'you can not eat right now'

def dietHelper(hour: Int): String = {
  if (hour == 9) {
    "you may have breakfast"
  } else if (hour == 11) {
    "you may have an apple"
  } else if (hour == 12) {
    "you may have a tuna sandwich"
  } else if (hour == 13) {
    "you may have melon slices"
  } else if (hour == 16) {
    "you may have protein shake"
  } else if (hour == 18) {
    "you may have dinner"
  } else {
    "you can not eat right now"
  }
}



////Exercise 4 assertions
assert(dietHelper(2) == "you can not eat right now")
assert(dietHelper(6) == "you can not eat right now")
assert(dietHelper(9) == "you may have breakfast")
assert(dietHelper(10) == "you can not eat right now")
assert(dietHelper(11) == "you may have an apple")
assert(dietHelper(12) == "you may have a tuna sandwich")
assert(dietHelper(13) == "you may have melon slices")
assert(dietHelper(14) == "you can not eat right now")
assert(dietHelper(16) == "you may have protein shake")
assert(dietHelper(18) == "you may have dinner")
assert(dietHelper(20) == "you can not eat right now")





// There must be a better way to do this!
// please turn cassette to side B, Pattern matching

