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

}