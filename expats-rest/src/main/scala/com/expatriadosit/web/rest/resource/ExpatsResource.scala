package org.expatriadosit.web.rest.resource

import akka.actor.{ActorLogging, Actor}
import spray.http.{HttpBody, HttpResponse, HttpRequest}
import spray.http.HttpMethods._
import org.expatriadosit.web.common.entity.SampleEntityObject
import org.expatriadosit.web.common.entity.RegistryRecordProtocol._
import spray.http.MediaTypes._
import reactivemongo.api.collections.default.BSONCollection
import spray.json._

class ExpatsResource (collection: BSONCollection) extends Actor with ActorLogging {
  val rx = """^\/api\/(\w*)\/(\w*)$""".r

  def receive = {
    case HttpRequest(GET, path, _, _, _) => {
      val origin = sender
      path match {
        case rx("sample",id) => {
          //Return hardcoded sample resource
          val sample = SampleEntityObject(id, "Hardcoded name", "Hardcoded description")
          origin ! HttpResponse( status = 200, entity = HttpBody(`application/json`,sample.toJson.toString) )
        }
        case _ => origin ! HttpResponse( status = 404)
      }
    }

    //Everything else we don't know about, resource is not known
    case _ => {
      sender ! HttpResponse( status = 404)
    }
  }
}
