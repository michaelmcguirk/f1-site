package models.errors

sealed trait ErgastError extends Throwable{
  def httpCode: Option[Int]
  def errorCode: Option[Int]
  def message: Option[String]
  def exception: Option[Throwable]
}

final case class ErgastNotFoundError(
  val httpCode: Option[Int] = None,
  val errorCode: Option[Int] = Some(1404),
  val message: Option[String] = None,
  val exception: Option[Throwable] = None
) extends ErgastError