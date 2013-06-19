package org.expatriadosit.web.common.entity

import spray.json.DefaultJsonProtocol

case class SampleEntity(id: Option[String], name: String, description: Option[String])

object SampleEntityObject {
  def apply(name: String) = new SampleEntity(None, name, None)
  def apply(id: String, name: String, description: String) = new SampleEntity(Some(id), name, Some(description))
}

//Mapper to provide toJSon methods to SampleEntity
object RegistryRecordProtocol extends DefaultJsonProtocol {
  implicit val recordFormat = jsonFormat3(SampleEntity)
}