package com.example

class CubeCalculator(x: Int):
  def cube:Int = (x*x*x)
  override def toString:String = s"$cube"
end CubeCalculator
