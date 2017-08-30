package repository

import slick.jdbc.JdbcBackend.Database
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

import scala.concurrent.Future

class CaveRepository(val driver: JdbcProfile,
                     val database: Database) {

  import driver.api._

  class BouteilleEntity(tag: Tag) extends Table[BouteilleJdbc](tag, "BOUTEILLE") {
    def id: Rep[Int] = column[Int]("ID", O.PrimaryKey, O.AutoInc)

    def nom: Rep[String] = column[String]("NOM")

    def nomChateau: Rep[Option[String]] = column[Option[String]]("NOM_CHATEAU")

    def millesime: Rep[Int] = column[Int]("MILLESIME")

    def contenance: Rep[Int] = column[Int]("CONTENANCE")

    def * : ProvenShape[BouteilleJdbc] = (id, nom, nomChateau, millesime, contenance) <> (BouteilleJdbc.tupled, BouteilleJdbc.unapply)
  }

  lazy val bouteilleQuery: TableQuery[BouteilleEntity] = TableQuery[BouteilleEntity]

  def listerBouteilles(): Future[Seq[BouteilleJdbc]] =
    database.run(bouteilleQuery.sortBy(_.nom).result)

  def recupererBouteille(id: Int): Future[Option[BouteilleJdbc]] =
    database.run(bouteilleQuery.filter(_.id === id).result.headOption)

  def ajouterBouteille(nom: String,
                       nomChateau: Option[String],
                       millesime: Int,
                       contenanceEnML: Int): Future[Int] =
    database.run(bouteilleQuery returning bouteilleQuery.map(_.id) += BouteilleJdbc(
      id = 0,
      nom = nom,
      nomChateau = nomChateau,
      millesime = millesime,
      contenanceEnML = contenanceEnML
    ))
}
