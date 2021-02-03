package models

import play.api.libs.json.Json

case class Driver(driverId: String,
                  permanentNumber: Option[String],
                  code: Option[String],
                  url: Option[String],
                  givenName: Option[String],
                  familyName: Option[String],
                  dateOfBirth: Option[String],
                  nationality: Option[String],
                  bestRaceResult: Option[DriverRaceResult],
                  poles: Option[List[DriverQualiResult]]
                 )

object Driver {
  implicit val driverFormat = Json.format[Driver]
}
