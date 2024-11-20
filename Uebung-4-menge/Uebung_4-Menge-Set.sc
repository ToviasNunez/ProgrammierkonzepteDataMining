type Set = Int => Boolean

def createEmptySet:Set = _ => false

def contains(elem:Int, set:Set):Boolean = set(elem)

def insert(elem:Int, set:Set):Set = x => set(x) || x==elem
def createRange(a:Int, b:Int):Set = x => x>=a && x <=b
def union(set1:Set, set2:Set):Set = x => set1(x)||set2(x)
def toList(set:Set, a:Int,b:Int):List[Int] = (a to b).filter(set).toList

def testSetFunctions(): Unit = {
  val emptySet = createEmptySet
  assert(!contains(1, emptySet), "Test für leeres Set fehlgeschlagen")

  val singleSet = insert(1, emptySet)
  assert(contains(1, singleSet), "Test für Einfügen fehlgeschlagen")

  val rangeSet = createRange(1, 5)
  assert(contains(3, rangeSet), "Test für RangeSet fehlgeschlagen")
  assert(!contains(6, rangeSet), "Test für RangeSet fehlgeschlagen")

  val unionSet = union(singleSet, rangeSet)
  assert(contains(1, unionSet), "Test für Union fehlgeschlagen")
  assert(contains(4, unionSet), "Test für Union fehlgeschlagen")
  assert(!contains(6, unionSet), "Test für Union fehlgeschlagen")

  val listFromSet = toList(rangeSet, 1, 10)
  assert(listFromSet == List(1, 2, 3, 4, 5), "Test für toList fehlgeschlagen")
}

testSetFunctions()

println("Alle Tests bestanden!")