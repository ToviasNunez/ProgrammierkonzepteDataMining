package implementation


case class Cons(head:Int, tail:IntList) extends IntList{
  def isEmpty= false
  def prefix(elem:IntList):IntList= elem match {
    case Empty => this
    case Cons(h,t) => Cons(h, prefix(t))
  }
  def flip:IntList= this match
    case Cons(h,t) => Cons(h,Empty).prefix(t.flip)

  def changeNumber(pred:Int=>Boolean, change: Int=>IntList):IntList= this match
    //if the head matches the predicate, apply the change function
    case  Cons(head, tail) if pred(head) => tail.changeNumber(pred, change).prefix(change(head))
    // if the head not match the predicate , keep it and process the tail
    case Cons(head, tail) => Cons(head,tail.changeNumber(pred,change))

}
