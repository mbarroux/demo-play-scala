package shared

import java.io.File
import java.nio.file.{Files, Paths}

import slick.jdbc.JdbcBackend.Database
import slick.jdbc.{H2Profile, JdbcProfile}

import scala.concurrent.Await
import scala.concurrent.duration._

trait EmbeddedDatabase {

  val driver: JdbcProfile

  val database: Database

  val scriptFile: String

  import driver.api._

  def executeSQL(file: String): Int = {
    val directory = new File(this.getClass.getClassLoader.getResource(".").toURI)
    val embeddedScript = Paths.get(directory.getAbsolutePath, file)
    val requests = new String(Files.readAllBytes(embeddedScript))

    Await.result(database.run(sqlu"""#$requests"""), Duration.Inf)
  }

  def createSchema: Int =
    executeSQL("bdd" + File.separator + scriptFile)
}

trait EmbeddedPostgresDatabase extends EmbeddedDatabase {

  override val driver: JdbcProfile = H2Profile

  override val database: Database = Database.forURL(
    url = "jdbc:h2:mem:test",
    driver = "org.h2.Driver",
    keepAliveConnection = true,
    user = "sa",
    password = ""
  )

  override val scriptFile = "embedded_postgres_database.sql"
}