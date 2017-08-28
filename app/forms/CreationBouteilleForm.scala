package forms

import play.api.data.Form
import play.api.data.Forms.{mapping, optional, text, _}

case class CreationBouteilleData(nom: String,
                                 nomChateau: Option[String],
                                 millesime: Int,
                                 contenanceEnMl: Int)

object CreationBouteilleForm {

  val form = Form(
    mapping(
      "nom" -> nonEmptyText,
      "nomChateau" -> optional(text),
      "millesime" -> number,
      "contenanceEnMl" -> number(min = 200, max = 18000) // Piccolo = 200 mL / Melchior = 18 litres !!!
    )(CreationBouteilleData.apply)(CreationBouteilleData.unapply)
  )
}