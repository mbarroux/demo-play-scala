package controllers

import javax.inject.Inject

import forms.{CreationBouteilleData, CreationBouteilleForm}
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import services.CaveService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.control.NonFatal

class CaveController @Inject()(caveService: CaveService,
                               val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def liste: Action[AnyContent] = Action.async {
    caveService.listerBouteilles().map(listeDesBouteilles =>
      Ok(views.html.listeDesBouteilles(listeDesBouteilles))
    )
  }

  def detail(id: Int): Action[AnyContent] = Action.async {
    caveService.recupererBouteille(id).map(bouteilleOptionnelle =>
      bouteilleOptionnelle.map(bouteille =>
        Ok(views.html.detailBouteille(bouteille))
      ).getOrElse(NotFound(s"La bouteille $id n'existe pas"))
    )
  }

  // meme chose qu'action précédente, seulement on renvoie du json et pas du html
  def detailApi(id: Int): Action[AnyContent] = Action.async {
    caveService.recupererBouteille(id).map(bouteilleOptionnelle =>
      bouteilleOptionnelle.map(bouteille =>
        Ok(Json.toJson(bouteille))
      ).getOrElse(NotFound(s"La bouteille $id n'existe pas"))
    )
  }

  def ajouter: Action[JsValue] = Action.async(BodyParsers.parse.json) { implicit request =>
    def creationBouteille(data: CreationBouteilleData): Future[Result] = {
      caveService.ajouterBouteille(data).map(nouvelleBouteille =>
        Created(Json.toJson(nouvelleBouteille))
      )
    }

    CreationBouteilleForm.form.bindFromRequest.fold(
      formWithErrors => Future.successful(BadRequest(formWithErrors.errorsAsJson)),
      creationBouteille
    )
  }
}