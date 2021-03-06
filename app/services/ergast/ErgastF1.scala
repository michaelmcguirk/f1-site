package services.ergast

import cats.data.EitherT
import models.errors.ErgastError
import models.{Driver, DriverRaceResult, Drivers}

import scala.concurrent.Future

trait ErgastF1 {
  def getDriver(id: String): EitherT[Future, ErgastError, Driver]
  def getDrivers(): Future[Drivers]
  def getDriverResults(id: String): EitherT[Future, ErgastError, List[DriverRaceResult]]
}
