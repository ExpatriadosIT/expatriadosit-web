package org.expatriadosit.web.rest

import com.typesafe.scalalogging.slf4j.Logging
import akka.actor.{ActorSystem, Props, ActorRef}
import org.expatriadosit.web.common.conf.configuration._
import org.expatriadosit.web.common.mongo.mongoDBobjects
import org.expatriadosit.web.rest.resource.ExpatsResource
import akka.io.IO
import spray.can.Http

object ExpatsWebApp extends App with Logging {

  implicit val system = ActorSystem()

  val httpServer = system.actorOf(Props( new ExpatsResource(mongoDBobjects.sampleCollection)))
  // create a new HttpServer using our handler and tell it where to bind to
  IO(Http) ! Http.Bind(
                httpServer,
                interface = conf.getString("http.server.interface"),
                port = conf.getInt("http.server.port")
              )
}