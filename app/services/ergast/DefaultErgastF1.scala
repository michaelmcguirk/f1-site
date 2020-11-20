package services.ergast

import cats.data.EitherT
import cats.implicits._
import com.google.inject.Inject
import models.errors.{ErgastError, ErgastNotFoundError}
import models.{Driver, Drivers}
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

class DefaultErgastF1 @Inject()(ws: WSClient) (implicit ec: ExecutionContext) extends ErgastF1 {

  def getDriver(id: String): EitherT[Future, ErgastError, Driver] = {
    val req = ws.url(s"http://ergast.com/api/f1/drivers/$id.json")
    val driverF = req.get().map{res =>
      (res.json \ "MRData" \ "DriverTable" \ "Drivers").as[Seq[Driver]]
    }.map {
      case driver :: _ => Right(driver) //EitherT.right(driver)
      case Nil => Left(ErgastNotFoundError(message = Some(s"Driver not found: $id")))
      //case Nil => EitherT.left(ErgastNotFoundError(message = Some(s"Driver not found: $id")))
    }
    EitherT(driverF)
  }

  def getDrivers(): Future[Drivers] = ???

}
