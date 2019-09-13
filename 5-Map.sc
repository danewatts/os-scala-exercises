//Map is a collection of pairs of keys and values
//Map takes 2 type parameters, left being the Key type, right being Value type
val stringMap: Map[String, String] = Map(
  "key1" -> "value1",
  "key2" -> "value2",
  "key3" -> "value3"
)

//Maps can be wrote in two ways. it is essentially a list of tuples.
val numMap: Map[Int, Int] = Map(
  (1, 100),
  (2, 200),
  (3, 300)
)

//Maps can be empty
Map.empty[Int, String]

val daysOfWeek: Map[Int, String] = Map(
  1 -> "Monday",
  2 -> "Tuesday"
)

//You can extract the keys or the values into a list from a Map
//.keys
numMap.keys

//.values
numMap.values

//Maps are used essentially for a lookup. You can access a value by calling MyMap.apply(key)
//.apply
numMap(3)

//Apply will throw exceptions if the element is not there. You can use .get to retrieve an option
//.get
numMap.get(3)
numMap.get(4)

//.contains will check if there is a given KEY in a Map
numMap.contains(3)

//Since Map is a collection it contains most of the standard methods: ++, drop, head, forall, exists, filter etc
//The difference is, an element of a Map has two associated bits of data (Key and value) so the type signature can be different
//The type signature for the parameter of exists is p: ((K, V)) ⇒ Boolean. Your function can see the Key and the Value
//However, this will be in the form of a Tuple. The key can be accessed by myTuple._1 and the value by myTuple._2

//.exists
daysOfWeek.exists(element => element._2 == "tuesday")

//.filter
daysOfWeek.filter(element => element._1 < 3)

//mapping is a bit more complex. If your function returns a tuple, your .map will return a Map
//If it returns a single value, your Map will become a List
//.map Tuple
numMap.map(element => (element._1 + 1, element._2 + 1))

//.map List
numMap.map(element => element._2 + 1)

//.flatMap can flatten any level of collections. So if you have a List[Map[String, String] you can flatMap
//this to become one big Map
numMap.map(element => Map(element._1 -> element._2))
numMap.flatMap(element => Map(element._1 -> element._2))


//.mapValues can be used to just modify the values of a Map
daysOfWeek.mapValues(_ + "s")