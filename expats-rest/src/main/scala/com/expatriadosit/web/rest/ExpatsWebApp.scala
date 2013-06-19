package org.expatriadosit.web.rest

import spray.can.server.{ServerSettings, HttpServer, SprayCanHttpServerApp}
import com.typesafe.scalalogging.slf4j.Logging
import spray.io.{IOExtension, PerConnectionHandler, PipelineContext}
import akka.actor.{Props, ActorRef}
import org.expatriadosit.web.common.conf.configuration._
import org.expatriadosit.web.common.mongo.mongoDBobjects
import org.expatriadosit.web.rest.resource.ExpatsResource

object ExpatsWebApp extends App with SprayCanHttpServerApp with Logging {

  implicit val ioBridge = IOExtension(system).ioBridge()

  def messageCreator(ctx:PipelineContext) : ActorRef = {
    system.actorOf(Props( new ExpatsResource(mongoDBobjects.sampleCollection)))
  }


  val httpServer = system.actorOf(Props(new HttpServer(ioBridge, PerConnectionHandler(messageCreator), ServerSettings())), "http-server")
  // create a new HttpServer using our handler and tell it where to bind to
  httpServer ! Bind(interface = conf.getString("http.server.interface"), port = conf.getInt("http.server.port"))
}