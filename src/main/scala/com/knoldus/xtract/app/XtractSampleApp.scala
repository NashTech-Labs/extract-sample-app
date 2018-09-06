package com.knoldus.xtract.app

import com.knoldus.xtract.util.XmlHelper


object XtractSampleApp extends App with XmlHelper {
  val path = "src/main/resources/person.xml"
  val response = xtract(path)
  println("***RESPONSE: " + response)
}
