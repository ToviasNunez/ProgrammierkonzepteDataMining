package implementation

case object Empty extends IntList{
  def isEmpty = true
  def head= throw new Error ("List is Empty")
  def tail= throw new Error ("List is Empty")
  def prefix(elem:IntList):IntList= Empty
  def flip:IntList= Empty
  def changeNumber(pred:Int=>Boolean, change: Int=>IntList):IntList= Empty
}