package models

import play.api.libs.json.Json

case class DriverRaceResult(driverId: String, race: Option[String], year: Option[String], position: Option[String])

object DriverRaceResult {
  implicit val driverResultFormat = Json.format[DriverRaceResult]
}
