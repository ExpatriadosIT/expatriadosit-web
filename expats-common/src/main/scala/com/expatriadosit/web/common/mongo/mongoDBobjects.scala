package org.expatriadosit.web.common.mongo

import reactivemongo.api.MongoDriver
import reactivemongo.api.collections.default.BSONCollection
import org.expatriadosit.web.common.conf.configuration._
import scala.concurrent.ExecutionContext.Implicits._

object mongoDBobjects {
  private implicit val mongo = new MongoDriver
  private val connection = mongo.connection(servers)
  val propertyDB = connection.db(db)
  lazy val sampleCollection: BSONCollection = propertyDB.collection(sampleCollectionName)
  lazy val actorSystem = mongo.system
}
