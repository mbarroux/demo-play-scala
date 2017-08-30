package forms

import play.api.data.Form
import play.api.data.Forms.{mapping, optional, text, _}

case class CreationBouteilleData(nom: String,
                                 nomChateau: Option[String],
                                 millesime: Int,
                                 contenanceEnMl: Int)

object CreationBouteilleForm {

  val form = ???
}