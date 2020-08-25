package com.wk

object ScalaObject {
  def main(args:Array[String]) :Unit = {
      val filePath = args(0)
      println("filepath = ", filePath)
      for(valu <- args){
        println("value: ", valu)
      }
  }
}
