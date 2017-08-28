package domain

import play.api.libs.json.{Format, Json}

case class Bouteille(id: Int,
                     nom: String,
                     nomChateau: Option[String],
                     millesime: Int,
                     contenanceEnML: Int)

object Bouteille {
  implicit val format: Format[Bouteille] = Json.format[Bouteille]
}