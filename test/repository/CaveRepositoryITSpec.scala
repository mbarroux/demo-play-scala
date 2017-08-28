package repository

import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll}
import org.scalatestplus.play.PlaySpec
import shared.EmbeddedPostgresDatabase

import scala.concurrent.Await
import scala.concurrent.duration._

class CaveRepositoryITSpec extends PlaySpec with MockitoSugar with BeforeAndAfter with BeforeAndAfterAll with EmbeddedPostgresDatabase {

  import driver.api._

  var caveRepository: CaveRepository = _

  override protected def beforeAll(): Unit =
    createSchema

  before {
    caveRepository = new CaveRepository(
      driver = driver,
      database = database
    )
  }

  override protected def afterAll(): Unit =
    database.close()

  "ajouterBouteille" should {
    "valoriser le nom de la bouteille de vin" in {
      // Given & When
      val idNouvelleBouteille = Await.result(caveRepository.ajouterBouteille(
        nom = "Champy Perest",
        nomChateau = Some("Savigny Vergelesses"),
        millesime = 1965,
        contenanceEnML = 750
      ), 2.seconds)

      // Then
      Await.result(selectFromBouteille[String]("NOM", idNouvelleBouteille), 2.seconds) mustBe "Champy Perest"
    }

    "ne pas valoriser le nom du chateau de la bouteille de vin quand pas renseigne" in {
      // Given & When
      val idNouvelleBouteille = Await.result(caveRepository.ajouterBouteille(
        nom = "Champy Perest",
        nomChateau = None,
        millesime = 1965,
        contenanceEnML = 750
      ), 2.seconds)

      // Then
      Await.result(selectFromBouteille[String]("NOM_CHATEAU", idNouvelleBouteille), 2.seconds) mustBe null
    }

    "valoriser le nom du chateau de la bouteille de vin quand il est renseigne" in {
      // Given & When
      val idNouvelleBouteille = Await.result(caveRepository.ajouterBouteille(
        nom = "Champy Perest",
        nomChateau = Some("Savigny Vergelesses"),
        millesime = 1965,
        contenanceEnML = 750
      ), 2.seconds)

      // Then
      Await.result(selectFromBouteille[String]("NOM_CHATEAU", idNouvelleBouteille), 2.seconds) mustBe "Savigny Vergelesses"
    }

    "valoriser le millesime de la bouteille de vin" in {
      // Given & When
      val idNouvelleBouteille = Await.result(caveRepository.ajouterBouteille(
        nom = "Champy Perest",
        nomChateau = Some("Savigny Vergelesses"),
        millesime = 1965,
        contenanceEnML = 750
      ), 2.seconds)

      // Then
      Await.result(selectFromBouteille[Int]("MILLESIME", idNouvelleBouteille), 2.seconds) mustBe 1965
    }

    "valoriser la contenance de la bouteille de vin" in {
      // Given & When
      val idNouvelleBouteille = Await.result(caveRepository.ajouterBouteille(
        nom = "Champy Perest",
        nomChateau = Some("Savigny Vergelesses"),
        millesime = 1965,
        contenanceEnML = 750
      ), 2.seconds)

      // Then
      Await.result(selectFromBouteille[Int]("CONTENANCE", idNouvelleBouteille), 2.seconds) mustBe 750
    }
  }

  private def selectFromBouteille[A](colonne: String, id: Int)(implicit rconv: slick.jdbc.GetResult[A]) =
    database.run(sql"""
           SELECT #$colonne
           FROM bouteille
           WHERE id = $id
       """.as[A].head
    )
}
