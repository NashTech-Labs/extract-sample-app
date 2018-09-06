package com.knoldus.xtract.models

import com.lucidchart.open.xtract.XmlReader._
import com.lucidchart.open.xtract.{XmlReader, __}
import play.api.libs.functional.syntax._


object Person {
  implicit val reader: XmlReader[Person] = (
    attribute[String]("name") and
      attribute[String]("dob") and
      attribute[String]("gender") and
      (__ \ "address").read[Address].optional and
      (__ \ "wife").lazyRead(first[Person]).optional and
      (__ \ "husband").lazyRead(first[Person]).optional and
      (__ \ "kids").lazyRead(seq[Person]).default(Nil)
    ) (apply _)
}

case class Person(
                   name: String,
                   dob: String,
                   gender: String,
                   address: Option[Address],
                   wife: Option[Person],
                   husband: Option[Person],
                   kids: Seq[Person]
                 )

object Address {
  implicit val reader: XmlReader[Address] = (
    attribute[String]("street") and
      attribute[String]("city") and
      attribute[String]("state") and
      attribute[String]("pin") and
      attribute[String]("country")
    ) (apply _)
}

case class Address(
                    street: String,
                    city: String,
                    state: String,
                    pin: String,
                    country: String
                  )

case class Response(
                     person: Seq[Person]
                   )

object Response {
  implicit val reader: XmlReader[Response] = (__ \ "person").read(seq[Person]).default(Nil).map(apply _)
}
