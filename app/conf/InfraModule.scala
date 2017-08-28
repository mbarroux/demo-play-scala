package conf

import com.google.inject.{AbstractModule, Provides, Singleton}
import net.codingwell.scalaguice.ScalaModule
import play.api.Configuration
import play.api.inject.ApplicationLifecycle
import repository.CaveRepository
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.PostgresProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class InfraModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {

  }

  @Provides
  @Singleton
  def provideDatabase(lifecycle: ApplicationLifecycle,
                      configuration: Configuration): Database = {
    val database = Database.forConfig(
      path = "db.default",
      config = configuration.underlying
    )

    lifecycle.addStopHook(() => Future(database.close()))

    database
  }

  @Provides
  @Singleton
  def provideCaveRepository(database: Database): CaveRepository =
    new CaveRepository(
      driver = PostgresProfile,
      database = database
    )
}
