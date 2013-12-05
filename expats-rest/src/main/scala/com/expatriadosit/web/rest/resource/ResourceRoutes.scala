package com.expatriadosit.web.rest.resource

import spray.routing.Route
import spray.http.HttpResponse
import spray.routing.directives.PathDirectives.pathPrefix
import spray.routing.directives.MethodDirectives._

trait ResourceRoutes extends TestResource {

  def route = testRoute
}

trait TestResource {
  def testRoute : Route = pathPrefix("test") {
    get {
      ctx =>
        ctx.complete(HttpResponse(status = 200, entity = """{ "message": "success"  }"""))
    }
  }
}
