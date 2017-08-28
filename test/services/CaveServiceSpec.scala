package services

import forms.CreationBouteilleData
import org.mockito.Matchers.any
import org.mockito.Mockito._
import org.mockito.{Matchers => MockitoMatchers}
import org.scalatest.BeforeAndAfter
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec
import repository.CaveRepository

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class CaveServiceSpec extends PlaySpec with MockitoSugar with BeforeAndAfter {

  var caveService: CaveService = _
  var caveRepository: CaveRepository = _

  before {
    caveRepository = mock[CaveRepository]
    caveService = new CaveService(caveRepository)
  }

  "ajouterBouteille" should {
    "doit recuperer les infos de la bouteille depuis la base a partir de l'id genere lors de sa creation" in {
      // Given
      when(caveRepository.ajouterBouteille(any[String], any[Option[String]], any[Int], any[Int])).thenReturn(Future.successful(183))
      val bouteilleData = CreationBouteilleData(
        nom = "Champy Perest",
        nomChateau = Some("Savigny Vergelesses"),
        millesime = 1965,
        contenanceEnMl = 750
      )

      // When
      Await.ready(caveService.ajouterBouteille(bouteilleData), 2.seconds)

      // Then
      verify(caveRepository).recupererBouteille(MockitoMatchers.eq(183))
    }
  }

}
