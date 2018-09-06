package com.knoldus.xtract.util

import java.io.File
import com.knoldus.xtract.models._
import com.lucidchart.open.xtract.XmlReader
import scala.io.Source
import scala.xml.XML

/**
  * This class provide functionality to parse xml data into scala case classes
  */
trait XmlHelper {

  def xtract(filePath: String): Option[Response] = {
    val xmlData = Source.fromFile(new File(filePath)).getLines().mkString("\n")
    println("***File to be parsed: ")
    println(xmlData)
    val xml = XML.loadString(xmlData)
    XmlReader.of[Response].read(xml).toOption
  }

}
