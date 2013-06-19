package org.expatriadosit.web.common.conf

import com.typesafe.config.ConfigFactory
import scala.collection.JavaConverters._

object configuration {
  private val defaultConf = ConfigFactory.load
  lazy val conf = ConfigFactory.load("expats-app").withFallback(defaultConf)

  lazy val servers = conf.getStringList("mongo.servers").asScala.toSeq
  lazy val db = conf.getString("mongo.database")
  lazy val sampleCollectionName = conf.getString("mongo.collection.sample")
}
