package v1.driver

import cats.data.{EitherT, Ior, IorT}
import com.google.inject.Inject
import cats.implicits._
import models.{Driver, DriverQualiResult, DriverRaceResult}
import models.errors.ErgastError
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.ergast.ErgastF1

import scala.concurrent.{ExecutionContext, Future}

class DriverController @Inject()(ws: WSClient, ergast: ErgastF1, val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext) extends BaseController {

  type ErgastResultF[A] = EitherT[Future, ErgastError, A]

  def getDrivers: Action[AnyContent] = Action.async {
    implicit request =>
      Future(NotImplemented(s"Oki doke - This endpoint is not quite there yet...."))
  }

  def getDriver(id: String): Action[AnyContent] = Action.async {
    implicit request =>
      ergast.getDriver(id).value.map{
        case Right(driver) => Ok(Json.toJson(driver))
        case Left(_) =>   NotFound("Not Found")// ToDo: Work with actual error here
      }
  }

  def getDriverAction(id: String): IorT[Future, List[ErgastError], Driver] = {
    //ToDo: make this so that if we get at least basic driver info,
    // then should return that. IorT collect errors
    ergast.getDriver(id).value.map {
      case Right(driver) =>
        //val bestResult = getBestResult(driver)
        val bestResultT = getBestResult(driver)
        val polesT = getPoles(driver)
        (bestResultT, polesT).parMapN{
          (bestResult, poles) =>
            driver.copy(bestRaceResult = Some(bestResult), poles = Some(poles))
        }
      case Left(error) => IorT.leftT[Future,Driver](error)
    }
  }

  def getBestResult(driver: Driver): ErgastResultF[DriverRaceResult] = ???

  def getPoles(driver: Driver): ErgastResultF[List[DriverQualiResult]] = ???
}
