import sbt._

object Dependencies {

  case class PlayDependencyVersions(playVersion: String, jacksonVersion: String)

  // Matrice des dépendances indispensables à play, doivent être changées ensembles pour assurer la compatibilié des versions
  val PlayDependencyVersions(playVersion: String, jacksonVersion: String) =
    PlayDependencyVersions(
      playVersion = "2.5.15",
      jacksonVersion = "2.7.8"
    )

  val slickVersion = "3.2.0"

  val slickPgVersion = "0.15.0"

  val play: ModuleID = "com.typesafe.play" %% "play" % playVersion

  val playJson: ModuleID = "com.typesafe.play" %% "play-json" % playVersion

  val slickPg: ModuleID = "com.github.tminglei" %% "slick-pg" % slickPgVersion

  val slickHikariCp: ModuleID = "com.typesafe.slick" %% "slick-hikaricp" % slickVersion

  val scalaGuice: ModuleID = "net.codingwell" %% "scala-guice" % "4.0.1"

  val h2database: ModuleID = "com.h2database" % "h2" % "1.4.192" % "test"

  val scalatestplus: ModuleID = "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0" % "test"

  val mockito: ModuleID = "org.mockito" % "mockito-all" % "1.10.19" % "test"
}
