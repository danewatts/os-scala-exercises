
//Traits can define and implement function and values
trait Greeter {
  println("Greeter")
  val greeting = "Hello" //Concrete value
  def greet(): Unit = println(greeting) //Concrete implementation of function
}

//Objects and classes can inherit these functions by extending
//a trait (or a class)
object HelloGreeter extends Greeter
HelloGreeter.greet()

//Objects can override methods and values from traits
object HeyGreeter extends Greeter {
  override val greeting: String = "Hey"
}
HeyGreeter.greet()




//Traits can also define values and functions that the subclass
//needs to implement (Abstract). NOT ???
trait Animal {
  val noise: String //Abstract value
  def makeNoise(): Unit //Abstract function
}

//When a class extends a trait with unimplemented methods,
//the class MUST implement those.
class Human(name: String) extends Animal {
  override val noise: String = "Ugh, look at the weather!"
  override def makeNoise(): Unit = println(noise)
}

//Classes can implement more than trait
trait WithTail {
  def wagTail(): Unit
}

class Dog(name: String) extends Animal with WithTail {
  override val noise: String = "Woof!"
  override def makeNoise(): Unit = println(noise) //Same for both animals, how to refactor?
  override def wagTail(): Unit = ()
}
//Traits can also extend other traits



//Traits resolve right to left but evaluate left to right
trait Greeter2 extends Greeter {
  println("Greeter2")
  override def greet(): Unit = {
    println("Greetings2")
    super.greet()
  }
}

trait Greeter3 extends Greeter {
  println("Greeter3")
  override def greet(): Unit = {
    println("Greetings3")
    super.greet()
  }
}

object HelloGreeter2 extends Greeter2 with Greeter3
HelloGreeter2.greet()
// Useful for tests as you can write a stub trait which your test
// implementation extends last.



//It makes sense that only Animals can extend the WagTail trait.
//At the moment any class can extend WithTail
class Potato extends WithTail {
  override def wagTail(): Unit = println("What???")
}
//We can change the WithTail trait to say anything that extends it must also
//extend some sort of animal implementation


//Traits have two primary usages. Using them as types (Animal) and for boiler plate code (Printer)
//You can make a function take in something that is the same type as your trait
def noisyAnimal(animal: Animal): Unit = animal.makeNoise()
//You can pass any implementation of animal to this
noisyAnimal(new Dog("Grace"))
noisyAnimal(new Human("Pan"))

//In functions you can specify that the parameter passed through must implement two traits
def happyAnimal(animal: Animal with WithTail): Unit = {
  animal.makeNoise(); animal.wagTail()
}
happyAnimal(new Dog("Grace")) //WORKS
//happyAnimal(new Human("Pan"))//Doesnt work, as Human does not implement WithTail




//Abstract classes are classes that can have abstract definitions (Not implemented, like a trait)
//Abstract classes are also allowed to have parameters, unlike a trait
abstract class Cat(name: String) extends Animal with WithTail {
  val hasFur: Boolean
}
//Abstract classes are more interoperable with java code. Typically, we would use an
//abstract class over a trait when writing library code