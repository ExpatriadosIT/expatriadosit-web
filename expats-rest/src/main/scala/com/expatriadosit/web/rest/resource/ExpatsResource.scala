package org.expatriadosit.web.rest.resource

import akka.actor.Actor
import spray.http.{HttpEntity, HttpResponse}
import org.expatriadosit.web.common.entity.SampleEntityObject
import org.expatriadosit.web.common.entity.RegistryRecordProtocol._
import spray.http.MediaTypes._
import reactivemongo.api.collections.default.BSONCollection
import spray.json._
import spray.routing.{Route, HttpService}
import com.expatriadosit.web.rest.resource.TestResource

class ExpatsResource (collection: BSONCollection) extends Actor with HttpService with TestResource {

  def actorRefFactory = context

  def receive = runRoute( expatRoute ~ testRoute  )

  def expatRoute : Route = pathPrefix("sample" / Segment) { id =>
    get { ctx =>
      val sample = SampleEntityObject(id, "Hardcoded name", "Hardcoded description")
      ctx.complete(HttpResponse(status = 200, entity = HttpEntity(`application/json`,sample.toJson.toString)))
    }
  }
}
