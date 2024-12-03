package implementation

abstract class IntList{
  def isEmpty:Boolean
  def head:Int
  def tail:IntList
  def prefix(elem:IntList):IntList
  def flip:IntList
  def changeNumber(pred:Int=>Boolean, change: Int=>IntList):IntList
}