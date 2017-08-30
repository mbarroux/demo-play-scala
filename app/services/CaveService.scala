package services

import javax.inject.Inject

import domain.Bouteille
import forms.CreationBouteilleData
import repository.{BouteilleJdbc, CaveRepository}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class CaveService @Inject()(caveRepository: CaveRepository) {

  def listerBouteilles(): Future[Seq[Bouteille]] =
    caveRepository.listerBouteilles().map(listeDesBouteillesJdbc => listeDesBouteillesJdbc.map(toBouteille))

  def recupererBouteille(id: Int): Future[Option[Bouteille]] =
    caveRepository.recupererBouteille(id).map(bouteilleJdbcOptionnelle => bouteilleJdbcOptionnelle.map(toBouteille))

  private def toBouteille(bouteilleJdbc: BouteilleJdbc) = Bouteille(
    id = bouteilleJdbc.id,
    nom = bouteilleJdbc.nom,
    nomChateau = bouteilleJdbc.nomChateau,
    millesime = bouteilleJdbc.millesime,
    contenanceEnML = bouteilleJdbc.contenanceEnML
  )
}
