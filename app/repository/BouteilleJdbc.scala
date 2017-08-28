package repository

case class BouteilleJdbc(id: Int,
                         nom: String,
                         nomChateau: Option[String],
                         millesime: Int,
                         contenanceEnML: Int)