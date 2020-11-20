package v1.driver

import com.google.inject.Inject
import cats.implicits._
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.ergast.ErgastF1

import scala.concurrent.{ExecutionContext, Future}

class DriverController @Inject()(ws: WSClient, ergast: ErgastF1, val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext) extends BaseController {

  def getDrivers: Action[AnyContent] = Action.async {
    implicit request =>
      Future(Ok(s"Oki doke - This endpoint is not quite there yet...."))
  }

  def getDriver(id: String): Action[AnyContent] = Action.async {
    implicit request =>
      ergast.getDriver(id).value.map{
        case Right(driver) => Ok(Json.toJson(driver))
        case Left(_) =>   NotFound("Not Found")
      }
  }
}
