// This has an associated ppt.
import play.api.libs.json._
import play.api.libs.functional.syntax._

//Data model for a dog
case class Dog(name: String, breed: String)
val dog = Dog("Dog", "Bounty Hunter")
val dogJsonStr = """{"name":"Dog","breed":"Bounty Hunter"}"""
//^ When the fields in the json directly map to the parameters
// in your case class, you can use Json.reads[Dog], Json.writes[Dog]
// and Json.format[Dog] for your conversions
implicit val dogFormat: Format[Dog] = Json.format[Dog]
Json.toJson(dog)
Json.parse(dogJsonStr).as[Dog]

//.parse can throw an exception
val invalidJson = """{"a:""}"""
//Json.parse(invalidJson)

//.as can throw an exception
val invalidDogJsonStr = """{"name":"Dog"}"""

// NOTE - Uncomment to test invalid json error
//Json.parse(invalidDogJsonStr).as[Dog]

case class Person(name: String, age: Int)
val person = Person("Pascal", 38)
val personJsonStr = """{"_firstName":"Pascal","_age": 38}"""

// When the fields in your json don't directly map to your case class
// You will have to write a custom reads and writes.

implicit val personReads: Reads[Person] = (
  (JsPath \ "_firstName").read[String] and
    (JsPath \ "_age").read[Int]
  )(Person.apply _)

implicit val personWrites: Writes[Person] = new Writes[Person] {
  override def writes(o: Person): JsValue = Json.obj(
    "_firstName" -> o.name,
    "_age" -> o.age
  )
}

Json.parse(personJsonStr).as[Person]
Json.toJson(person)


case class Item(name: String, price: Double, amount: Int, discount: Option[Double])
case class Receipt(items: List[Item])


// Ignore the formatting below, the repl sometimes breaks multiline
val receiptJsonStr =
  """   {                                         """ +
    """     "items": [                              """ +
    """       {                                     """ +
    """         "name":"Lynx Body Spray",           """ +
    """         "amount":2,                         """ +
    """         "price":5                           """ +
    """       },                                    """ +
    """       {                                     """ +
    """         "name":"Pineapple Juice",           """ +
    """         "amount":7,                         """ +
    """         "price":1.99,                       """ +
    """         "discount":10                       """ +
    """       }                                     """ +
    """     ]                                       """ +
    """   }                                         """

implicit val itemsReads = Json.format[Item]
implicit val receiptReads = Json.format[Receipt]

Json.parse(receiptJsonStr).as[Receipt]
//What if we wanted to add a total but it didnt come in the data?
