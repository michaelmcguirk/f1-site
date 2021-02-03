package models

//ToDo create DriverResult trait.
case class DriverQualiResult(driverId: String, race: Option[String], year: Option[String], position: Option[String], time: Option[String])
