package models

import play.api.libs.json.Json

case class Drivers(drivers: List[Driver])

object Drivers {
  implicit val driversFormat = Json.format[Drivers]
}