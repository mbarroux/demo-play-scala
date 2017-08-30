package services

import javax.inject.Inject

import domain.Bouteille
import forms.CreationBouteilleData
import repository.{BouteilleJdbc, CaveRepository}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class CaveService @Inject()(caveRepository: CaveRepository) {

}
