package v1.driver

import com.google.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class DriverRouter @Inject()(controller: DriverController) extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/") => controller.getDrivers
    case GET(p"/$id") => controller.getDriver(id)
  }
}
